package com.foodprint.interfaces;


import com.foodprint.database.LocalDatabase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public interface IServlet {

    static File logFile = new File("/var/lib/jetty/logs/app.log");

    IDatabase database = LocalDatabase.getInstance();

    void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void doPost(HttpServletRequest request, HttpServletResponse response);

    void doPut(HttpServletRequest request, HttpServletResponse response);

    void doDelete(HttpServletRequest request, HttpServletResponse response);

    void doClear(HttpServletResponse response) throws IOException;
}
