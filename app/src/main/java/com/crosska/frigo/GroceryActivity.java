package com.crosska.frigo;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class GroceryActivity extends AppCompatActivity {

    private EditText search_edittext;
    private final ArrayList<GroceryRecyclerState> states = new ArrayList<GroceryRecyclerState>();
    private Button fridge_button;
    private Button freezer_button;
    private Button cancel_new_product_button;
    private Button add_new_product_button;
    private MaterialCardView fridge_list_card;
    private MaterialCardView freezer_list_card;
    private MaterialCardView add_card;
    private MaterialCardView top_card;
    private FloatingActionButton f_button;
    private RadioGroup radio_group;
    private Spinner product_type_spinner;
    private boolean card_type_showed = true;
    private Typeface myFont;
    private EditText type_edittext;
    private EditText product_custom_name_edittext;
    private EditText product_exist_name_edittext;
    private final String[] countries = {
            "Жиры",
            "Грибы",
            "Кондитерские",
            "Консервы",
            "Крупы",
            "Молочные",
            "Морепродукты",
            "Мясопродукты",
            "Напитки",
            "Овощи",
            "Соусы",
            "Фрукты",
            "Хлебобулочные",
            "Яйца"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.recyclerview_fridge);
        GroceryRecyclerStateAdapter adapter = new GroceryRecyclerStateAdapter(this, states);
        recyclerView.setAdapter(adapter);

        search_edittext = findViewById(R.id.grocery_activity_search_textedit);
        fridge_button = findViewById(R.id.grocery_activity_fridge_button);
        freezer_button = findViewById(R.id.grocery_activity_freezer_button);
        fridge_list_card = findViewById(R.id.grocery_activity_fridge_card);
        freezer_list_card = findViewById(R.id.grocery_activity_freezer_card);
        add_card = findViewById(R.id.grocery_activity_add_card);
        top_card = findViewById(R.id.grocery_activity_top_card);
        f_button = findViewById(R.id.floatingActionButton);
        radio_group = findViewById(R.id.grocery_activity_radio_group);
        product_type_spinner = findViewById(R.id.grocery_activity_spinner);
        type_edittext = findViewById(R.id.grocery_activity_type_edittext);
        add_new_product_button = findViewById(R.id.grocery_activity_add_button);
        cancel_new_product_button = findViewById(R.id.grocery_activity_cancel_button);
        product_custom_name_edittext = findViewById(R.id.grocery_activity_custom_product_name_edittext);
        product_exist_name_edittext = findViewById(R.id.grocery_activity_exist_product_name_edittext);

        myFont = Typeface.createFromAsset(getAssets(), "comfortaa_light.ttf");
        MyArrayAdapter ma = new MyArrayAdapter(this);
        product_type_spinner.setAdapter(ma);
    }

    private void setInitialData() {
        for (int i = 0; i < 2; i++) {
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Молоко"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Хлеб"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Сыр"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Сосиски"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Колбаса"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Вода"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Кетчуп"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Майонез"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Горчица"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Жареная курица"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Курага"));
            states.add(new GroceryRecyclerState(R.drawable.ic_bread_basket, "Шоколадка"));
        }
    }

    public void fridgeContentClicked(View view) {
        if (!card_type_showed) {
            freezer_list_card.setEnabled(false);
            fridge_list_card.setEnabled(false);
            card_type_showed = true;
            freezer_button.setBackgroundColor(getResources().getColor(R.color.blue_light));
            fridge_button.setBackgroundColor(getResources().getColor(R.color.blue_standart));
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_hide_freezer_card);
            freezer_list_card.setVisibility(View.INVISIBLE);
            freezer_list_card.startAnimation(animation);
            animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_show_fridge_card);
            fridge_list_card.startAnimation(animation);
            fridge_list_card.setVisibility(View.VISIBLE);
        }
    }

    public void freezerContentClicked(View view) {
        if (card_type_showed) {
            fridge_list_card.setEnabled(false);
            card_type_showed = false;
            freezer_button.setBackgroundColor(getResources().getColor(R.color.blue_standart));
            fridge_button.setBackgroundColor(getResources().getColor(R.color.blue_light));
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_hide_fridge_card);
            fridge_list_card.setVisibility(View.INVISIBLE);
            fridge_list_card.startAnimation(animation);
            animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_show_freezer_card);
            freezer_list_card.startAnimation(animation);
            freezer_list_card.setVisibility(View.VISIBLE);
        }
    }

    public void eraseSearchClicked(View view) {
        search_edittext.setText("");
    }

    public void addNewProductClicked(View view) {
        type_edittext.setText(""); // Очистка поля типа продукта
        product_custom_name_edittext.setText(""); // Очистка поля своего наименования продукта
        product_exist_name_edittext.setText(""); // Очистка поля заданного наименования продукта
        add_new_product_button.setClickable(true); // Активация кнопки "Готово"
        cancel_new_product_button.setClickable(true); // Активация кнопки "Отмена"
        radio_group.check(R.id.grocery_activity_radio_exist); // Выбор радио-кнопки
        f_button.setEnabled(false); // Деактивация float action button

        product_type_spinner.setSelection(0); // Выбор первого элемента спиннера

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_show_smooth_alpha);
        product_type_spinner.setEnabled(true); // Активация спиннера
        product_type_spinner.setVisibility(View.VISIBLE);
        product_type_spinner.startAnimation(animation);

        product_exist_name_edittext.setEnabled(true); // Активация поля заданного наименования
        product_exist_name_edittext.setVisibility(View.VISIBLE);
        product_exist_name_edittext.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_hide_smooth_alpha);
        type_edittext.setEnabled(false); // Деактивация поля своего типа
        type_edittext.setVisibility(View.INVISIBLE);
        type_edittext.startAnimation(animation);

        product_custom_name_edittext.setEnabled(false); // Деактивация поля своего типа
        product_custom_name_edittext.setVisibility(View.INVISIBLE);
        product_custom_name_edittext.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_hide_top_card);
        top_card.setVisibility(View.INVISIBLE);
        top_card.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_hide_floating_button);
        f_button.setVisibility(View.INVISIBLE);
        f_button.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_show_add_card);
        add_card.startAnimation(animation);
        add_card.setVisibility(View.VISIBLE);
        if (card_type_showed) {
            fridge_list_card.setVisibility(View.INVISIBLE);
            animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_hide_smooth_alpha);
            fridge_list_card.startAnimation(animation);
        } else {
            freezer_list_card.setVisibility(View.INVISIBLE);
            animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_hide_smooth_alpha);
            freezer_list_card.startAnimation(animation);
        }
       /* SQLiteDatabase DataBase = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
        SQLQuery = "SELECT * FROM users WHERE (login = '" + login_sql + "' AND pass = '" + password_sql + "')";
        Cursor query = DataBase.rawQuery(SQLQuery, null);
        if (query.moveToFirst()) {
            login = query.getString(0);
            password = query.getString(1);
            name = query.getString(2);
            sex = query.getInt(3);
        }
        query.close();
        DataBase.close();*/
    }

    public void existRadioButtonClicked(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_show_smooth_alpha);
        product_type_spinner.setEnabled(true);
        product_type_spinner.setVisibility(View.VISIBLE);
        product_type_spinner.startAnimation(animation);

        product_exist_name_edittext.setEnabled(true);
        product_exist_name_edittext.setVisibility(View.VISIBLE);
        product_exist_name_edittext.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_hide_smooth_alpha);
        type_edittext.setEnabled(false);
        type_edittext.setVisibility(View.INVISIBLE);
        type_edittext.startAnimation(animation);

        product_custom_name_edittext.setEnabled(false);
        product_custom_name_edittext.setVisibility(View.INVISIBLE);
        product_custom_name_edittext.startAnimation(animation);
    }

    public void customRadioButtonClicked(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_hide_smooth_alpha);
        product_type_spinner.setEnabled(false);
        product_type_spinner.setVisibility(View.INVISIBLE);
        product_type_spinner.startAnimation(animation);

        product_exist_name_edittext.setEnabled(false);
        product_exist_name_edittext.setVisibility(View.INVISIBLE);
        product_exist_name_edittext.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_show_smooth_alpha);
        type_edittext.setEnabled(true);
        type_edittext.setVisibility(View.VISIBLE);
        type_edittext.startAnimation(animation);

        product_custom_name_edittext.setEnabled(true);
        product_custom_name_edittext.setVisibility(View.VISIBLE);
        product_custom_name_edittext.startAnimation(animation);
    }

    public void cancelAddButtonClicked(View view) {
        add_new_product_button.setClickable(false);
        cancel_new_product_button.setClickable(false);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_show_top_card);
        top_card.startAnimation(animation);
        top_card.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_hide_add_card);
        add_card.setVisibility(View.INVISIBLE);
        add_card.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_show_floating_button);
        f_button.startAnimation(animation);
        f_button.setVisibility(View.VISIBLE);
        if (card_type_showed) {
            animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_show_smooth_alpha);
            fridge_list_card.startAnimation(animation);
            fridge_list_card.setVisibility(View.VISIBLE);
        } else {
            animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_show_smooth_alpha);
            freezer_list_card.startAnimation(animation);
            freezer_list_card.setVisibility(View.VISIBLE);
        }
        f_button.setEnabled(true);
    }

    public void createAddButtonClicked(View view) {
        add_new_product_button.setClickable(false);
        cancel_new_product_button.setClickable(false);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_show_top_card);
        top_card.startAnimation(animation);
        top_card.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_hide_add_card);
        add_card.setVisibility(View.INVISIBLE);
        add_card.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_grocery_activity_show_floating_button);
        f_button.startAnimation(animation);
        f_button.setVisibility(View.VISIBLE);
        if (card_type_showed) {
            animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_show_smooth_alpha);
            fridge_list_card.startAnimation(animation);
            fridge_list_card.setVisibility(View.VISIBLE);
        } else {
            animation = AnimationUtils.loadAnimation(this, R.anim.anim_global_show_smooth_alpha);
            freezer_list_card.startAnimation(animation);
            freezer_list_card.setVisibility(View.VISIBLE);
        }
        f_button.setEnabled(true);

    }

    private class MyArrayAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MyArrayAdapter(GroceryActivity con) {
            // TODO Auto-generated constructor stub
            mInflater = LayoutInflater.from(con);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return countries.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ListContent holder;
            View v = convertView;
            if (v == null) {
                v = mInflater.inflate(R.layout.grocery_spinner_element, null);
                holder = new ListContent();

                holder.name = (TextView) v.findViewById(R.id.textView1);

                v.setTag(holder);
            } else {

                holder = (ListContent) v.getTag();
            }

            holder.name.setTypeface(myFont);
            holder.name.setTextSize(27);
            holder.name.setText("" + countries[position]);
            return v;
        }

    }

    static class ListContent {
        TextView name;
    }

}
