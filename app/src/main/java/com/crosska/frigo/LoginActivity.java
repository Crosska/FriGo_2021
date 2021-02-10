package com.crosska.frigo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {

    private MaterialCardView login_bar;
    private EditText login_edit_text;
    private EditText password_edit_text;
    private CardView error_cardview;
    private SharedPreferences saved_data;
    private boolean login_card_showed = false;
    private boolean error_message_showed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        login_bar = findViewById(R.id.login_edit_text_group);
        login_edit_text = findViewById(R.id.login_activity_login_text_edit);
        password_edit_text = findViewById(R.id.login_activity_password_text_edit);
        error_cardview = findViewById(R.id.login_activity_error_card);
        AnimateBackground();
    }

    private void AnimateBackground() {
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

    @Override
    protected void onResume() {
        super.onResume();

        saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
        if (saved_data.getBoolean("ACCOUNT_LOGGED", false)) {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
        }

        login_edit_text.setText("");
        password_edit_text.setText("");

    }

    /*private void getPermissions() {
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
    }*/

    private void startNewAnimation(ImageView food_icon) {
        Random rand = new Random();
        int delay = rand.nextInt(4000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                LoginActivity.this.runOnUiThread(() -> moveFoodIcon(food_icon));
            }
        }, delay);
    }

    public void registerButtonClicked(View view) {
        //if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        //        == PackageManager.PERMISSION_GRANTED) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
        //} else {
        //    Toast.makeText(this, R.string.error_app_rights, Toast.LENGTH_SHORT).show();
        //}
    }

    public void loginBarButtonClicked(View view) {
        login_bar.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_login_activity_show_login_card);
        login_bar.startAnimation(animation);
        login_card_showed = true;
    }

    public void backgroundClicked(View view) {
        if (login_card_showed) {
            login_bar.setVisibility(View.INVISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_hide_smooth_alpha);
            login_bar.startAnimation(animation);
            login_card_showed = false;
        }
    }

    public void mainIconClicked(View view) {
        Toast.makeText(getApplicationContext(), "FriGo by Crosska (c)", Toast.LENGTH_SHORT).show();
    }

    public void loginButtonClicked(View view) {
        // if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        //         == PackageManager.PERMISSION_GRANTED) {

        String login_sql = login_edit_text.getText().toString();
        String password_sql = password_edit_text.getText().toString();
        String login = "null";
        String password = "null";
        String name = "null";
        int sex = -1;

        SQLiteDatabase DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
        String SQLQuery = "SELECT Login, Pass, Name, Sex FROM Users WHERE (Login = '" + login_sql + "' AND Pass = '" + password_sql + "')";
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
            ed.putString("LOGIN", login);
            ed.apply();
            ed.putString("NAME", name);
            ed.apply();
            ed.putInt("SEX", sex);
            ed.apply();
            ed.putBoolean("ACCOUNT_LOGGED", true);
            ed.apply();
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {
            Animation animation;
            if (!error_message_showed) {
                error_message_showed = true;
                error_cardview.setVisibility(View.VISIBLE);
                animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_show_smooth_alpha);
            } else {
                error_cardview.setVisibility(View.VISIBLE);
                animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_hide_smooth_alpha);
                animation.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.anim_global_show_smooth_alpha);
                        error_cardview.startAnimation(animation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                });
            }
            error_cardview.startAnimation(animation);
        }
        //} else {
        //    Toast.makeText(this, "Для работы приложения необходим доступ к контактам", Toast.LENGTH_SHORT).show();
        //}
    }

    public void errorMessageClicked(View view) {
        error_message_showed = false;
        error_cardview.setVisibility(View.INVISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_hide_smooth_alpha);
        error_cardview.startAnimation(animation);
    }

    private void moveFoodIcon(ImageView food_icon) {
        food_icon.setVisibility(View.VISIBLE);
        Animation animation_move = AnimationUtils.loadAnimation(this, R.anim.anim_login_activity_food_moving);
        food_icon.startAnimation(animation_move);
    }

    @Override
    public void onBackPressed() {
        if (login_card_showed) {
            login_bar.setVisibility(View.INVISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_login_activity_hide_login_card);
            login_bar.startAnimation(animation);
            login_card_showed = false;
        } else {
            super.onBackPressed();
            this.finishAffinity();
        }
    }

}