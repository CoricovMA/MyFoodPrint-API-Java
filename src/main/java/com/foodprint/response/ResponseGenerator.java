package com.foodprint.response;

import com.foodprint.Ingredients.Ingredient;
import com.foodprint.Ingredients.IngredientRequest;
import com.foodprint.Ingredients.IngredientResponse;
import com.foodprint.interfaces.IDatabase;
import com.foodprint.database.LocalDatabase;
import com.foodprint.interfaces.Request;
import com.foodprint.interfaces.Response;
import com.foodprint.util.IngredientParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.foodprint.Ingredients.IngredientResponse.*;

public class ResponseGenerator {

    private static final IngredientParser INGREDIENT_PARSER = IngredientParser.getInstance();
    private static final Logger logger = LogManager.getLogger(ResponseGenerator.class);

    public Response generateFoodPrintResponse(String requestString){
        FoodPrintRequest request = new FoodPrintRequest(requestString);
        FoodPrintResponse response = new FoodPrintResponse().setRequest(request);
        return null ;
    }

    private Request generateRequest(String requestString){

        return null;
    }

    private FoodPrintResponse generateResponse(String requestString){
        FoodPrintRequest request = new FoodPrintRequest(requestString);
        FoodPrintResponse response = new FoodPrintResponse().setRequest(request);

        for(Object ingredientRequest: request.getRequestIngredients()){


        }

        return null;
    }

    public IngredientResponse getResponseFromRequest(IngredientRequest ingredientRequest){

        return null;
    }


}
