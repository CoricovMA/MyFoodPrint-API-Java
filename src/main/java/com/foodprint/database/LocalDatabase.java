package com.foodprint.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.foodprint.Ingredients.Ingredient;
import com.foodprint.interfaces.IDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LocalDatabase implements IDatabase {

    private static Logger logger = LogManager.getLogger(LocalDatabase.class);
    private static LocalDatabase instance;
    private static Map<String, Ingredient> ingredientsMap;

    private LocalDatabase(){
        ingredientsMap = getIngredientsMapFromJson(getIngredientsJson());
    }

    public static IDatabase getInstance() {
        if(instance == null){
            instance = new LocalDatabase();
        }
        logger.info("Instantiating local database.");
        return instance;
    }

    @Override
    public Ingredient getIngredient(String name) {
        logger.info("Fetching ingredient \"{}\" from local db.", name);

        long startTime = System.currentTimeMillis();

        Ingredient ingredient = ingredientsMap.get(name);

        long endTime = System.currentTimeMillis();

        logger.info("Ingredient {} fetched successfully in {}ms.", name, endTime-startTime);
        return ingredient;
    }

    @Override
    public void addIngredient(Ingredient ingredientToAdd) {
        ingredientsMap.put(ingredientToAdd.getName(), ingredientToAdd);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        for(String key: ingredientsMap.keySet()){
            ingredients.add(ingredientsMap.get(key));
        }

        return ingredients;
    }

    @Override
    public boolean containsKey(String key) {
        return ingredientsMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        Ingredient ingredient = (Ingredient)value;
        return ingredientsMap.containsValue(ingredient);
    }

    public static JSONObject getIngredientsJson() {
        File jsonIngredients = new File("/var/lib/jetty/resources/all_ingredients.json");
//        File jsonIngredients = new File("C:\\Users\\AMC\\IdeaProjects\\MyFoodPrint-API-Java\\src\\main\\resources\\food_json\\all_ingredients.json");

        Scanner scanner = null;

        try {

            scanner = new Scanner(new FileReader(jsonIngredients));

        } catch (FileNotFoundException e) {

            logger.warn("error while instantiating BufferedReader.", e);

        }

        StringBuilder jsonString = new StringBuilder();

        while(scanner.hasNext()){
            jsonString.append(scanner.nextLine());
        }

        scanner.close();

        return new JSONObject(jsonString.toString().trim());
    }

    private Map<String, Ingredient> getIngredientsMapFromJson(JSONObject ingredientsJson){
        Map<String, Ingredient> mapToReturn = new HashMap<>();

        JSONArray arrayOfIngredients = ingredientsJson.getJSONArray("ingredients");

        for(Object jsonObject: arrayOfIngredients){
            try {

                Ingredient toInsert = new Ingredient((JSONObject) jsonObject);

                mapToReturn.put(toInsert.getName().toLowerCase(), toInsert);

            } catch (JsonProcessingException e) {

                logger.warn("Error inserting ingredient in map.", e);

            } catch (IOException e) {

                logger.warn("IOException while inserting ingredient in map.", e);

            }
        }

        return mapToReturn;
    }

}

