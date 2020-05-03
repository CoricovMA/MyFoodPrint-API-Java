package com.foodprint;

import com.foodprint.response.FoodPrintRequest;
import com.foodprint.response.FoodPrintResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class testin {

    public static void main(String[] args) throws InterruptedException {

        Logger logger = LogManager.getLogger(testin.class);

        logger.info("Hello");

        String param = "1 kg beef, 1 kg chicken";
        FoodPrintRequest foodPrintRequest = new FoodPrintRequest(param);
        System.out.println(foodPrintRequest);
        FoodPrintResponse foodPrintResponse = new FoodPrintResponse().setRequest(foodPrintRequest);
        System.out.println(foodPrintResponse);
    }
}
