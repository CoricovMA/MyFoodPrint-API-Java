package com.foodprint.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.foodprint.Ingredients.Ingredient;
import com.foodprint.interfaces.IDatabase;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class LocalDatabase implements IDatabase {

    private static final Logger logger = LogManager.getLogger(LocalDatabase.class);
    private static LocalDatabase instance;
    private static Map<String, Ingredient> ingredientsMap;
    private static ServletContext servletContext;

    private LocalDatabase(){
        try {
            ingredientsMap = getIngredientsMapFromJson(getIngredientsJson());
        } catch (URISyntaxException e) {
            logger.error("URISyntaxException while getting ingredient json. {}", e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException while getting ingredient json. {}", e.getMessage());
        }
    }

    public static IDatabase getInstance() {
        if(instance == null){
            instance = new LocalDatabase();
        }
        logger.info("Instantiating local database.");
        return instance;
    }

    @Override
    public Object getIngredient(String name) {
        try{

            return ingredientsMap.get(name);

        }catch (Exception e){

            logger.info("Error fetching ingredient \"{}\".", name);
            return null;
        }
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

    public static JSONObject getIngredientsJson() throws URISyntaxException, FileNotFoundException {

        String data = null;
        String fileName = "food/all_ingredients.json";

        URL url = LocalDatabase.class.getClassLoader().getResource(fileName);

        try{
            assert url != null;
            data = Files.toString(new File(url.toURI()), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JSONObject(data);
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


    public static void setContext(ServletContext context){
        servletContext = context;
    }
}

