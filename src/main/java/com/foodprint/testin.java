package com.foodprint;

import com.foodprint.Ingredients.Ingredient;
import com.foodprint.util.ObjectParser;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class testin {

    public static void main(String[] args){
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\AMC\\IdeaProjects\\MyFoodPrint-API-Java\\src\\main\\resources\\food_json\\all_ingredients.json");
            Scanner scanner = new Scanner(fileInputStream);
            StringBuilder sb = new StringBuilder();
            while(scanner.hasNext()){
                sb.append(scanner.nextLine().trim());
            }

            System.out.println(sb.toString());
            JSONObject jsonObject = new JSONObject(sb.toString());
           

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
