package com.foodprint;

import com.foodprint.parser.RicParser;
import com.foodprint.response.FoodPrintResponse;
import com.foodprint.response.ResponseGenerator;
import com.foodprint.util.IngredientParser;
import com.foodprint.util.ObjectParser;

public class testin {

    public static void main(String[] args) {
        String ingr1 = "https://www.ricardocuisine.com/en/recipes/8766-family-style-spicy-thai-beef#comments";
        RicParser parser = new RicParser();
        IngredientParser ingredientParser = IngredientParser.getInstance();

//        for(Object ingr: parser.parseIngredients(ingr1)){
//            System.out.printf("%s  \t|\t  %s \n", ingredientParser.removeQuantityFromString(ingr.toString()), ingr.toString());
//        }
//
//        String ingr = "1 kg chicken";


//        String substr = ingr.substring(ingr.indexOf("(")+1, ingr.indexOf(")"));
//        System.out.println(substr);
        ResponseGenerator responseGenerator = new ResponseGenerator();
        FoodPrintResponse res = responseGenerator.generateResponse(ingr1);
        System.out.println(res);

//        System.out.println(res);

    }
}
