package com.foodprint.interfaces;

import com.foodprint.Ingredients.Ingredient;

import java.util.List;

public interface IDatabase  {

    Object getIngredient(String name);

    void addIngredient(Ingredient ingredientToAdd);

    List<Ingredient> getAllIngredients();

    boolean containsKey(String key);

    boolean containsValue(Object value);
}
