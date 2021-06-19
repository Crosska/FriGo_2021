package com.crosska.frigo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    ImageView settings_img;
    ImageView account_img;
    ImageView info_img;
    ImageView user_logo;
    MaterialCardView settings_card;
    MaterialCardView account_card;
    MaterialCardView info_card;
    MaterialCardView settings_page;
    MaterialCardView account_page;
    MaterialCardView info_page;
    MaterialCardView current_page;
    MaterialCardView logo_cardview;
    MaterialTextView user_login;
    MaterialTextView user_name;
    EditText user_name_new;
    EditText current_pass;
    RadioButton radioButton_man;
    RadioButton radioButton_woman;
    RadioButton radioButton_other;

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
        logo_cardview = findViewById(R.id.settings_activity_app_cardview);
        user_logo = findViewById(R.id.settings_account_user_logo);
        user_login = findViewById(R.id.settings_account_user_login);
        user_name_new = findViewById(R.id.settings_account_new_user_name);
        current_pass = findViewById(R.id.settings_account_user_current_pass);
        radioButton_man = findViewById(R.id.settings_account_user_sex_0);
        radioButton_woman = findViewById(R.id.settings_account_user_sex_1);
        radioButton_other = findViewById(R.id.settings_account_user_sex_2);
        user_name = findViewById(R.id.settings_account_user_name);


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
        animation = AnimationUtils.loadAnimation(SettingsActivity.this, R.anim.anim_settings_activity_logo_rotate);
        logo_cardview.startAnimation(animation);

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

            SharedPreferences saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
            switch (saved_data.getInt("SEX", 0)) {
                case 0:
                    user_logo.setImageResource(R.drawable.ic_user_man);
                    radioButton_man.setChecked(true);
                    break;
                case 1:
                    user_logo.setImageResource(R.drawable.ic_user_woman);
                    radioButton_woman.setChecked(true);
                    break;
                case 2:
                    user_logo.setImageResource(R.drawable.ic_user_alien);
                    radioButton_other.setChecked(true);
                    break;
                default:
                    break;
            }
            user_login.setText(saved_data.getString("LOGIN", "null"));
            user_name.setText(saved_data.getString("NAME", "null"));

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
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Отчёт по приложению FriGo");
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Пользователь:" + user + "\nОписание отчёта:\n");
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

    public void applyNewDataButtonClicked(View view) {
        String pass = "null";
        SQLiteDatabase DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
        String SQLQuery = "SELECT Pass FROM Users WHERE (Login = '" + user_login.getText().toString() + "')";
        Cursor query = DataBase.rawQuery(SQLQuery, null);
        if (query.moveToFirst()) {
            pass = query.getString(0);
        }
        query.close();
        DataBase.close();
        if (current_pass.getText().toString().equals(pass)) {
            int new_sex;
            if (radioButton_man.isChecked()) {
                new_sex = 0;
            } else if (radioButton_woman.isChecked()) {
                new_sex = 1;
            } else {
                new_sex = 2;
            }
            String new_name = user_name_new.getText().toString();
            DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
            SQLQuery = "UPDATE Users SET Name = '" + new_name + "', Sex = " + new_sex + " WHERE Login = '" + user_login + "';";
            DataBase.execSQL(SQLQuery);
            DataBase.close();

            SharedPreferences saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
            SharedPreferences.Editor ed = saved_data.edit();
            ed.putString("NAME", new_name);
            ed.apply();
            ed.putInt("SEX", new_sex);
            ed.apply();

            Toast.makeText(this, "Данные аккаунта успешно обновлены", Toast.LENGTH_LONG).show();
            this.finish();

        } else {
            Toast.makeText(this, "Вы ввели неправильный пароль", Toast.LENGTH_SHORT).show();
        }
    }
}
