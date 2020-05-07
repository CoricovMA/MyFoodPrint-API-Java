package com.foodprint.servlets;

import com.foodprint.interfaces.IServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {
                "/ping/",
                "/ping"
        },
        name = "PingServlet",
        description = "Used to check if servlet is up",
        loadOnStartup = 1
)
public class PingServlet extends HttpServlet implements IServlet {

    private static final Logger logger = LogManager.getLogger(PingServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("Pong.");
        logger.info("Server was pinged. We ponged back.");
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
