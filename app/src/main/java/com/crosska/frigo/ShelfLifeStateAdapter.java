package com.crosska.frigo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShelfLifeStateAdapter extends RecyclerView.Adapter<ShelfLifeStateAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<ShelfLifeState> products;

    ShelfLifeStateAdapter(Context context, List<ShelfLifeState> products) {
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ShelfLifeStateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.shelf_life_listview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShelfLifeStateAdapter.ViewHolder holder, int position) {
        ShelfLifeState product = products.get(position);
        holder.food_name.setText(product.getFood_name());
        holder.food_type.setText(product.getFood_type());
        holder.food_date.setText(product.getFood_date());
        holder.food_days.setText(product.getFood_days());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView food_name;
        final TextView food_type;
        final TextView food_date;
        final TextView food_days;

        ViewHolder(View view) {
            super(view);
            food_name = view.findViewById(R.id.shelf_life_product_name);
            food_type = view.findViewById(R.id.shelf_life_product_type);
            food_date = view.findViewById(R.id.shelf_life_product_date);
            food_days = view.findViewById(R.id.shelf_life_product_days);
        }
    }

}
