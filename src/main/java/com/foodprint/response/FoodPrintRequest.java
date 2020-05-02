package com.foodprint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.base.BaseFoodPrintObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodPrintRequest extends BaseFoodPrintObject {

    private List<Object> requestIngredients;


    @JsonProperty("request_ingredients")
    public List<Object> getRequestIngredients(){
        return this.requestIngredients;
    }

    public FoodPrintRequest(String requestedIngredientsText){
        requestIngredients = new ArrayList<>();

        requestIngredients.addAll(Arrays.asList(splitGivenIngredients(requestedIngredientsText)));

    }

    private String [] splitGivenIngredients(String givenString){
        String [] arrayOfIngredients = {};

        if(givenString.contains(",")){
            arrayOfIngredients = givenString.split(",");
        }else if(givenString.contains("\n")){
            arrayOfIngredients = givenString.split("\n");
        }

        return arrayOfIngredients;

    }
}
