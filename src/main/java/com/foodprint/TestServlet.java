package com.foodprint;

import com.foodprint.Ingredients.Ingredient;
import com.foodprint.database.Database;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

@WebServlet(
        name = "MyFoodPrint-API",
        description = "MyFoodPrint API using HttpServlet",
        urlPatterns = {
                "/calculate/*",
                "/list/*",
                "/"
        },
        loadOnStartup = 1
)
public class TestServlet extends HttpServlet {

    /*
    TODO Add object which handles ingredients parsing and shit :)
     */

    private static Database database;

    @Override
    public void init() {
        database = Database.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String action = request.getServletPath();

        switch (action) {
            case "/":
                doHome(request, response);
                break;
            case "/calculate":
                doCalculate(request, response);
                break;
            case "/list":
                doList(request, response);
                break;
            default:
                doError(response);
                break;
        }

        PrintWriter out = response.getWriter();
        out.println("</br>");
        out.println(request.getServletPath());
        out.println("</br>");
        out.println(request.getServletContext().getContextPath());
        out.println("</br>");
        out.println(request.getRequestURL());
        out.println("</br>");
        out.println(request.getRequestURI());
        out.close();
    }

    private void doCalculate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("calculating");
        response.getWriter().println(System.getProperty("user.dir"));
        response.getWriter().println("</br>");
        PrintWriter out = response.getWriter();
        Ingredient ingr = new Ingredient("hi", "there");
        try {
            database.insertIngredient(ingr, request, response);
        } catch (ExecutionException e) {
            out.println("Execution error \n" + e);
        } catch (InterruptedException e) {
            out.println("Interrupted error \n" + e);
        }
        response.getWriter().println(ingr);
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(System.getProperty("user.dir"));
        File f = new File("/var/lib/jetty/resources/myfoodprint_db_config.json");

    }

    private void doHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("home");
    }

    private void doError(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
