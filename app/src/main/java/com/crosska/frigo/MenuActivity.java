package com.crosska.frigo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    private SharedPreferences saved_data;
    private String Username;
    private String Name;
    private TextView username_textview;
    private MaterialCardView maincard_human;
    private MaterialCardView secondcard_human;
    private ImageView user_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_menu);
        username_textview = findViewById(R.id.username_textview);
        maincard_human = findViewById(R.id.menu_maincard_human);
        secondcard_human = findViewById(R.id.menu_secondcard_human);
        user_img = findViewById(R.id.user_imageview);
        checkRecentAccount();
        if (Name.isEmpty()) {
            username_textview.setText(Username);
        } else {
            username_textview.setText(Name);
        }
        saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
        int sex = saved_data.getInt("SEX", -1);
        switch (sex) {
            case 0:
                int id = getResources().getIdentifier("com.crosska.frigo:drawable/ic_user_man", null, null);
                user_img.setImageResource(id);
                break;
            case 1:
                id = getResources().getIdentifier("com.crosska.frigo:drawable/ic_user_woman", null, null);
                user_img.setImageResource(id);
                break;
            case 2:
                id = getResources().getIdentifier("com.crosska.frigo:drawable/ic_user_alien", null, null);
                user_img.setImageResource(id);
                break;
            default:
                Toast.makeText(this, "Ошибка получения данных из SharedPreference", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        maincard_human.setRadius((float) maincard_human.getWidth() / 2);
        secondcard_human.setRadius((float) secondcard_human.getWidth() / 2);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }

    private void checkRecentAccount() {
        saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
        Username = saved_data.getString("LGN", "null");
        Name = saved_data.getString("NAME", "null");
    }

    public void logoutButtonClicked(View view) {
        this.finish();
        saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
        SharedPreferences.Editor ed = saved_data.edit();
        ed.putString("LGN", "null");
        ed.apply();
        ed.putString("PSD", "null");
        ed.apply();
        ed.putString("NAME", "null");
        ed.apply();
        ed.putInt("SEX", -1);
        ed.apply();
    }

    public void settingsButtonClicked(View view) {
    }

}