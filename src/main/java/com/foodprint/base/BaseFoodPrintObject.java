package com.foodprint.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseFoodPrintObject {

    private static Logger logger = LogManager.getLogger(BaseFoodPrintObject.class);
    private static ObjectMapper jsonMapper = new JsonMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public String toString(){
        String objectString = "";
        try {
            objectString =  jsonMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.warn("Something went wrong.", e);
        }

        return objectString;
    }

    public static ObjectMapper getJsonMapper(){
        return jsonMapper;
    }

}
