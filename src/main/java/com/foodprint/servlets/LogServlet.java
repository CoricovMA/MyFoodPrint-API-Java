package com.foodprint.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "LogServlet",
        urlPatterns = {"/logs/*"},
        loadOnStartup = 1
)
public class LogServlet extends HttpServlet implements IServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("Logs </br>");
        String servletPath = request.getServletPath();
        String requestUri = request.getRequestURI();
        try {
            String desiredLogs = requestUri.substring(requestUri.indexOf(servletPath) + servletPath.length() + 1);
            response.getWriter().println(desiredLogs);
        } catch (StringIndexOutOfBoundsException e) {
            response.getWriter().println("</br> error");
        }

        response.getWriter().println(servletPath);
        response.getWriter().println(requestUri);
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
