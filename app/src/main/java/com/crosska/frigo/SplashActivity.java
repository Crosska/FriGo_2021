package com.crosska.frigo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private MaterialCardView icon_card;
    private boolean animation_start = true;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("user_data", MODE_PRIVATE);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        icon_card = findViewById(R.id.loading_activity_app_cardview);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_loading_activity_icon_app);
        icon_card.startAnimation(animation);
        if (!checkActiveAccount()) {
            if (sp.getBoolean("DBL", false)) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                SQLiteDatabase DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
                String SQLQuery = "CREATE TABLE IF NOT EXISTS Users (" +
                        "Login TEXT PRIMARY KEY, " +
                        "Pass TEXT, " +
                        "Name TEXT , " +
                        "Sex INTEGER )";
                DataBase.execSQL(SQLQuery);
                SQLQuery = "CREATE TABLE IF NOT EXISTS Fridge (" +
                        "ID_FOOD INTEGER PRIMARY KEY AUTOINCREMENT , " +
                        "Login TEXT, " +
                        "ID_PRODUCT INTEGER, " +
                        "Count INTEGER, " +
                        "ShelfData DATETIME, " +
                        "AddInfo TEXT, " +
                        "FOREIGN KEY(Login) REFERENCES users(Login) )";
                DataBase.execSQL(SQLQuery);
                SQLQuery = "CREATE TABLE IF NOT EXISTS Freezer (" +
                        "ID_FOOD INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Login TEXT, " +
                        "ID_PRODUCT INTEGER, " +
                        "Count INTEGER, " +
                        "AddInfo TEXT, " +
                        "FOREIGN KEY(Login) REFERENCES users(Login) )";
                DataBase.execSQL(SQLQuery);
                SQLQuery = "CREATE TABLE IF NOT EXISTS Dict_product (" +
                        "ID_PRODUCT INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Type TEXT, " +
                        "Name TEXT, " +
                        "FOREIGN KEY(ID_PRODUCT) REFERENCES fridge(ID_PRODUCT), " +
                        "FOREIGN KEY(ID_PRODUCT) REFERENCES freezer(ID_PRODUCT) )";
                DataBase.execSQL(SQLQuery);
                DataBase.close();

                insertDataProduct();

                SharedPreferences saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
                SharedPreferences.Editor ed = saved_data.edit();
                ed.putBoolean("DBL", true);
                ed.apply();

                /*DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
                SQLQuery = "SELECT (name) FROM dict_product_name";
                Cursor query = DataBase.rawQuery(SQLQuery, null);
                String login = "";
                if (query.moveToFirst()) {
                    login = query.getString(0);
                }
                query.close();
                DataBase.close();*/

                //Toast.makeText(this, login, Toast.LENGTH_LONG).show();

                Random rand = new Random();
                int delay = rand.nextInt(2500);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SplashActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }, delay);
            }
        }
    }

    private boolean checkActiveAccount() {
        SharedPreferences saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
        String recent_login = saved_data.getString("LGN", "null");
        if (!recent_login.equals("null")) {
            Random rand = new Random();
            int delay = rand.nextInt(1000);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    SplashActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }, delay);
            return true;
        }
        return false;
    }

    private void insertDataProduct() {
        try {
            AssetManager assetManager = this.getAssets();
            InputStreamReader istream = new InputStreamReader(assetManager.open("products_names.txt"));
            BufferedReader in = new BufferedReader(istream);
            String name, type;
            SQLiteDatabase DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
            String SQLQuery;
            while ((type = in.readLine()) != null) {
                name = in.readLine();
                SQLQuery = "INSERT INTO Dict_product (Type, Name) VALUES ('" + type + "', '" + name + "') ";
                DataBase.execSQL(SQLQuery);
            }
            in.close();
            DataBase.close();
        } catch (IOException e) {
            Toast.makeText(this, "Критическая ошибка!\nНе найдены необходимые системные файлы", Toast.LENGTH_LONG).show();
            this.finishAffinity();
        }
    }
}