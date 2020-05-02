package com.foodprint.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FoodPrintRequest {

    @JsonProperty("request_ingredients")
    private List<Object> requestIngredients;

}
