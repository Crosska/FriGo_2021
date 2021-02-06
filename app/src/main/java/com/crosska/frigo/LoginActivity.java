package com.crosska.frigo;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {

    private MaterialCardView login_bar;
    private MaterialCardView icon_card;
    private EditText login_edit_text;
    private EditText password_edit_text;
    private CardView error_cardview;
    private boolean login_card_showed = false;
    private boolean error_message_showed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPermissions();
        if (!checkActiveAccount()) {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.activity_login);
            login_bar = findViewById(R.id.login_edit_text_group);
            icon_card = findViewById(R.id.materialCardViewLoginIcon);
            login_edit_text = findViewById(R.id.login_activity_login_text_edit);
            password_edit_text = findViewById(R.id.login_activity_password_text_edit);
            error_cardview = findViewById(R.id.login_activity_error_card);
        }
    }

    private void getPermissions() {
        permissionGET_ACCOUNTS();
        permissionREAD_CONTACTS();
    }

    private void permissionGET_ACCOUNTS() {
        int MY_PERMISSIONS_REQUEST_GET_ACCOUNTS = 0;
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.GET_ACCOUNTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.GET_ACCOUNTS},
                        MY_PERMISSIONS_REQUEST_GET_ACCOUNTS);
            }
        }
    }

    private void permissionREAD_CONTACTS() {
        int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
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
        food_icon = findViewById(R.id.icon_taco);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_watermelon);
        startNewAnimation(food_icon);
        food_icon = findViewById(R.id.icon_hotdog);
        startNewAnimation(food_icon);
    }

    private void startNewAnimation(ImageView food_icon) {
        Random rand = new Random();
        int delay = rand.nextInt(3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                LoginActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        moveIcon(food_icon);
                    }
                });
            }
        }, delay);
    }

    public void registerButtonClicked(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Для работы приложения необходим доступ к контактам", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginBarButtonClicked(View view) {
        login_bar.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.show_login_bar);
        login_bar.startAnimation(animation);
        login_card_showed = true;
    }

    public void backgroundClicked(View view) {
        if (login_card_showed) {
            login_bar.setVisibility(View.INVISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_login_bar);
            login_bar.startAnimation(animation);
            login_card_showed = false;
        }
    }

    public void mainIconClicked(View view) {
        Toast.makeText(getApplicationContext(), "FriGo by Crosska (c)", Toast.LENGTH_SHORT).show();
    }

    public void loginButtonClicked(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {

            String login_sql = login_edit_text.getText().toString();
            String password_sql = password_edit_text.getText().toString();
            String login = "null";
            String password = "null";
            String name = "null";
            int sex = -1;

            SQLiteDatabase DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
            String SQLQuery = "CREATE TABLE IF NOT EXISTS users (login TEXT, pass TEXT, name TEXT , sex INTEGER)";
            DataBase.execSQL(SQLQuery);
            SQLQuery = "SELECT * FROM users WHERE (login = '" + login_sql + "' AND pass = '" + password_sql + "')";
            Cursor query = DataBase.rawQuery(SQLQuery, null);
            if (query.moveToFirst()) {
                login = query.getString(0);
                password = query.getString(1);
                name = query.getString(2);
                sex = query.getInt(3);
            }
            query.close();
            DataBase.close();

            if (login_sql.equals(login) && password_sql.equals(password)) {
                SharedPreferences saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
                SharedPreferences.Editor ed = saved_data.edit();
                ed.putString("LGN", login);
                ed.apply();
                ed.putString("PSD", password);
                ed.apply();
                ed.putString("NAME", name);
                ed.apply();
                ed.putInt("SEX", sex);
                ed.apply();
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(intent);
            } else {
                Animation animation;
                if (!error_message_showed) {
                    error_message_showed = true;
                    error_cardview.setVisibility(View.VISIBLE);
                    animation = AnimationUtils.loadAnimation(this, R.anim.show_error_message);
                } else {
                    error_cardview.setVisibility(View.VISIBLE);
                    animation = AnimationUtils.loadAnimation(this, R.anim.hide_error_message);
                    animation.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.show_error_message);
                            error_cardview.startAnimation(animation);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }

                    });
                }
                error_cardview.startAnimation(animation);
            }
        } else {
            Toast.makeText(this, "Для работы приложения необходим доступ к контактам", Toast.LENGTH_SHORT).show();
        }
    }

    public void errorMessageClicked(View view) {
        error_message_showed = false;
        error_cardview.setVisibility(View.INVISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_error_message);
        error_cardview.startAnimation(animation);
    }

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

    private boolean checkActiveAccount() {
        SharedPreferences saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
        String recent_login = saved_data.getString("LGN", "null");
        if (!recent_login.equals("null")) {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (login_card_showed) {
            login_bar.setVisibility(View.INVISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_login_bar);
            login_bar.startAnimation(animation);
            login_card_showed = false;
        } else {
            super.onBackPressed();
        }
    }

}