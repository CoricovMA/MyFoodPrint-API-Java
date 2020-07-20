package com.foodprint.database;

import com.foodprint.Ingredients.Ingredient;
import com.foodprint.interfaces.IDatabase;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class SinglesDB implements IDatabase {

    private static final Logger logger = LogManager.getLogger(SinglesDB.class);
    private static SinglesDB instance;
    private static Map<String, Ingredient> singlesIngredientsMap;

    private SinglesDB(){
        try{
            singlesIngredientsMap = getSinglesMapFromJson(getIngredientsJson());
        }catch (FileNotFoundException e){
            logger.error("We couldn't find that csv file. {}", e.getMessage());
        }
    }

    private Map<String, Ingredient> getSinglesMapFromJson(JSONObject ingredientsJson) {
        return null;
    }

    @Override
    public Object getIngredient(String name) {
        return 
    }

    @Override
    public void addIngredient(Ingredient ingredientToAdd) {

    }

    public static JSONObject getIngredientsJson() throws FileNotFoundException{
        String fileName = "food/fruits_and_veg_singles.csv";

        URL url = LocalDatabase.class.getClassLoader().getResource(fileName);
        JSONObject toReturn = new JSONObject();
        try{
            assert url != null;
            File f = new File(url.toURI());
            CSVReader csvReader = new CSVReader(new FileReader(f));
            String []record;
            while((record = csvReader.readNext()) != null) {
                toReturn.append(record[0], record[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return null;
    }

    @Override
    public boolean containsKey(String key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }
}
