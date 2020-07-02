package com.foodprint.response;

import com.foodprint.Ingredients.IngredientRequest;
import com.foodprint.Ingredients.IngredientResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ResponseGenerator {

    private static final Logger logger = LogManager.getLogger(ResponseGenerator.class);

    public FoodPrintResponse generateResponse(String requestString){
        FoodPrintRequest request = new FoodPrintRequest(requestString);
        FoodPrintResponse response = new FoodPrintResponse().setRequest(request);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        logger.info("Instantiated executor with {} threads.", request.getRequestIngredients().size());

        List<IngredientResponse> resultList = new ArrayList<>();

        for(Object ingredientRequest: request.getRequestIngredients()){
            FoodPrintCalculator calculator = new FoodPrintCalculator((IngredientRequest)ingredientRequest);
            Future<IngredientResponse> result = executor.submit(calculator);

            try {

                resultList.add(result.get());

            } catch (InterruptedException e) {

                logger.warn("Error adding IngredientResponse {} to result list.", ingredientRequest, e);

            } catch (ExecutionException e) {

                logger.warn("Execution exception while trying to add {} to result list.", ingredientRequest );

            }

        }

        logger.info("Shutting down executor.");
        executor.shutdown();
        response.setIngredientsResponseList(resultList);

        return response;
    }


}
