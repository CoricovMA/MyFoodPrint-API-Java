package com.foodprint.Ingredients;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.interfaces.AbstractFoodPrintObject;
import com.foodprint.util.IngredientParser;

public class IngredientRequest extends AbstractFoodPrintObject {

    @JsonProperty("ingredient")
    private String ingredient;

    @JsonProperty("quantity")
    private double quantity;

    @JsonProperty("volume")
    private String volume;

    public IngredientRequest(){
        this.ingredient = "";
        this.quantity = 0.0;
        this.volume = "";
    }

    public IngredientRequest(String ingredient){
        this.ingredient = ingredient;
    }

    public IngredientRequest(String ingredient, double quantity){
        this(ingredient);
        this.quantity = quantity;
    }

    public IngredientRequest(String ingredient, double quantity, String volume){
        this(ingredient, quantity);
        this.volume = volume;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

}
