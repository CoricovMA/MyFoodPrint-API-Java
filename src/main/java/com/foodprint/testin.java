package com.foodprint;

import com.foodprint.database.LocalDatabase;

public class testin {

    public static void main(String[] args) {
        System.out.println(LocalDatabase.getIngredientsJson().toString());
        System.out.println(LocalDatabase.getInstance().getAllIngredients());
    }
}
