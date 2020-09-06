package com.foodprint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodprint.Ingredients.IngredientResponse;
import com.foodprint.interfaces.AbstractFoodPrintObject;
import com.foodprint.errors.FoodPrintErrors;
import com.foodprint.interfaces.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Method chaining seemed like the correct thing to do in the case of this class
 */
public class FoodPrintResponse extends AbstractFoodPrintObject implements Response {

    public enum Status {
        ERROR,
        SUCCESS
    }

    /**
     * Status of the request
     */
    @JsonProperty("status")
    private Status status;

    /**
     * Original request object
     */
    @JsonProperty("request")
    private FoodPrintRequest request;

    /**
     * Any errors that happened
     */
    @JsonProperty("errors")
    private List<FoodPrintErrors.ERROR> errors;

    /**
     * Response ingredients
     */
    @JsonProperty("ingredients")
    private List<IngredientResponse> ingredientResponseList;

    /**
     * Sum of calories of all ingredients
     */
    @JsonProperty("total_calories")
    private double totalCalories;

    /**
     * Sum of emissions of all ingredients
     */
    @JsonProperty("total_emissions")
    private double totalEmissions;

    /**
     * Carbon score
     * Emissions / Calories * 1000
     */
    @JsonProperty("carbon_score")
    private double carbonScore;

    public FoodPrintResponse() {
        this.status = Status.SUCCESS;
        this.totalCalories = 0;
        this.totalEmissions = 0;
        this.carbonScore = (double) 0;
    }

    public FoodPrintResponse setCarbonScore(double carbonScore) {
        this.carbonScore = carbonScore;
        return this;
    }

    public FoodPrintResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public FoodPrintResponse setTotalCalories(double calories) {
        this.totalCalories = calories;
        return this;
    }

    public FoodPrintResponse setTotalEmissions(double totalEmissions) {
        this.totalEmissions = totalEmissions;
        return this;
    }

    public FoodPrintResponse doError() {
        this.status = Status.ERROR;
        return this;
    }

    /**
     * @param request food print request object
     * @return this object, with the request set
     */
    public FoodPrintResponse setRequest(FoodPrintRequest request) {
        this.request = request;
        return this;
    }

    public void setIngredientsResponseList(List<IngredientResponse> responseList) {
        this.ingredientResponseList = responseList;
        this.calculateTotals();
    }

    public void addIngredientResponse(IngredientResponse ingredientResponse){
        if(this.ingredientResponseList == null){
            this.ingredientResponseList = new ArrayList<>();
        }
        
        this.ingredientResponseList.add(ingredientResponse);
    }

    public Status getStatus() {
        return status;
    }

    public FoodPrintRequest getRequest() {
        return request;
    }

    public List<IngredientResponse> getIngredientResponseList() {
        return ingredientResponseList;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public double getTotalEmissions() {
        return totalEmissions;
    }

    public double getCarbonScore() {
        return carbonScore;
    }

    private void calculateTotals(){
        double tempTotalEmissions = 0;
        double tempTotalCalories = 0;

        for(IngredientResponse response: ingredientResponseList){

            tempTotalEmissions += response.getEmissions();

            tempTotalCalories += response.getCalories();

        }

        this.totalCalories = tempTotalCalories;
        this.totalEmissions = tempTotalEmissions;
    }

    public void addErrors(FoodPrintErrors.ERROR ... errorArgs){
        if(this.errors == null){
            errors = new ArrayList<>();
        }
        errors.addAll(Arrays.asList(errorArgs));
    }

}
