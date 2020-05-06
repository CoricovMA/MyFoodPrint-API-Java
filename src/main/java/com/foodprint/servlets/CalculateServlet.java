package com.foodprint.servlets;

import com.foodprint.interfaces.IServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CalculateServlet implements IServlet {


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
