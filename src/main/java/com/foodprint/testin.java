package com.foodprint;

import com.foodprint.response.FoodPrintResponse;
import com.foodprint.response.ResponseGenerator;

public class testin {

    public static void main(String[] args) {
        String ingr = "1 chicken";
        ResponseGenerator responseGenerator = new ResponseGenerator();
        FoodPrintResponse res = responseGenerator.generateResponse(ingr);
        System.out.println(res);
    }
}
