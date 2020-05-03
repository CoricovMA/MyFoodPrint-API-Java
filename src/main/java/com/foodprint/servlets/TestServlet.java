package com.foodprint.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

    /*
    TODO Add object which handles ingredients parsing and shit :)
     */


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

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

    }

    private void doCalculate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("calculating");
        response.getWriter().println("</br>");
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
