package com.crosska.frigo;

public class GroceryRecyclerState {

    private String food_name; // Наименование еды
    private int food_image; // Картинка еды

    public GroceryRecyclerState(int food_image, String food_name) {

        this.food_name = food_name;
        this.food_image = food_image;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getFood_image() {
        return food_image;
    }

    public void setFood_image(int food_image) {
        this.food_image = food_image;
    }


}
