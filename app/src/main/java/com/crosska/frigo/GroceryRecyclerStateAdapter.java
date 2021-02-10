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

public class GroceryRecyclerStateAdapter extends RecyclerView.Adapter<GroceryRecyclerStateAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<GroceryRecyclerState> states;

    GroceryRecyclerStateAdapter(Context context, List<GroceryRecyclerState> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GroceryRecyclerStateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grocery_recycler_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroceryRecyclerStateAdapter.ViewHolder holder, int position) {
        GroceryRecyclerState state = states.get(position);
        holder.foodView.setImageResource(state.getFood_image());
        holder.nameView.setText(state.getFood_name());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView foodView;
        final TextView nameView;

        ViewHolder(View view) {
            super(view);
            foodView = view.findViewById(R.id.list_imageview_food);
            nameView = view.findViewById(R.id.list_textview_foodname);
        }
    }

}
