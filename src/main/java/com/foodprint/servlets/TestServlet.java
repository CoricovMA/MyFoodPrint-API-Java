package com.foodprint.servlets;

import com.foodprint.database.LocalDatabase;
import com.foodprint.interfaces.IServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scala.tools.nsc.classpath.FileUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@WebServlet(
        name = "MyFoodPrint-API",
        description = "MyFoodPrint API using HttpServlet",
        urlPatterns = {
                "/calculate/*",
                "/list/*"
        },
        loadOnStartup = 1
)
public class TestServlet extends HttpServlet implements IServlet {

    private static final Logger logger = LogManager.getLogger(TestServlet.class);

    @Override
    public void init(){
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String action = request.getServletPath();
        response.getWriter().write("Path: " + action + "</br>");
//        response.getWriter().write("Path after uri: " + request.getRequestURI().substring((request.getRequestURI().indexOf(action) + action.length()+1)) + "</br>");
        response.getWriter().write("Path: " + request.getPathTranslated() + "</br>");
        response.getWriter().write("Path: " + request.getRequestURI() + "</br>");
        response.getWriter().write("Path: " + request.getRequestURL() + "</br>");
        response.getWriter().write("servlet " + getServletContext().getContextPath() + "</br>");

        File f = new File("WEB-INF/classes/food_json/all_ingredients.json");

        Scanner scanner = new Scanner(f);

        while(scanner.hasNext()){
            response.getWriter().println(scanner.nextLine());
        }

        scanner.close();

        response.getWriter().println("Here" + " "+ f.getAbsolutePath() + "</br>");
        response.getWriter().println(f);
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
    public void doClear(HttpServletResponse response) {

    }

    private void doCalculate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("calculating");
        response.getWriter().println(database.getIngredient("abalone").toString());
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(System.getProperty("user.dir"));
    }

    private void doHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("home");
    }

    private void doError(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
