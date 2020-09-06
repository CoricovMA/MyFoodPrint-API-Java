package com.foodprint.servlets;

import com.foodprint.interfaces.IServlet;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

@WebServlet(
        name = "LogServlet",
        urlPatterns = {"/logs/*"},
        loadOnStartup = 1
)
public class LogServlet extends HttpServlet implements IServlet {

    private static File logDirectory;
    private static final Logger logger = LogManager.getLogger(LogServlet.class);

//    public void init() {
//        logger.info("Initializing Log servlet.");
//        try {
//            URL url = LogServlet.class.getClassLoader().getResource("logs/app.log");
//            logDirectory = new File(url.toURI());
//            logDirectory.mkdir();
//        } catch (Exception e) {
//            logger.warn("Error creating log directory.");
//        }
//        logger.info("Initializing log directory.");
//    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String servletPath = request.getServletPath();
        String requestUri = request.getRequestURI();

        try {
            String desiredLogs = requestUri.substring(requestUri.indexOf(servletPath) + servletPath.length() + 1);

            if (desiredLogs.contains("all")) {

                getAllLogs(response);

            } else if (desiredLogs.contains("date")) {

                String date = desiredLogs.substring(desiredLogs.indexOf("/") + 1);

                response.getWriter().println("</br>date: " + date + "</br>");

                getDateLogs(request, response, date);
            }
        } catch (StringIndexOutOfBoundsException e) {
            logger.warn("Error fetching logs {}", request.getServletPath());
        }

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

    private void getAllLogs(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        URL url = LogServlet.class.getClassLoader().getResource("logs/app.log");
        String data = null;

        StringBuilder sb = new StringBuilder();

        try {
            File f = new File(url.toURI());

            Scanner scanner = new Scanner(f);

            while(scanner.hasNext()){
                sb.append(scanner.nextLine());
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        out.println(sb.toString());
        out.println("hello world");
        out.println(url);
    }

    private void getDateLogs(HttpServletRequest request, HttpServletResponse response, String date) throws IOException {
        PrintWriter out = response.getWriter();
        out.flush();
    }
}
