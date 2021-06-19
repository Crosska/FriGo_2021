package com.crosska.frigo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    ImageView settings_img;
    ImageView account_img;
    ImageView info_img;
    MaterialCardView settings_card;
    MaterialCardView account_card;
    MaterialCardView info_card;
    MaterialCardView settings_page;
    MaterialCardView account_page;
    MaterialCardView info_page;
    MaterialCardView current_page;

    boolean settings = true;
    boolean account = false;
    boolean info = false;
    boolean erase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        settings_img = findViewById(R.id.settings_app_image);
        account_img = findViewById(R.id.settings_account_image);
        info_img = findViewById(R.id.settings_info_image);
        settings_card = findViewById(R.id.settings_app_button);
        account_card = findViewById(R.id.settings_account_button);
        info_card = findViewById(R.id.settings_info_button);
        settings_page = findViewById(R.id.settings_app_cardview);
        account_page = findViewById(R.id.settings_account_cardview);
        info_page = findViewById(R.id.settings_info_cardview);

        // Установка цвета и иконок кнопок
        settings_card.setCardBackgroundColor(getResources().getColor(R.color.blue_standart));
        settings_img.setImageResource(R.drawable.ic_settings_app_gear);
        account_card.setCardBackgroundColor(Color.WHITE);
        account_img.setImageResource(R.drawable.ic_settings_account_blue);
        info_card.setCardBackgroundColor(Color.WHITE);
        info_img.setImageResource(R.drawable.ic_settings_info_blue);

        // Установка страницы на настройки
        current_page = settings_page;
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_hide_page);
        current_page.setVisibility(View.INVISIBLE);
        current_page.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(SettingsActivity.this, R.anim.anim_settings_activity_show_page);
        settings_page.startAnimation(animation);
        settings_page.setVisibility(View.VISIBLE);

    }

    public void settingsAppButtonClicked(View view) {
        if (!settings) {
            settings = true;
            account = false;
            info = false;

            settings_card.setCardBackgroundColor(getResources().getColor(R.color.blue_standart));
            settings_img.setImageResource(R.drawable.ic_settings_app_gear);
            account_card.setCardBackgroundColor(Color.WHITE);
            account_img.setImageResource(R.drawable.ic_settings_account_blue);
            info_card.setCardBackgroundColor(Color.WHITE);
            info_img.setImageResource(R.drawable.ic_settings_info_blue);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_card_popup);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = AnimationUtils.loadAnimation(SettingsActivity.this, R.anim.anim_settings_activity_card_popdown);
                    settings_card.startAnimation(animation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
            settings_card.startAnimation(animation);

            animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_hide_page);
            current_page.setVisibility(View.INVISIBLE);
            current_page.startAnimation(animation);
            animation = AnimationUtils.loadAnimation(SettingsActivity.this, R.anim.anim_settings_activity_show_page);
            settings_page.startAnimation(animation);
            settings_page.setVisibility(View.VISIBLE);

            current_page = settings_page;

        } else {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_card_shake);
            settings_card.startAnimation(animation);
        }

    }

    public void settingsAccountButtonClicked(View view) {
        if (!account) {
            settings = false;
            account = true;
            info = false;

            settings_card.setCardBackgroundColor(Color.WHITE);
            settings_img.setImageResource(R.drawable.ic_settings_app_gear_blue);
            account_card.setCardBackgroundColor(getResources().getColor(R.color.blue_standart));
            account_img.setImageResource(R.drawable.ic_settings_account);
            info_card.setCardBackgroundColor(Color.WHITE);
            info_img.setImageResource(R.drawable.ic_settings_info_blue);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_card_popup);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = AnimationUtils.loadAnimation(SettingsActivity.this, R.anim.anim_settings_activity_card_popdown);
                    account_card.startAnimation(animation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
            account_card.startAnimation(animation);

            animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_hide_page);
            current_page.setVisibility(View.INVISIBLE);
            current_page.startAnimation(animation);
            animation = AnimationUtils.loadAnimation(SettingsActivity.this, R.anim.anim_settings_activity_show_page);
            account_page.startAnimation(animation);
            account_page.setVisibility(View.VISIBLE);

            current_page = account_page;

        } else {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_card_shake);
            account_card.startAnimation(animation);
        }

    }

    public void settingsInfoButtonClicked(View view) {
        if (!info) {
            settings = false;
            account = false;
            info = true;

            settings_card.setCardBackgroundColor(Color.WHITE);
            settings_img.setImageResource(R.drawable.ic_settings_app_gear_blue);
            account_card.setCardBackgroundColor(Color.WHITE);
            account_img.setImageResource(R.drawable.ic_settings_account_blue);
            info_card.setCardBackgroundColor(getResources().getColor(R.color.blue_standart));
            info_img.setImageResource(R.drawable.ic_settings_info);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_card_popup);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = AnimationUtils.loadAnimation(SettingsActivity.this, R.anim.anim_settings_activity_card_popdown);
                    info_card.startAnimation(animation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
            info_card.startAnimation(animation);

            animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_hide_page);
            current_page.setVisibility(View.INVISIBLE);
            current_page.startAnimation(animation);
            animation = AnimationUtils.loadAnimation(SettingsActivity.this, R.anim.anim_settings_activity_show_page);
            info_page.startAnimation(animation);
            info_page.setVisibility(View.VISIBLE);

            current_page = info_page;

        } else {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_card_shake);
            info_card.startAnimation(animation);
        }
    }

    public void eraseAllButtonClicked(View view) {
        if (!erase) {
            Toast.makeText(this, "Данная кнопка сбросит приложение до заводских настроек. Нажмите еще раз если хотите это сделать.", Toast.LENGTH_LONG).show();
            erase = true;
        } else {
            this.deleteDatabase("data.db");
            SharedPreferences saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
            SharedPreferences.Editor ed = saved_data.edit();
            ed.putBoolean("ACCOUNT_LOGGED", false);
            ed.putBoolean("DATABASE_LOADED", false);
            ed.apply();
            this.finishAffinity();
        }
    }

    public void sendDevReportButtonClicked(View view) {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");
        // Кому
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[]{"musical.vg@gmail.com"});
        // Зачем
        SharedPreferences saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
        String user = saved_data.getString("LOGIN", "");
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Отчёт по приложению FriGo" + user);
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Отчёт:\n");
        // С чем
        /*emailIntent.putExtra(
                android.content.Intent.EXTRA_STREAM,
                Uri.parse("file://"
                        + Environment.getExternalStorageDirectory()
                        + "/Клипы/SOTY_ATHD.mp4"));

        emailIntent.setType("text/video");*/
        // Поехали!
        SettingsActivity.this.startActivity(Intent.createChooser(emailIntent,
                "Отправка письма..."));
    }
}
