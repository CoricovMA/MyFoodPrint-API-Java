package com.foodprint.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Method chaining seemed like the correct thing to do in the case of this class
 */
public class FoodPrintResponse {

    public enum Status{
        ERROR,
        SUCCESS,

    }

    @JsonProperty("status")
    private Status status;

    @JsonProperty("request")
    private FoodPrintRequest request;

    @JsonProperty("total_calories")
    private int totalCalories;

    @JsonProperty("total_emissions")
    private int totalEmissions;

    @JsonProperty("carbon_score")
    private Float carbonScore;

    public FoodPrintResponse(){
        this.status = Status.SUCCESS;
        this.request = new FoodPrintRequest();
        this.totalCalories = 0;
        this.totalEmissions = 0;
        this.carbonScore = (float) 0;
    }

    public FoodPrintResponse setCarbonScore(Float carbonScore) {
        this.carbonScore = carbonScore;
        return this;
    }

    public FoodPrintResponse setStatus(Status status){
        this.status = status;
        return this;
    }

    public FoodPrintResponse setTotalCalories(int calories){
        this.totalCalories = calories;
        return this;
    }

    public FoodPrintResponse setTotalEmissions(int totalEmissions){
        this.totalEmissions = totalEmissions;
        return this;
    }

    public FoodPrintResponse doError(){
        this.status = Status.ERROR;
        return this;
    }

    /**
     * @param request food print request object
     * @return this object, with the request set
     */
    public FoodPrintResponse setRequest(FoodPrintRequest request){
        this.request = request;
        return this;
    }

}
