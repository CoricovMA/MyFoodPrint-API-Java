package com.foodprint.database;

import com.foodprint.Ingredients.Ingredient;
import com.foodprint.interfaces.IDatabase;

import java.util.List;

public class SinglesDB implements IDatabase {



    @Override
    public Ingredient getIngredient(String name) {
        return null;
    }

    @Override
    public void addIngredient(Ingredient ingredientToAdd) {

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
