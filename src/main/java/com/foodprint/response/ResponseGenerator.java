package com.foodprint.response;

import com.foodprint.interfaces.IDatabase;
import com.foodprint.database.LocalDatabase;
import com.foodprint.interfaces.Request;
import com.foodprint.interfaces.Response;
import com.foodprint.util.StringParser;

public class ResponseGenerator {

    private static IDatabase database = LocalDatabase.getInstance();
    private static StringParser stringParser = StringParser.getInstance();

    public Response generateFoodPrintResponse(Request request){


        return null ;
    }

    private Request generateRequest(String requestString){

        return null;
    }
}
