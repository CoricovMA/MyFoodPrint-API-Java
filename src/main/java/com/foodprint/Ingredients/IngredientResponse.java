package com.foodprint.Ingredients;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.interfaces.AbstractFoodPrintObject;

public final class IngredientResponse extends AbstractFoodPrintObject {

    public enum INGREDIENT_STATUS{
        SUCCESS("Search was successful."),
        ERROR("Error while getting ingredient."),
        NOT_FOUND("Ingredient not found");

        private String message;

        INGREDIENT_STATUS(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }
    }

    @JsonProperty("name")
    private String name;

    @JsonProperty("quantity")
    private double quantity;

    @JsonProperty("volume")
    private String volume;

    @JsonProperty("emissions")
    private double emissions;

    @JsonProperty("calories")
    private double calories;

    @JsonProperty("ingredient_status")
    private INGREDIENT_STATUS status;

    public IngredientResponse(){
        this.name = "";
        this.quantity = 0.0;
        this.volume = "";
        this.emissions = 0.0;
        this.calories = 0;
    }

    public IngredientResponse(String name){
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setEmissions(double emissions) {
        this.emissions = emissions;
    }

    public void setStatus(INGREDIENT_STATUS status){
        this.status = status;
    }

    public String getStatus(){
        return this.status.message;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String getVolume() {
        return volume;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public double getEmissions() {
        return emissions;
    }

    public double getCalories() {
        return calories;
    }

}
