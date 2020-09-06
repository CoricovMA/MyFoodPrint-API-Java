package com.foodprint.errors;

import org.json.JSONObject;

public class FoodPrintErrors {

    public enum ERROR{

        /**
         * TODO what could go wrong?
         */

        NO_INGREDIENT_FOUND("Given ingredient not found", 1500),
        NO_QUANTITY_GIVEN("No quantity given to ingredient", 1501);

        private final String message;
        private final int errorCode;
        private String requestedIngredientString;

        ERROR(String message, int code){
            this.errorCode = code;
            this.message = message;
        }

        private void setRequestedIngredientString(String requestedIngredientString){
            this.requestedIngredientString = requestedIngredientString;
        }

        public ERROR setRequestedIngredient(String requestedIngredient){
            this.setRequestedIngredientString(requestedIngredient);
            return this;
        }

        public String getMessage(){
            return this.message;
        }

        public int getCode(){
            return this.errorCode;
        }

        public String toString(){
            JSONObject errorJson = new JSONObject();
            errorJson.put("message", this.message);
            errorJson.put("error_code", this.errorCode);

            if(this.requestedIngredientString != null){
                errorJson.put("requested_ingredient_string", this.requestedIngredientString);
                return errorJson.toString().trim();
            }

            return errorJson.toString().trim();
        }

    }

}
