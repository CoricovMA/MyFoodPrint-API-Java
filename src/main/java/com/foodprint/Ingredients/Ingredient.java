package com.foodprint.Ingredients;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.base.BaseFoodPrintObject;
import org.json.JSONObject;

import java.io.IOException;

public class Ingredient extends BaseFoodPrintObject {
    @JsonProperty("name")
    private String name;

    @JsonProperty("calories")
    private String calories;

    @JsonProperty("co2_eq")
    private String co2Equivalent;

    @JsonProperty("car_miles_eq")
    private String carMilesEquivalent;

    @JsonProperty("food_group")
    private String foodGroup;

    @JsonProperty("food_subgroup")
    private String foodSubGroup;

    // set to empty string vs null.
    // empty string shows up in jon, null does not
    public Ingredient(){
        this.calories = "";
        this.carMilesEquivalent = "";
        this.co2Equivalent = "";
        this.foodGroup = "";
        this.foodSubGroup = "";
        this.name = "";
    }

    public Ingredient(JSONObject jsonObject) throws IOException {
        String stringFromJsonObject = jsonObject.toString();

        Ingredient tempIngredient = getJsonMapper().readValue(stringFromJsonObject, Ingredient.class);

        this.setName(tempIngredient.getName());
        this.setCalories(tempIngredient.getCalories());
        this.setCo2Equivalent(tempIngredient.getCo2Equivalent());
        this.setCarMilesEquivalent(tempIngredient.getCarMilesEquivalent());
        this.setFoodGroup(tempIngredient.getFoodGroup());
        this.setFoodSubGroup(tempIngredient.getFoodSubGroup());
    }

    public Ingredient(String name){
        this();
        this.setName(name);
    }

    public Ingredient(String name, String calories){
        this(name);
        this.setCalories(calories);
    }

    public Ingredient(String name, String calories, String co2Equivalent){
        this(name, calories);
        this.setCo2Equivalent(co2Equivalent);
    }

    public Ingredient(String name, String calories, String co2Equivalent, String carMilesEquivalent){
        this(name, calories, co2Equivalent);
        this.setCarMilesEquivalent(carMilesEquivalent);
    }

    public Ingredient(String name, String calories, String co2Equivalent, String carMilesEquivalent, String foodGroup){
        this(name, calories, co2Equivalent, carMilesEquivalent);
        this.setFoodGroup(foodGroup);
    }

    public Ingredient(String name, String calories, String co2Equivalent,
                      String carMilesEquivalent, String foodGroup, String foodSubGroup){
        this(name, calories, co2Equivalent, carMilesEquivalent, foodGroup);
        this.setFoodSubGroup(foodSubGroup);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCalories(String calories){
        this.calories = calories;
    }

    public void setCo2Equivalent(String co2Equivalent){
        this.co2Equivalent = co2Equivalent;
    }

    public void setCarMilesEquivalent(String carMilesEquivalent){
        this.carMilesEquivalent = carMilesEquivalent;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public void setFoodSubGroup(String foodSubGroup){
        this.foodSubGroup = foodSubGroup;
    }


    private String getName(){
        return this.name;
    }

    private String getCalories(){
        return this.calories;
    }

    private String getCo2Equivalent(){
        return this.co2Equivalent;
    }

    private String getCarMilesEquivalent(){
        return this.carMilesEquivalent;
    }

    private String getFoodGroup(){
        return this.foodGroup;
    }

    private String getFoodSubGroup(){
        return this.foodSubGroup;
    }
}
