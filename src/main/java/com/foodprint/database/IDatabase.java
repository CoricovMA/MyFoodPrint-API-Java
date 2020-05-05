package com.foodprint.database;

import com.foodprint.Ingredients.Ingredient;

import java.util.List;

public interface IDatabase {

    Ingredient getIngredient(String name);

    void addIngredient(Ingredient ingredientToAdd);

    List<Ingredient> getAllIngredients();

}
