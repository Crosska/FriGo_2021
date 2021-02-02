package com.crosska.frigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class LoginActivity extends AppCompatActivity {

    private MaterialCardView login_bar;
    private boolean popup_showed = false;
    private SharedPreferences saved_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!checkRecentAccount()) {
            this.getSupportActionBar().hide();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.activity_login);
            login_bar = findViewById(R.id.login_edit_text_group);
        }
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
        Toast.makeText(getApplicationContext(), "FriGo by Crosska (c)", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkRecentAccount();
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