package com.foodprint;

import com.foodprint.parser.RicParser;
import com.foodprint.response.FoodPrintResponse;
import com.foodprint.response.ResponseGenerator;
import com.foodprint.util.IngredientParser;
import com.foodprint.util.ObjectParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class testin {

    public static void main(String[] args) {
        File f = new File("C:\\Users\\AMC\\IdeaProjects\\MyFoodPrint-API-Java\\src\\main\\resources\\food\\fruits_and_veg_singles.csv");
        Scanner scanner = null;
        try {
            scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNext()){
            System.out.println(scanner.nextLine().split(","));
        }

//        String ingr1 = "ripe avocado, cubed";
////        String ingr1 = "1 kg chicken";
//        RicParser parser = new RicParser();
//        IngredientParser ingredientParser = IngredientParser.getInstance();
//
////        for(Object ingr: parser.parseIngredients(ingr1)){
////            System.out.printf("%s  \t|\t  %s \n", ingredientParser.removeQuantityFromString(ingr.toString()), ingr.toString());
////        }
////
////        String ingr = "1 kg chicken";
//
//
////        String substr = ingr.substring(ingr.indexOf("(")+1, ingr.indexOf(")"));
////        System.out.println(substr);
//        ResponseGenerator responseGenerator = new ResponseGenerator();
//        FoodPrintResponse res = responseGenerator.generateResponse(ingr1);
//        System.out.println(res);
//
////        System.out.println(res);

    }
}
