package com.foodprint.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.Ingredients.IngredientRequest;
import com.foodprint.interfaces.AbstractFoodPrintObject;
import com.foodprint.interfaces.Request;
import com.foodprint.parser.ParserFactory;
import com.foodprint.util.IngredientParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodPrintRequest extends AbstractFoodPrintObject implements Request {

    private List<Object> requestIngredients;
    private static final IngredientParser ingredientParser = IngredientParser.getInstance();
    private final String requestedIngredientsText;

    @JsonProperty("request_ingredients")
    public List<Object> getRequestIngredients() {
        return this.requestIngredients;
    }

    @JsonProperty("request_errors")
    public List<Object> errors;

    /**
     * TODO ADD FUNCTIONALITY FOR REQUESTS CONTAINING WEBSITES
     * 1. Detect if website in request
     * 2. Use appropriate parser
     * 3. Generate request from parser (parser should return list of ingredients)
     *
     * @param requestedIngredientsText Text of requested ingredients, which will be parsed and transformed to a FoodPrintRequest
     */

    public FoodPrintRequest(String requestedIngredientsText) {

        this.requestedIngredientsText = requestedIngredientsText;

        setRequestIngredientsList();

        setObjectToIngredientRequest();

    }

    /**
     * This is where the list of ingredients is set
     */
    private void setRequestIngredientsList() {
        if (requiresParser()) {
            try {
                requestIngredients = ParserFactory.getParser(requestedIngredientsText).parseIngredients(requestedIngredientsText);
            }catch (NullPointerException npe){

            }
        } else {

            normalRequest();

        }
    }

    private boolean requiresParser() {
        return this.requestedIngredientsText.contains(".com")
                || this.requestedIngredientsText.contains("https://")
                || this.requestedIngredientsText.contains("www.");
    }

    private void normalRequest() {
        requestIngredients = new ArrayList<>();

        requestIngredients.addAll(Arrays.asList(splitGivenIngredients(requestedIngredientsText)));
    }

    private String[] splitGivenIngredients(String givenString) {
        String[] arrayOfIngredients;

        givenString = givenString.replace('\n', ',');

        if (givenString.contains(",") && isValidArrayOfIngredients(givenString)) {
            arrayOfIngredients = givenString.split(",");
        } else {
            arrayOfIngredients = new String[1];

            if(givenString.contains(",")){
                givenString = givenString.replace(",", "");
            }

            arrayOfIngredients[0] = givenString;
        }

        return arrayOfIngredients;
    }

    private boolean isValidArrayOfIngredients(String givenIngredientString){
        String [] arrayOfPotentialIngredients = givenIngredientString.split(",");

        if(arrayOfPotentialIngredients.length > 2){
            return true;
        }

        if(arrayOfPotentialIngredients.length == 1){
            return true;
        }else return arrayOfPotentialIngredients.length == 2 && arrayOfPotentialIngredients[1].trim().split(" ").length > 1;

    }

    private void setObjectToIngredientRequest() {
        List<Object> listOfIngredients = new ArrayList<>();

        for (Object ingredientIter : requestIngredients) {
            IngredientRequest ingredient = new IngredientRequest();

            String ingredientToCheck = ingredientIter.toString();

            ingredient.setRequestedString(ingredientToCheck.trim());

            if (IngredientParser.hasParentheses(ingredientToCheck)) {

                String volumeQuantity = ingredientToCheck.
                        substring(ingredientToCheck.indexOf("(") + 1,
                                ingredientToCheck.indexOf(")"));

                ingredient.setVolume(ingredientParser.getVolumeFromString(volumeQuantity));

                ingredient.setQuantity(ingredientParser.getQuantityFromString(volumeQuantity));

            } else {

                ingredient.setQuantity(ingredientParser.getQuantityFromString(ingredientToCheck));

                ingredient.setVolume(ingredientParser.getVolumeFromString(ingredientToCheck));

            }
            
            String ingredientName = (String) ingredientIter;

            try {
                ingredientName = ingredientName.replace(ingredient.getVolume(), "");
            } catch (NullPointerException e) {
                ingredient.setVolume("single(s)");
            }

            ingredientName = ingredientParser.removeQuantityFromString(ingredientName);

            ingredient.setIngredient(ingredientName);

            listOfIngredients.add(ingredient);
        }

        this.requestIngredients = listOfIngredients;

    }

}
