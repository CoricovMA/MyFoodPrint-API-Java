package com.foodprint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.interfaces.AbstractFoodPrintObject;
import com.foodprint.interfaces.Request;
import com.foodprint.util.StringParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodPrintRequest extends AbstractFoodPrintObject implements Request {

    private List<Object> requestIngredients;
    private static StringParser stringParser = StringParser.getInstance();

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
