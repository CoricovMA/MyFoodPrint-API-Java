package com.foodprint.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@WebServlet(
        name = "LogServlet",
        urlPatterns = {"/logs/*"},
        loadOnStartup = 1
)
public class LogServlet extends HttpServlet implements IServlet {

    private static File logDirectory;
    private static final String logDirectoryPath = "/var/lib/jetty/logs";
    private static final Logger logger = LogManager.getLogger(LogServlet.class);

    public void init() {
        logger.info("Initializing Log servlet.");
        try {
            logDirectory = new File(logDirectoryPath);
            logDirectory.mkdir();
        }catch (Exception e){
            logger.warn("Error creating log directory.");
        }
        logger.info("Initializing log directory at {}", logDirectoryPath);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String servletPath = request.getServletPath();
        String requestUri = request.getRequestURI();

        try {
            String desiredLogs = requestUri.substring(requestUri.indexOf(servletPath) + servletPath.length() + 1);

            if (desiredLogs.contains("all")) {

                getAllLogs(request, response);

            } else if (desiredLogs.contains("date")) {

                String date = desiredLogs.substring(desiredLogs.indexOf("/") + 1);

                response.getWriter().println("</br>date: " + date + "</br>");

                getDateLogs(request, response, date);
            }
        } catch (StringIndexOutOfBoundsException e) {
            logger.warn("Error fetching logs {}", request.getServletPath());
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

    private void getAllLogs(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Scanner scanner = new Scanner(new FileReader("/var/lib/jetty/logs/app.log"));
        out.flush();

        while(scanner.hasNext()){
            out.println(scanner.nextLine() + "</br>");
        }
    }

    private void getDateLogs(HttpServletRequest request, HttpServletResponse response, String date) throws IOException {
        PrintWriter out = response.getWriter();
        out.flush();
    }
}
