package com.crosska.frigo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {

    private MaterialCardView login_bar;
    private boolean popup_showed = false;
    private SharedPreferences saved_data;
    private MaterialCardView icon_card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!checkRecentAccount()) {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.activity_login);
            login_bar = findViewById(R.id.login_edit_text_group);
            icon_card = findViewById(R.id.materialCardViewLoginIcon);
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        icon_card.setRadius((float) icon_card.getWidth() / 2);




        ImageView food_icon;
        food_icon = findViewById(R.id.icon_apple);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_banana);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_bread);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_cheese);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_fish);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_hotdog);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_watermelon);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_taco);
        startNewAnimation(food_icon);
    }

    private void startNewAnimation(ImageView food_icon) {
        Random rand = new Random();
        int delay = rand.nextInt(3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                moveIcon(food_icon);
            }
        }, delay);
    }

    /*private void mainProcessing() {
        Thread thread = new Thread(null, doBackgroundThreadProcessing,
                "animation_thread");
        thread.start();
    }

    private Runnable doBackgroundThreadProcessing = new Runnable() {
        public void run() {
            backgroundThreadProcessing();
        }
    };

    private void backgroundThreadProcessing() {

    }*/

    private void moveIcon(ImageView food_icon) {
        Animation animation_move = AnimationUtils.loadAnimation(this, R.anim.food_moving);
        animation_move.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                food_icon.startAnimation(animation_move);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        food_icon.startAnimation(animation_move);
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkRecentAccount();
        icon_card.setRadius((float) icon_card.getWidth() / 2);
        ImageView food_icon;
        food_icon = findViewById(R.id.icon_apple);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_banana);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_bread);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_cheese);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_fish);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_hotdog);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_watermelon);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_taco);
        startNewAnimation(food_icon);
    }

    public void registerButtonClicked(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void loginButtonClicked(View view) {
        login_bar.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.show_login_bar);
        login_bar.startAnimation(animation);
        popup_showed = true;
    }

    public void backgroundClicked(View view) {
        if (popup_showed) {
            login_bar.setVisibility(View.INVISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_login_bar);
            login_bar.startAnimation(animation);
            popup_showed = false;
        }
    }

    public void mainIconClicked(View view) {
        if (popup_showed) {
            login_bar.setVisibility(View.INVISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_login_bar);
            login_bar.startAnimation(animation);
            popup_showed = false;
        }
        //Toast.makeText(this, String.valueOf(icon_card.getWidth()), Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "FriGo by Crosska (c)", Toast.LENGTH_SHORT).show();
    }

    private boolean checkRecentAccount() {
        saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
        String recent_login = saved_data.getString("LGN", "null");
        String recent_password = saved_data.getString("PSD", "null");
        if (!recent_login.equals("null") || !recent_password.equals("null")) {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
            //Toast.makeText(this, recent_login + recent_password, Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

}