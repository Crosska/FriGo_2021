package com.crosska.frigo;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
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
    private EditText password_repeat_textfield;
    private EditText name_textfield;
    private CardView error_cardview;
    private TextView error_textview;
    private MaterialCardView maincard_human;
    private MaterialCardView secondcard_human;
    private ImageView password_show_image;
    private ImageView password_repeat_show_image;
    private boolean error_message_showed = false;
    private boolean password_show = false;
    private boolean password_repeat_show = false;

    /*
    (?=.*[0-9]) цифра должна появляться по крайней мере один раз
    (?=.*[a-z]) строчная буква должна появляться как минимум раз
    (?=.*[a-z]) письмо с верхним регистром должно происходить по крайней мере один раз
    (?=.*[@#$%^&+=]) специальный символ должен появляться по крайней мере один раз
    (?=\\S+$) пробелы не разрешены во всей строке
    .{8,} не менее 8 символов
    */

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
        password_repeat_textfield = findViewById(R.id.registration_activity_password_repeat_textfield);
        name_textfield = findViewById(R.id.name_textfield);
        error_cardview = findViewById(R.id.error_message_cardview);
        error_textview = findViewById(R.id.error_message_textview);
        scrollview = findViewById(R.id.scroll_main);
        maincard_human = findViewById(R.id.registry_maincard_human);
        secondcard_human = findViewById(R.id.registry_secondcard_human);
        password_show_image = findViewById(R.id.password_eye);
        password_repeat_show_image = findViewById(R.id.password_repeat_eye);
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
        if (!login_textfield.getText().toString().isEmpty() && !password_textfield.getText().toString().isEmpty() && !password_textfield.getText().toString().isEmpty()) { // Проверка на пустые поля

            if (checkExistingAccount()) {

                String pattern_login = "(?=\\S+$).{3,}";
                if (login_textfield.getText().toString().matches(pattern_login)) {

                    String pattern_final_password = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
                    if (password_textfield.getText().toString().matches(pattern_final_password)) {

                        if (password_textfield.getText().toString().equals(password_repeat_textfield.getText().toString())) {
                            String login_sql = login_textfield.getText().toString();
                            String password_sql = password_textfield.getText().toString();
                            String name_sql = name_textfield.getText().toString();

                            SQLiteDatabase DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
                            String SQLQuery = "CREATE TABLE IF NOT EXISTS users (ID_USER INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, pass TEXT, name TEXT, sex INTEGER)";
                            DataBase.execSQL(SQLQuery);
                            SQLQuery = "INSERT INTO users (login, pass, name, sex) VALUES ('" + login_sql + "', '" + password_sql + "', '" + name_sql + "', " + getSex() + ") ";
                            DataBase.execSQL(SQLQuery);
                            DataBase.close();
                            saveAccount();
                            this.finish();
                            if (error_message_showed) {
                                Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_hide_error_card);
                                error_cardview.startAnimation(animation);
                                error_cardview.setVisibility(View.INVISIBLE);
                            }
                        } else {
                            showNotMatchSymbolErrorMessage();
                            scrollview.smoothScrollTo(0, error_cardview.getBottom());
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
                showExistErrorMessage();
                scrollview.smoothScrollTo(0, error_cardview.getBottom());
            }

        } else {
            showEmptyErrorMessage();
            scrollview.smoothScrollTo(0, error_cardview.getBottom());
        }
    }

    private boolean checkExistingAccount() {
        String login_sql = login_textfield.getText().toString();
        SQLiteDatabase DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
        String SQLQuery = "CREATE TABLE IF NOT EXISTS users (ID_USER INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, pass TEXT, name TEXT , sex INTEGER)";
        DataBase.execSQL(SQLQuery);
        SQLQuery = "SELECT * FROM users WHERE (login = '" + login_sql + "')";
        Cursor query = DataBase.rawQuery(SQLQuery, null);
        if (query.moveToFirst()) {
            query.close();
            DataBase.close();
            return false;
        }
        query.close();
        DataBase.close();
        return true;
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
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_hide_error_card);
        error_cardview.startAnimation(animation);
    }

    private void showExistErrorMessage() {
        Animation animation;
        if (!error_message_showed) {
            error_message_showed = true;
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_show_error_card);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    error_textview.setText(R.string.registration_login_exist_error);
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
            animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_grocery_activity_hide_error_card);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    error_textview.setText(R.string.registration_login_exist_error);
                    animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_show_error_card);
                    error_cardview.startAnimation(animation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

            });
        }
        error_cardview.startAnimation(animation);
    }

    private void showSpecSymbolErrorMessage() {
        Animation animation;
        if (!error_message_showed) {
            error_message_showed = true;
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_show_error_card);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    error_textview.setText(R.string.registration_password_requirements_error);
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
            animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_grocery_activity_hide_error_card);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    error_textview.setText(R.string.registration_password_requirements_error);
                    animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_show_error_card);
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
        Animation animation;
        if (!error_message_showed) {
            error_message_showed = true;
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_show_error_card);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    error_textview.setText(R.string.registration_length_error);
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
            animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_grocery_activity_hide_error_card);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    error_textview.setText(R.string.registration_length_error);
                    animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_show_error_card);
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
        Animation animation;
        if (!error_message_showed) {
            error_message_showed = true;
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_show_error_card);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    error_textview.setText(R.string.registration_empty_error);
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
            animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_grocery_activity_hide_error_card);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_show_error_card);
                    error_cardview.startAnimation(animation);
                    error_textview.setText(R.string.registration_empty_error);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

            });
        }
        error_cardview.startAnimation(animation);
    }

    private void showNotMatchSymbolErrorMessage() {
        Animation animation;
        if (!error_message_showed) {
            error_message_showed = true;
            error_cardview.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_show_error_card);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    error_textview.setText(R.string.registration_repeat_error);
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
            animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_grocery_activity_hide_error_card);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    error_textview.setText(R.string.registration_repeat_error);
                    animation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.anim_show_error_card);
                    error_cardview.startAnimation(animation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

            });
        }
        error_cardview.startAnimation(animation);
    }

    private void saveAccount() {
        SharedPreferences saved_data = getSharedPreferences("user_data", MODE_PRIVATE);
        SharedPreferences.Editor ed = saved_data.edit();
        ed.putString("LGN", login_textfield.getText().toString());
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

    public void showPassword(View view) {
        if (!password_show) {
            password_show = true;
            password_textfield.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            int id = getResources().getIdentifier("com.crosska.frigo:drawable/ic_eye_on", null, null);
            password_show_image.setImageResource(id);
        } else {
            password_show = false;
            password_textfield.setTransformationMethod(PasswordTransformationMethod.getInstance());
            int id = getResources().getIdentifier("com.crosska.frigo:drawable/ic_eye_off", null, null);
            password_show_image.setImageResource(id);
        }
    }

    public void showPasswordRepeat(View view) {
        if (!password_repeat_show) {
            password_repeat_show = true;
            password_repeat_textfield.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            int id = getResources().getIdentifier("com.crosska.frigo:drawable/ic_eye_on", null, null);
            password_repeat_show_image.setImageResource(id);
        } else {
            password_repeat_show = false;
            password_repeat_textfield.setTransformationMethod(PasswordTransformationMethod.getInstance());
            int id = getResources().getIdentifier("com.crosska.frigo:drawable/ic_eye_off", null, null);
            password_repeat_show_image.setImageResource(id);
        }
    }
}
