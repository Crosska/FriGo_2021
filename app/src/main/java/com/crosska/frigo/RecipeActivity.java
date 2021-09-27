package com.crosska.frigo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Objects;

public class RecipeActivity extends AppCompatActivity {

    private final ArrayList<RecipeRecyclerState> products = new ArrayList<>();
    MaterialCardView web_button;
    MaterialCardView product_button;
    MaterialCardView web_card;
    MaterialCardView product_card;
    ImageView web_image;
    ImageView product_image;

    Boolean card_type = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        web_button = findViewById(R.id.recipes_web_button);
        product_button = findViewById(R.id.recipes_product_button);
        web_card = findViewById(R.id.recipes_web_card);
        product_card = findViewById(R.id.recipes_product_card);
        web_image = findViewById(R.id.recipes_web_image);
        product_image = findViewById(R.id.recipes_product_image);

        fullProductsList();
        RecyclerView recyclerView = findViewById(R.id.recyclerview_products);
        RecipeRecyclerStateAdapter adapter = new RecipeRecyclerStateAdapter(this, products);
        recyclerView.setAdapter(adapter);

        card_type = false;
        web_button.setCardBackgroundColor(getResources().getColor(R.color.blue_standart));
        web_image.setImageResource(R.drawable.ic_recipes_web);
        product_button.setCardBackgroundColor(Color.WHITE);
        product_image.setImageResource(R.drawable.ic_recipes_checklist_blue);
    }

    public void fullProductsList() {
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_fridge, "Говядина"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_fridge, "Каша пшеничная"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_freezer, "Мороженное пломбир"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_fridge, "Спаржа"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_freezer, "Молоко"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_freezer, "Морковка"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_freezer, "Капуста"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_fridge, "Кефир"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_fridge, "Яйца"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_fridge, "Сыр"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_fridge, "Соевый соус"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_fridge, "Майонез"));
        products.add(new RecipeRecyclerState(R.drawable.ic_thermometer_fridge, "Мёд"));
    }

    public void webRecipesButtonClicked(View view) {
        if (card_type) {
            card_type = false;
            web_button.setCardBackgroundColor(getResources().getColor(R.color.blue_standart));
            web_image.setImageResource(R.drawable.ic_recipes_web);
            product_button.setCardBackgroundColor(Color.WHITE);
            product_image.setImageResource(R.drawable.ic_recipes_checklist_blue);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_card_popup);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = AnimationUtils.loadAnimation(RecipeActivity.this, R.anim.anim_settings_activity_card_popdown);
                    web_card.startAnimation(animation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
            web_card.startAnimation(animation);

            animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_hide_page);
            product_card.setVisibility(View.INVISIBLE);
            product_card.startAnimation(animation);
            animation = AnimationUtils.loadAnimation(RecipeActivity.this, R.anim.anim_settings_activity_show_page);
            web_card.startAnimation(animation);
            web_card.setVisibility(View.VISIBLE);

        } else {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_card_shake);
            web_button.startAnimation(animation);
        }
    }

    public void listProductsButtonClicked(View view) {
        if (!card_type) {
            card_type = true;

            product_button.setCardBackgroundColor(getResources().getColor(R.color.blue_standart));
            product_image.setImageResource(R.drawable.ic_recipes_checklist);
            web_button.setCardBackgroundColor(Color.WHITE);
            web_image.setImageResource(R.drawable.ic_recipes_web_blue);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_card_popup);
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = AnimationUtils.loadAnimation(RecipeActivity.this, R.anim.anim_settings_activity_card_popdown);
                    product_card.startAnimation(animation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

            });
            product_card.startAnimation(animation);

            animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_hide_page);
            web_card.setVisibility(View.INVISIBLE);
            web_card.startAnimation(animation);
            animation = AnimationUtils.loadAnimation(RecipeActivity.this, R.anim.anim_settings_activity_show_page);
            product_card.startAnimation(animation);
            product_card.setVisibility(View.VISIBLE);

        } else {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_settings_activity_card_shake);
            product_button.startAnimation(animation);
        }
    }

    public void website1RecipeButtonClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.povar.ru"));
        Intent chooser = Intent.createChooser(intent, "Открыть сайт с помощью...");
        startActivity(chooser);
    }

    public void website2RecipeButtonClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.edimdoma.ru"));
        Intent chooser = Intent.createChooser(intent, "Открыть сайт с помощью...");
        startActivity(chooser);
    }

    public void website3RecipeButtonClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.ovkuse.ru"));
        Intent chooser = Intent.createChooser(intent, "Открыть сайт с помощью...");
        startActivity(chooser);
    }

    public void website4RecipeButtonClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.gastronom.ru"));
        Intent chooser = Intent.createChooser(intent, "Открыть сайт с помощью...");
        startActivity(chooser);
    }

    public void website5RecipeButtonClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.kedem.ru"));
        Intent chooser = Intent.createChooser(intent, "Открыть сайт с помощью...");
        startActivity(chooser);
    }
}