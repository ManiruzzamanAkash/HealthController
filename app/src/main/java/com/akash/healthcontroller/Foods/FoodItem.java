package com.akash.healthcontroller.Foods;

/**
 * Created by maniruzzaman_akash on 5/12/2017.
 */

public class FoodItem {
    private String food_name;
    private String food_calorie;

    public FoodItem(String food_name, String food_calorie) {
        super();
        this.food_name = food_name;
        this.food_calorie = food_calorie;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_calorie() {
        return food_calorie;
    }

    public void setFood_calorie(String food_calorie) {
        this.food_calorie = food_calorie;
    }
}
