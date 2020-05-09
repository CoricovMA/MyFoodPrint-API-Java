package com.foodprint.servlets;

import com.foodprint.interfaces.IServlet;
import com.foodprint.response.ResponseGenerator;

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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        doClear(response);
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
        PrintWriter out = response.getWriter();
        out.flush();
        out.close();
    }
}
