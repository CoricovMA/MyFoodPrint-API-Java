package com.foodprint.errors;

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

        public void setRequestedIngredient(String requestedIngredient){
            this.requestedIngredientString = requestedIngredient;
        }

        public String getMessage(){
            return this.message;
        }

        public int getCode(){
            return this.errorCode;
        }

        public String toString(){
            return this.message + " " + errorCode;
        }

    }

}
