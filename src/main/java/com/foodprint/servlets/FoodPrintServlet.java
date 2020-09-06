package com.foodprint.servlets;

import com.foodprint.interfaces.IServlet;
import com.foodprint.response.FoodPrintResponse;
import com.foodprint.response.ResponseGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "FoodPrintServlet",
        description = "This servlet is the main backend servlet for the api. It does all the calculations" +
                " needed, and returns a FoodPrintResponse.",
        urlPatterns = {
                "/foodprint",
                "/foodprint/"
        }
)
public class FoodPrintServlet extends HttpServlet{

    private static final ResponseGenerator responseGenerator = new ResponseGenerator();
    private static final Logger logger = LogManager.getLogger(FoodPrintServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestedIngredients = "";
        FoodPrintResponse responseToReturn = null;
        try{
            requestedIngredients = request.getParameter("ingredients");

            long startTime = System.currentTimeMillis();

            responseToReturn = responseGenerator.generateResponse(requestedIngredients);

            if(responseToReturn.getStatus() == FoodPrintResponse.Status.SUCCESS){
                logger.info("Response successfully generated in {}ms.", System.currentTimeMillis()-startTime);
            }


        }catch (NullPointerException e){
            logger.warn("Something went wrong calculating:\"{}\".", requestedIngredients);
        }

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println(responseToReturn.toString());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

    }
}
