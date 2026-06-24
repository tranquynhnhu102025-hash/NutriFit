package com.example.nutrifit.data.model;

public class Food {
    private int id;
    private String food_name;
    private int calories;

    public Food(int id, String food_name, int calories) {
        this.id = id;
        this.food_name = food_name;
        this.calories = calories;
    }

    public int getId() { return id; }
    public String getFoodName() { return food_name; }
    public int getCalories() { return calories; }
}
