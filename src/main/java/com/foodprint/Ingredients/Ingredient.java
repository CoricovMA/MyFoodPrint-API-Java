package com.foodprint.Ingredients;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.base.BaseFoodPrintObject;
import org.json.JSONObject;

import java.io.IOException;

public class Ingredient extends BaseFoodPrintObject {
    @JsonProperty("name")
    private String name;

    @JsonProperty("calories")
    private float calories;

    @JsonProperty("co2_per_kg")
    private float co2Equivalent;

    @JsonProperty("car_miles_eq")
    private float carMilesEquivalent;

    @JsonProperty("food_group")
    private String foodGroup;

    @JsonProperty("food_subgroup")
    private String foodSubGroup;

    @JsonProperty("energy_usage")
    private float energyUsage;

    @JsonProperty("water_usage")
    private float waterUsage;

    // set to empty string vs null.
    // empty string shows up in jon, null does not
    public Ingredient(){
        this.calories = 0;
        this.carMilesEquivalent = 0;
        this.co2Equivalent = 0;
        this.foodGroup = "";
        this.foodSubGroup = "";
        this.name = "";
        this.energyUsage = 0;
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
        this.setWaterUsage(tempIngredient.getWaterUsage());
        this.setEnergyUsage(tempIngredient.getEnergyUsage());
    }

    public Ingredient(String name){
        this();
        this.setName(name);
    }

    public Ingredient(String name, float calories){
        this(name);
        this.setCalories(calories);
    }

    public Ingredient(String name, float calories, float co2Equivalent){
        this(name, calories);
        this.setCo2Equivalent(co2Equivalent);
    }

    public Ingredient(String name, float calories, float co2Equivalent, float carMilesEquivalent){
        this(name, calories, co2Equivalent);
        this.setCarMilesEquivalent(carMilesEquivalent);
    }

    public Ingredient(String name, float calories, float co2Equivalent, float carMilesEquivalent, String foodGroup){
        this(name, calories, co2Equivalent, carMilesEquivalent);
        this.setFoodGroup(foodGroup);
    }

    public Ingredient(String name, float calories, float co2Equivalent,
                      float carMilesEquivalent, String foodGroup, String foodSubGroup){
        this(name, calories, co2Equivalent, carMilesEquivalent, foodGroup);
        this.setFoodSubGroup(foodSubGroup);
    }

    public Ingredient(String name, float calories, float co2Equivalent,
                      float carMilesEquivalent, String foodGroup, String foodSubGroup, float energyUsage){
        this(name, calories, co2Equivalent, carMilesEquivalent, foodGroup, foodSubGroup);
        this.setEnergyUsage(energyUsage);
    }

    public Ingredient(String name, float calories, float co2Equivalent,
                      float carMilesEquivalent, String foodGroup, String foodSubGroup, float energyUsage, float waterUsage){
        this(name, calories, co2Equivalent, carMilesEquivalent, foodGroup, foodSubGroup, energyUsage);
        this.setWaterUsage(waterUsage);
    }

    public void setName(String name){
        this.name = name;
    }
    public void setEnergyUsage(float energyUsage){
        this.energyUsage = energyUsage;
    }

    public void setCalories(float calories){
        this.calories = calories;
    }

    public void setCo2Equivalent(float co2Equivalent){
        this.co2Equivalent = co2Equivalent;
    }

    public void setCarMilesEquivalent(float carMilesEquivalent){
        this.carMilesEquivalent = carMilesEquivalent;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public void setFoodSubGroup(String foodSubGroup){
        this.foodSubGroup = foodSubGroup;
    }


    public String getName(){
        return this.name;
    }

    private float getCalories(){
        return this.calories;
    }

    private float getCo2Equivalent(){
        return this.co2Equivalent;
    }

    private float getCarMilesEquivalent(){
        return this.carMilesEquivalent;
    }

    private String getFoodGroup(){
        return this.foodGroup;
    }

    private String getFoodSubGroup(){
        return this.foodSubGroup;
    }

    public float getEnergyUsage() {
        return energyUsage;
    }

    public float getWaterUsage() {
        return waterUsage;
    }

    public void setWaterUsage(float waterUsage) {
        this.waterUsage = waterUsage;
    }
}
