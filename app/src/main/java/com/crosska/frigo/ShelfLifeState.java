package com.crosska.frigo;

public class ShelfLifeState {

    private String food_name; // Наименование еды
    private String food_type; // Тип еды
    private String food_date; // Срок годности еды
    private String food_days; // Дней свежести еды

    public ShelfLifeState(String food_name, String food_type, String food_date, String food_days) {

        this.food_name = food_name;
        this.food_type = food_type;
        this.food_date = food_date;
        this.food_days = food_days;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getFood_date() {
        return food_date;
    }

    public void setFood_date(String food_date) {
        this.food_date = food_date;
    }

    public String getFood_days() {
        return food_days;
    }

    public void setFood_days(String food_days) {
        this.food_days = food_days;
    }

}
