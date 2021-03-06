package com.foodprint.interfaces;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractFoodPrintObject {

    private static Logger logger = LogManager.getLogger(AbstractFoodPrintObject.class);
    private static ObjectMapper jsonMapper = new JsonMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public String toString(){
        String objectString = "";
        try {
            objectString =  jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.warn("Something went wrong.", e);
        }

        return objectString;
    }

    public static ObjectMapper getJsonMapper(){
        return jsonMapper;
    }

}
