package com.crosska.frigo;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class GroceryActivity extends AppCompatActivity {

    private EditText search_edittext;
    private final ArrayList<State> states = new ArrayList<State>();
    private Button fridge_button;
    private Button freezer_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_fridge);
        StateAdapter adapter = new StateAdapter(this, states);  // Создаем адаптер
        recyclerView.setAdapter(adapter); // Устанавливаем для списка адаптер
        search_edittext = findViewById(R.id.grocery_activity_search_textedit);
        fridge_button = findViewById(R.id.grocery_activity_fridge_button);
        freezer_button = findViewById(R.id.grocery_activity_freezer_button);
    }

    private void setInitialData() {
        for (int i = 0; i < 10; i++) {
            states.add(new State(R.drawable.ic_bread_basket, "Молоко"));
            states.add(new State(R.drawable.ic_bread_basket, "Хлеб"));
            states.add(new State(R.drawable.ic_bread_basket, "Сыр"));
            states.add(new State(R.drawable.ic_bread_basket, "Сосиски"));
            states.add(new State(R.drawable.ic_bread_basket, "Колбаса"));
            states.add(new State(R.drawable.ic_bread_basket, "Вода"));
            states.add(new State(R.drawable.ic_bread_basket, "Кетчуп"));
            states.add(new State(R.drawable.ic_bread_basket, "Майонез"));
            states.add(new State(R.drawable.ic_bread_basket, "Горчица"));
            states.add(new State(R.drawable.ic_bread_basket, "Жареная курица"));
            states.add(new State(R.drawable.ic_bread_basket, "Курага"));
            states.add(new State(R.drawable.ic_bread_basket, "Шоколадка"));
        }
    }

    public void fridgeContentClicked(View view) {
        freezer_button.setBackgroundColor(getResources().getColor(R.color.blue_light));
        fridge_button.setBackgroundColor(getResources().getColor(R.color.blue_standart));
    }

    public void freezerContentClicked(View view) {
        freezer_button.setBackgroundColor(getResources().getColor(R.color.blue_standart));
        fridge_button.setBackgroundColor(getResources().getColor(R.color.blue_light));
    }

    public void eraseSearchClicked(View view) {
        search_edittext.setText("");
    }
}
