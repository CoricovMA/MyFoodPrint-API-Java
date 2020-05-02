package com.foodprint;

import com.foodprint.response.FoodPrintRequest;
import com.foodprint.response.FoodPrintResponse;

public class testin {

    public static void main(String[] args) throws InterruptedException {
        String param = "1 kg beef, 1 kg chicken";
        FoodPrintRequest foodPrintRequest = new FoodPrintRequest(param);
        System.out.println(foodPrintRequest);
        FoodPrintResponse foodPrintResponse = new FoodPrintResponse().setRequest(foodPrintRequest);
        System.out.println(foodPrintResponse);
    }
}
