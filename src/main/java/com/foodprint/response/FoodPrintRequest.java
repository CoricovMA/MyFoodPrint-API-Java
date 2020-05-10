package com.foodprint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.Ingredients.IngredientRequest;
import com.foodprint.interfaces.AbstractFoodPrintObject;
import com.foodprint.interfaces.Request;
import com.foodprint.util.IngredientParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodPrintRequest extends AbstractFoodPrintObject implements Request {

    private List<Object> requestIngredients;
    private static final IngredientParser ingredientParser = IngredientParser.getInstance();

    @JsonProperty("request_ingredients")
    public List<Object> getRequestIngredients(){
        return this.requestIngredients;
    }

    @JsonProperty("request_errors")
    public List<Object> errors;

    public FoodPrintRequest(String requestedIngredientsText){
        requestIngredients = new ArrayList<>();

        requestIngredients.addAll(Arrays.asList(splitGivenIngredients(requestedIngredientsText)));

        setObjectToIngredientRequest();
    }

    private String [] splitGivenIngredients(String givenString){
        String [] arrayOfIngredients = {};

        givenString = givenString.replace('\n', ',');

        if(givenString.contains(",")){
            arrayOfIngredients = givenString.split(",");
        }else{
            arrayOfIngredients = new String[1];
            arrayOfIngredients[0] = givenString;
        }

        return arrayOfIngredients;
    }

    private void setObjectToIngredientRequest(){
        List<Object> listOfIngredients = new ArrayList<>();
        for(Object ingr: requestIngredients){
            IngredientRequest ingredient = new IngredientRequest();

            ingredient.setRequestedString((String)ingr);
            ingredient.setQuantity(ingredientParser.getQuantityFromString((String)ingr));
            ingredient.setVolume(ingredientParser.getVolumeFromString((String)ingr));

            String ingredientName = (String) ingr;

            try {
                ingredientName = ingredientName.replace(ingredient.getVolume(), "");
            }catch (NullPointerException e){
                ingredient.setVolume("single(s)");
            }


            ingredientName = ingredientParser.removeQuantityFromString(ingredientName);

            ingredient.setIngredient(ingredientName);
            listOfIngredients.add(ingredient);
        }

        this.requestIngredients = listOfIngredients;
    }


}
