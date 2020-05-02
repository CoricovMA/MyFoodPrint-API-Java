package com.foodprint.Ingredients;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.base.BaseFoodPrintObject;

public class IngredientResponse extends BaseFoodPrintObject {

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

}
