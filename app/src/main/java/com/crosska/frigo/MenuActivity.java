package com.crosska.frigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private SharedPreferences saved_data;
    private String Username;
    private String Name;
    private TextView username_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_menu);
        username_textview = findViewById(R.id.username_textview);
        checkRecentAccount();
        if(Name.isEmpty()) {
            username_textview.setText(Username);
        } else {
            username_textview.setText(Name);
        }
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

}