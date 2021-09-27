package com.crosska.frigo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeRecyclerStateAdapter extends RecyclerView.Adapter<RecipeRecyclerStateAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<RecipeRecyclerState> products;

    RecipeRecyclerStateAdapter(Context context, List<RecipeRecyclerState> products) {
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecipeRecyclerStateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recipe_recycler_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeRecyclerStateAdapter.ViewHolder holder, int position) {
        RecipeRecyclerState product = products.get(position);
        holder.foodView.setImageResource(product.getFood_image());
        holder.nameView.setText(product.getFood_name());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView foodView;
        final TextView nameView;

        ViewHolder(View view) {
            super(view);
            foodView = view.findViewById(R.id.recipe_imageview_product);
            nameView = view.findViewById(R.id.recipe_textview_product);
        }
    }

}
