package com.foodprint.util;

import com.foodprint.interfaces.AbstractFoodPrintObject;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ObjectParser {

    public static Map getObjectMap(AbstractFoodPrintObject foodPrintObject){
        JSONObject jsonObject = new JSONObject(foodPrintObject.toString());

        Map<String, Object> objectMap = new HashMap<>();

        for(String key: jsonObject.keySet()){
            objectMap.put(key, jsonObject.get(key));
        }

        return objectMap;

    }

}
