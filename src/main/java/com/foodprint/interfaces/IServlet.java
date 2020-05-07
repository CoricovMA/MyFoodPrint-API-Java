package com.foodprint.interfaces;


import com.foodprint.interfaces.IDatabase;
import com.foodprint.database.LocalDatabase;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IServlet {

    IDatabase database = LocalDatabase.getInstance();

    void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void doPost(HttpServletRequest request, HttpServletResponse response);

    void doPut(HttpServletRequest request, HttpServletResponse response);

    void doDelete(HttpServletRequest request, HttpServletResponse response);

    void doClear(HttpServletResponse response) throws IOException;
}
