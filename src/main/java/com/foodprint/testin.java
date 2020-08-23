package com.foodprint;

import com.foodprint.parser.RicParser;
import com.foodprint.response.FoodPrintResponse;
import com.foodprint.response.ResponseGenerator;
import com.foodprint.util.IngredientParser;
import com.foodprint.util.ObjectParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.Scanner;

public class testin {

    public static void main(String[] args) throws IOException, CsvValidationException {
//        File f = new File("C:\\Users\\AMC\\IdeaProjects\\MyFoodPrint-API-Java\\src\\main\\resources\\food\\fruits_and_veg_singles.csv");
//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(f);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        CSVReader csvReader = new CSVReader(new FileReader(f));
//        String []record;
//        while((record = csvReader.readNext()) != null) {
//            for(String line : record){
//                System.out.println(line);
//            }
//        }

        String ingr1 = "https://www.ricardocuisine.com/en/recipes/8752-quinoa-bowl-with-salmon-zucchini-and-sweet-potatoes";
//        String ingr1 = "1 kg chicken";
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
