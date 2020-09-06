package com.foodprint.util;

import com.foodprint.Ingredients.Ingredient;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ObjectParserTest {

    private Ingredient ingredient;
    private JSONObject ingredientJsonObject;
    private Map objectMap;

    @Before
    public void setUp() throws Exception {
        ingredient = new Ingredient();
        ingredient.setName("testIngredient");
        ingredient.setFoodSubGroup("testSubGroup");
        ingredient.setFoodGroup("testFoodGroup");
        ingredient.setCalories(1234);
        ingredient.setCarMilesEquivalent(123);
        ingredient.setCo2Equivalent(1123);
        ingredient.setEnergyUsage(12345);
        ingredient.setWaterUsage(123456);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getObjectMap() {
        ingredientJsonObject = new JSONObject(ingredient.toString());
        objectMap = ObjectParser.getObjectMap(ingredient);

        assertEquals("testIngredient", objectMap.get("name"));
        assertEquals(1234.0, objectMap.get("calories"));
        assertEquals(123.0, objectMap.get("car_miles_eq"));
        assertEquals(1123.0, objectMap.get("co2_per_kg"));
        assertEquals(12345.0, objectMap.get("energy_usage"));
        assertEquals(123456.0, objectMap.get("water_usage"));
        assertEquals("testFoodGroup", objectMap.get("food_group"));
        assertEquals("testSubGroup", objectMap.get("food_subgroup"));
    }
}