package com.foodprint.Ingredients;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.foodprint.interfaces.AbstractFoodPrintObject;
import org.json.JSONObject;

import java.io.IOException;

@JsonPropertyOrder({"name",
                    "calories",
                    "co2_per_kg",
                    "car_miles_eq",
                    "food_group",
                    "food_subgroup",
                    "energy_usage",
                    "water_usage"
})
public class Ingredient extends AbstractFoodPrintObject {
    @JsonProperty("name")
    private String name;

    @JsonProperty("calories")
    private double calories;

    @JsonProperty("co2_per_kg")
    private double co2Equivalent;

    @JsonProperty("car_miles_eq")
    private double carMilesEquivalent;

    @JsonProperty("food_group")
    private String foodGroup;

    @JsonProperty("food_subgroup")
    private String foodSubGroup;

    @JsonProperty("energy_usage")
    private double energyUsage;

    @JsonProperty("water_usage")
    private double waterUsage;

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

    public Ingredient(String name, double calories){
        this(name);
        this.setCalories(calories);
    }

    public Ingredient(String name, double calories, double co2Equivalent){
        this(name, calories);
        this.setCo2Equivalent(co2Equivalent);
    }

    public Ingredient(String name, double calories, double co2Equivalent, double carMilesEquivalent){
        this(name, calories, co2Equivalent);
        this.setCarMilesEquivalent(carMilesEquivalent);
    }

    public Ingredient(String name, double calories, double co2Equivalent, double carMilesEquivalent, String foodGroup){
        this(name, calories, co2Equivalent, carMilesEquivalent);
        this.setFoodGroup(foodGroup);
    }

    public Ingredient(String name, double calories, double co2Equivalent,
                      double carMilesEquivalent, String foodGroup, String foodSubGroup){
        this(name, calories, co2Equivalent, carMilesEquivalent, foodGroup);
        this.setFoodSubGroup(foodSubGroup);
    }

    public Ingredient(String name, double calories, double co2Equivalent,
                      double carMilesEquivalent, String foodGroup, String foodSubGroup, double energyUsage){
        this(name, calories, co2Equivalent, carMilesEquivalent, foodGroup, foodSubGroup);
        this.setEnergyUsage(energyUsage);
    }

    public Ingredient(String name, double calories, double co2Equivalent,
                      double carMilesEquivalent, String foodGroup, String foodSubGroup, double energyUsage, double waterUsage){
        this(name, calories, co2Equivalent, carMilesEquivalent, foodGroup, foodSubGroup, energyUsage);
        this.setWaterUsage(waterUsage);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEnergyUsage(double energyUsage){
        this.energyUsage = energyUsage;
    }

    public void setCalories(double calories){
        this.calories = calories;
    }

    public void setCo2Equivalent(double co2Equivalent){
        this.co2Equivalent = co2Equivalent;
    }

    public void setCarMilesEquivalent(double carMilesEquivalent){
        this.carMilesEquivalent = carMilesEquivalent;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public void setFoodSubGroup(String foodSubGroup){
        this.foodSubGroup = foodSubGroup;
    }

    public void setWaterUsage(double waterUsage) {
        this.waterUsage = waterUsage;
    }

    public String getName(){
        return this.name;
    }

    public String getFoodGroup(){
        return this.foodGroup;
    }

    public String getFoodSubGroup(){
        return this.foodSubGroup;
    }

    public double getCalories(){
        return this.calories;
    }

    public double getCo2Equivalent(){
        return this.co2Equivalent;
    }

    public double getCarMilesEquivalent(){
        return this.carMilesEquivalent;
    }

    public double getEnergyUsage() {
        return energyUsage;
    }

    public double getWaterUsage() {
        return waterUsage;
    }


}
