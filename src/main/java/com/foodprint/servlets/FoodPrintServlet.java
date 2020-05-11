package com.foodprint.servlets;

import com.foodprint.database.LocalDatabase;
import com.foodprint.interfaces.IDatabase;
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
import java.io.PrintWriter;

@WebServlet(
        name = "FoodPrintServlet",
        description = "This servlet is the main backend servlet for the api. It does all the calculations" +
                " needed, and returns a FoodPrintResponse.",
        urlPatterns = {
                "/foodprint",
                "/foodprint/"
        }
)
public class FoodPrintServlet extends HttpServlet implements IServlet {

    private static final ResponseGenerator responseGenerator = new ResponseGenerator();
    private static final Logger logger = LogManager.getLogger(FoodPrintServlet.class);

    /**
     * Warming up the DB(Map)
     * The first request takes some time, so a request to the db, whatever it might be
     * just a ping
     * will get it ready to go
     */
    public void init(){
        IDatabase database = LocalDatabase.getInstance();
        database.getIngredient("chicken");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestedIngredients = "";

        try{
            requestedIngredients = request.getParameter("ingredients");

            long startTime = System.currentTimeMillis();

            FoodPrintResponse responseToReturn = responseGenerator.generateResponse(requestedIngredients);

            if(responseToReturn.getStatus() == FoodPrintResponse.Status.SUCCESS){
                logger.info("Response successfully generated in {}ms.", System.currentTimeMillis()-startTime);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().println(responseToReturn.toString());
        }catch (NullPointerException e){
            logger.warn("Something went wrong calculating:\"{}\".", requestedIngredients);
        }

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

    @Override
    public void doClear(HttpServletResponse response) throws IOException {

    }
}
