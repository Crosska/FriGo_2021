package com.crosska.frigo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class RegistrationActivity extends AppCompatActivity {

    private RadioButton womanRadioButton;
    private RadioButton manRadioButton;
    private RadioButton otherRadioButton;
    private EditText login_textfield;
    private EditText password_textfield;
    private EditText name_textfield;
    private CardView error_cardview;
    private TextView error_textview;
    private boolean error_message_showed = false;
    private SharedPreferences saved_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_registration);
        womanRadioButton = findViewById(R.id.woman_radiobutton);
        manRadioButton = findViewById(R.id.man_radiobutton);
        otherRadioButton = findViewById(R.id.other_radiobutton);
        login_textfield = findViewById(R.id.login_textfield);
        password_textfield = findViewById(R.id.password_textfield);
        name_textfield = findViewById(R.id.name_textfield);
        error_cardview = findViewById(R.id.error_message_cardview);
        error_textview = findViewById(R.id.error_message_textview);
    }

    public void cancelButtonClicked(View view) {
        this.finish();
    }

    public void createButtonClicked(View view) {

        if (!login_textfield.getText().toString().isEmpty() && !password_textfield.getText().toString().isEmpty()) {
            if (login_textfield.getText().toString().length() >= 4 && password_textfield.getText().toString().length() >= 8) {
                if (password_textfield.getText().toString().contains("!") ||
                        password_textfield.getText().toString().contains("@") ||
                        password_textfield.getText().toString().contains("#") ||
                        password_textfield.getText().toString().contains("$") ||
                        password_textfield.getText().toString().contains("%") ||
                        password_textfield.getText().toString().contains("^") ||
                        password_textfield.getText().toString().contains("&") ||
                        password_textfield.getText().toString().contains("*") ||
                        password_textfield.getText().toString().contains("(") ||
                        password_textfield.getText().toString().contains(")") ||
                        password_textfield.getText().toString().contains("?")) {

                    SQLiteDatabase DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
                    DataBase.execSQL("CREATE TABLE IF NOT EXISTS users (login TEXT, password TEXT)");
                    String login_sql = login_textfield.getText().toString();
                    String password_sql = password_textfield.getText().toString();

                    DataBase.execSQL("INSERT INTO users VALUES ( '" + login_sql + "', '" + password_sql + "' ) ");

                    /*Cursor query = DataBase.rawQuery("SELECT * FROM users;", null);
                    if (query.moveToFirst()) {

                        String name = query.getString(0);
                        String password = query.getString(1);
                        //Toast.makeText(this, name + " = " + password, Toast.LENGTH_LONG).show();
                    }
                    query.close();*/
                    DataBase.close();
                    saveAccount();
                    this.finish();


                    if (error_message_showed) {
                        Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_error_message);
                        error_cardview.startAnimation(animation);
                        error_cardview.setVisibility(View.INVISIBLE);
                    }

                } else {
                    showSpecSymbolErrorMessage();
                }
            } else {
                showLengthErrorMessage();
            }
        } else {
            showEmptyErrorMessage();
        }
    }

    private void showSpecSymbolErrorMessage() {
        Context cont = this;
        Animation animation;
        if (!error_message_showed) {
            error_message_showed = true;
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(cont, R.anim.show_error_message);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    error_textview.setText("Ошибка:\n\nПароль должен содержать хотя бы один спецсимвол");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
        } else {
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(cont, R.anim.hide_error_message);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    error_textview.setText("Ошибка:\n\nПароль должен содержать хотя бы один спецсимвол");
                    animation = AnimationUtils.loadAnimation(cont, R.anim.show_error_message);
                    error_cardview.startAnimation(animation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
        }
        error_cardview.startAnimation(animation);
    }

    private void showLengthErrorMessage() {
        Context cont = this;
        Animation animation;
        if (!error_message_showed) {
            error_message_showed = true;
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(cont, R.anim.show_error_message);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    error_textview.setText("Ошибка:\n\nЛогин должен быть длиной минимум 4 символа, а пароль минимум 8 символов");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
        } else {
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(cont, R.anim.hide_error_message);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    error_textview.setText("Ошибка:\n\nЛогин должен быть длиной минимум 4 символа, а пароль минимум 8 символов");
                    animation = AnimationUtils.loadAnimation(cont, R.anim.show_error_message);
                    error_cardview.startAnimation(animation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
        }
        error_cardview.startAnimation(animation);
    }

    private void showEmptyErrorMessage() {
        Context cont = this;
        Animation animation;
        if (!error_message_showed) {
            error_message_showed = true;
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(cont, R.anim.show_error_message);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    error_textview.setText("Ошибка:\n\nВы должны указать логин и пароль");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
        } else {
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(cont, R.anim.hide_error_message);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = AnimationUtils.loadAnimation(cont, R.anim.show_error_message);
                    error_cardview.startAnimation(animation);
                    error_textview.setText("Ошибка:\n\nВы должны указать логин и пароль");
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
        }
        error_cardview.startAnimation(animation);
    }

    public void womanRadioButtonClicked(View view) {
        manRadioButton.setChecked(false);
        otherRadioButton.setChecked(false);
    }

    public void manRadioButtonClicked(View view) {
        womanRadioButton.setChecked(false);
        otherRadioButton.setChecked(false);
    }

    public void otherRadioButtonClicked(View view) {
        manRadioButton.setChecked(false);
        womanRadioButton.setChecked(false);
    }

    public void errorMessageClicked(View view) {
        error_message_showed = false;
        error_cardview.setVisibility(View.INVISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_error_message);
        error_cardview.startAnimation(animation);
    }

    private void saveAccount() {
        saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
        SharedPreferences.Editor ed = saved_data.edit();
        ed.putString("LGN", login_textfield.getText().toString());
        ed.apply();
        ed.putString("PSD", password_textfield.getText().toString());
        ed.apply();
        ed.putString("NAME", name_textfield.getText().toString());
        ed.apply();
        ed.putInt("SEX", getSex());
        ed.apply();
    }

    private int getSex() {
        if (manRadioButton.isChecked()) {
            return 0;
        } else if (womanRadioButton.isChecked()) {
            return 1;
        }
        return 2;
    }

}
