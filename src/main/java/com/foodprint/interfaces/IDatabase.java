package com.foodprint.interfaces;

import com.foodprint.Ingredients.Ingredient;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDatabase  {

    Ingredient getIngredient(String name);

    void addIngredient(Ingredient ingredientToAdd);

    List<Ingredient> getAllIngredients();

    boolean containsKey(String key);

    boolean containsValue(Object value);
}
