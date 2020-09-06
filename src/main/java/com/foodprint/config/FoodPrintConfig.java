package com.foodprint.config;

import com.foodprint.interfaces.IFoodPrintException;

public class FoodPrintConfig implements IFoodPrintException {

    public static enum Property{
        DETAILED_RESPONSE(false, "If set to true, response will contain list of request ingredients," +
                                                "requested ingredient string, quantity parsed, volume parsed, ingredient parsed");
        private Object value;
        private String description;

        Property(Object value, String description){
            this.value = value;
            this.description = description;
        }

        public String getDescription(){
            return this.description;
        }

        public Object getValue(){
            return this.value;
        }

    }



}
