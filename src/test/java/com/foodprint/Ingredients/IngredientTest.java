package com.foodprint.Ingredients;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class IngredientTest {

    private Ingredient ingredient;

    @Before
    public void setUp(){
        ingredient = new Ingredient(
                "testName",
                1.2,
                1.3,
                1.4,
                "testGroup",
                "testSubGroup",
                1.5,
                1.6
        );
    }

    @Test
    public void testGettersSetters(){
        assertEquals("testName", ingredient.getName());
        assertEquals("testGroup", ingredient.getFoodGroup());
        assertEquals(1.2, ingredient.getCalories(), 0.0);
        assertEquals(1.3, ingredient.getCo2Equivalent(), 0.0);
        assertEquals(1.4, ingredient.getCarMilesEquivalent(), 0.0);
        assertEquals(1.5, ingredient.getEnergyUsage(), 0.0);
        assertEquals(1.6, ingredient.getWaterUsage(), 0.0);
        assertEquals("testSubGroup", ingredient.getFoodSubGroup());
    }

    @Test
    public void testJsonConstructor() throws IOException {
        String ingredientString = "{\n" +
                "\"name\":\"testName\",\n" +
                "\"calories\": 1.0,\n" +
                "\"co2_per_kg\":1.3,\n" +
                "\"car_miles_eq\": 1.4,\n" +
                "\"food_group\": \"testGroup\",\n" +
                "\"food_subgroup\": \"testSubGroup\",\n" +
                "\"energy_usage\": 1.5,\n" +
                "\"water_usage\": 1.6\n" +
                "}";
        JSONObject jsonObject = new JSONObject(ingredientString);
        Ingredient ab = new Ingredient(jsonObject);

        assertEquals("testName", ingredient.getName());
        assertEquals("testGroup", ingredient.getFoodGroup());
        assertEquals(1.2, ingredient.getCalories(), 0.0);
        assertEquals(1.3, ingredient.getCo2Equivalent(), 0.0);
        assertEquals(1.4, ingredient.getCarMilesEquivalent(), 0.0);
        assertEquals(1.5, ingredient.getEnergyUsage(), 0.0);
        assertEquals(1.6, ingredient.getWaterUsage(), 0.0);
        assertEquals("testSubGroup", ingredient.getFoodSubGroup());
    }

}