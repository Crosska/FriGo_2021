package com.crosska.frigo;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ShelfLifeActivity extends AppCompatActivity {

    private final ArrayList<ShelfLifeState> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_life);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fullProductsList();

        RecyclerView recyclerView = findViewById(R.id.shelf_life_recyclerview);
        ShelfLifeStateAdapter adapter = new ShelfLifeStateAdapter(this, products);
        recyclerView.setAdapter(adapter);
    }

    public void fullProductsList() {
        products.add(new ShelfLifeState("Молоко", "Кисломолочное", "12.07.2021", "7 дней"));
        products.add(new ShelfLifeState("Творог", "Кисломолочное", "16.07.2021", "11 дней"));
        products.add(new ShelfLifeState("Яйца куриные", "Другое", "11.07.2021", "6 дней"));
        products.add(new ShelfLifeState("Огурцы", "Овощи", "12.08.2021", "37 дней"));
        products.add(new ShelfLifeState("Мясо говядина", "Мясное", "01.08.2021", "26 дней"));
    }

}