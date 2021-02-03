package com.crosska.frigo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    private ScrollView scrollview;
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
    private final String pattern_final_password = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=/.,!?<>{}*])(?=\\S+$).{8,}";
    private final String pattern_login = "(?=\\S+$).{4,}";
    private final String pattern_password = "(?=\\S+$).{8,}";
    private MaterialCardView maincard_human;
    private MaterialCardView secondcard_human;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_registration);
        womanRadioButton = findViewById(R.id.woman_radiobutton);
        manRadioButton = findViewById(R.id.man_radiobutton);
        otherRadioButton = findViewById(R.id.other_radiobutton);
        login_textfield = findViewById(R.id.registration_activity_login_textfield);
        password_textfield = findViewById(R.id.registration_activity_password_textfield);
        name_textfield = findViewById(R.id.name_textfield);
        error_cardview = findViewById(R.id.error_message_cardview);
        error_textview = findViewById(R.id.error_message_textview);
        scrollview = findViewById(R.id.scroll_main);
        maincard_human = findViewById(R.id.registry_maincard_human);
        secondcard_human = findViewById(R.id.registry_secondcard_human);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        maincard_human.setRadius((float) maincard_human.getWidth() / 2);
        secondcard_human.setRadius((float) secondcard_human.getWidth() / 2);
    }

    public void cancelButtonClicked(View view) {
        this.finish();
    }

    public void createButtonClicked(View view) {

        if (!login_textfield.getText().toString().isEmpty() && !password_textfield.getText().toString().isEmpty()) {
            if (login_textfield.getText().toString().matches(pattern_login) && password_textfield.getText().toString().matches(pattern_password)) {

                /*
                (?=.*[0-9]) цифра должна появляться по крайней мере один раз
                (?=.*[a-z]) строчная буква должна появляться как минимум раз
                (?=.*[a-z]) письмо с верхним регистром должно происходить по крайней мере один раз
                (?=.*[@#$%^&+=]) специальный символ должен появляться по крайней мере один раз
                (?=\\S+$) пробелы не разрешены во всей строке
                .{8,} не менее 8 символов
                */

                if (password_textfield.getText().toString().matches(pattern_final_password)) {
                    SQLiteDatabase DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
                    DataBase.execSQL("CREATE TABLE IF NOT EXISTS users (login TEXT, pass TEXT, name TEXT, sex INTEGER)");
                    String login_sql = login_textfield.getText().toString();
                    String password_sql = password_textfield.getText().toString();
                    String name_sql = name_textfield.getText().toString();
                    String sql = "INSERT INTO users VALUES ( '" + login_sql + "', '" + password_sql + "', '" + name_sql + "', " + getSex() + ") ";
                    DataBase.execSQL(sql);
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
                    scrollview.smoothScrollTo(0, error_cardview.getBottom());
                }
            } else {
                showLengthErrorMessage();
                scrollview.smoothScrollTo(0, error_cardview.getBottom());
            }
        } else {
            showEmptyErrorMessage();
            scrollview.smoothScrollTo(0, error_cardview.getBottom());

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

                @SuppressLint("SetTextI18n")
                @Override
                public void onAnimationStart(Animation animation) {
                    error_textview.setText("Ошибка:\n\nПароль должен подходить под требования: " +
                            "\nДлинной не менее 8 символов" +
                            "\nХотя бы одна цифра" +
                            "\nХотя бы одна прописная буква" +
                            "\nХотя бы одна строчная буква" +
                            "\nХотя бы один спецсимвол" +
                            "\nНе содержать пробелы\n" +
                            "\nПример \"Pa$$w0rd\"");
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

                @SuppressLint("SetTextI18n")
                @Override
                public void onAnimationEnd(Animation animation) {
                    error_textview.setText("Ошибка:\n\nПароль должен подходить под требования: \n" +
                            "\nДлинной не менее 8 символов" +
                            "\nХотя бы одна цифра" +
                            "\nХотя бы одна прописная буква" +
                            "\nХотя бы одна строчная буква" +
                            "\nХотя бы один спецсимвол" +
                            "\nНе содержать пробелы\n" +
                            "\nПример \"Pa$$w0rd\"");
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
