package com.foodprint.response;

import com.foodprint.Ingredients.Ingredient;
import com.foodprint.Ingredients.IngredientRequest;
import com.foodprint.Ingredients.IngredientResponse;
import com.foodprint.database.LocalDatabase;
import com.foodprint.interfaces.IDatabase;
import com.foodprint.util.IngredientParser;

import java.util.List;
import java.util.concurrent.Callable;

public class FoodPrintCalculator implements Callable<IngredientResponse> {

    private static final IDatabase database = LocalDatabase.getInstance();
    private final IngredientRequest request;
    private static final IngredientParser ingredientParser = IngredientParser.getInstance();

    public FoodPrintCalculator(IngredientRequest request) {
        this.request = request;
    }

    @Override
    public IngredientResponse call() {
        IngredientResponse response = new IngredientResponse();

        Ingredient ingredient = getIngredientFromDb(request.getIngredient());
        double multiplier = ingredientParser.getQuantityFromVol(request.getVolume());
        response.setCalories(ingredient.getCalories() * multiplier);
        response.setEmissions(ingredient.getCo2Equivalent() * multiplier);
        response.setQuantity(request.getQuantity());
        response.setVolume(request.getVolume());
        response.setRequestedString(request.getRequestedString());
        response.setName(ingredient.getName());
        response.setStatus(IngredientResponse.INGREDIENT_STATUS.SUCCESS);

        return response;
    }

    public Ingredient getIngredientFromDb(String givenIngredient) {
        if (database.containsKey(givenIngredient)) {
            return database.getIngredient(givenIngredient);
        }

        String[] arr = givenIngredient.split(" ");
        for (String word : arr) {
            if (database.containsKey(word)) {
                return database.getIngredient(word);
            }
        }

        for (String str : givenIngredient.split(" ")) {
            List<String> suggestions = ingredientParser.getSpellingMatches(str);
            for (String suggestion : suggestions) {
                if (database.containsKey(suggestion)) {
                    return database.getIngredient(suggestion);
                }
            }
        }

        return null;
    }


}
