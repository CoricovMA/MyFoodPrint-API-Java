package com.foodprint.Ingredients;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.interfaces.AbstractFoodPrintObject;

import java.util.List;

public final class IngredientResponse extends AbstractFoodPrintObject {

    @JsonProperty("name")
    private String name;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("volume")
    private String volume;

    @JsonProperty("emissions")
    private double emissions;

    @JsonProperty("calories")
    private int calories;

    public IngredientResponse(Builder builder){
        this.name = builder.name;
        this.quantity = builder.quantity;
        this.volume = builder.volume;
        this.emissions = builder.emissions;
        this.calories = builder.calories;
    }

    public static class Builder{
        private String name;
        private int quantity;
        private double emissions;
        private int calories;
        private String volume;

        public static Builder newInstance(){
            return new Builder();
        }

        private Builder(){}

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setQuantity(int quantity){
            this.quantity = quantity;
            return this;
        }

        public Builder setEmissions(double emissions){
            this.emissions = emissions;
            return this;
        }

        public Builder setCalories(int calories){
            this.calories = calories;
            return this;
        }

        public Builder setVolume(String volume){
            this.volume = volume;
            return this;
        }

        public IngredientResponse build(){
            return new IngredientResponse(this);
        }

    }

}
