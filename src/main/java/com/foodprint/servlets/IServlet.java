package com.foodprint.servlets;

import com.foodprint.database.Database;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IServlet {

    Database database = Database.getInstance();

    void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void doPost(HttpServletRequest request, HttpServletResponse response);

    void doPut(HttpServletRequest request, HttpServletResponse response);

    void doDelete(HttpServletRequest request, HttpServletResponse response);
}
