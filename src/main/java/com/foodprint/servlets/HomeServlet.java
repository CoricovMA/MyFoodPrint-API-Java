package com.foodprint.servlets;

import com.foodprint.database.LocalDatabase;
import com.foodprint.interfaces.IDatabase;
import com.foodprint.interfaces.IServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        name = "HomeServlet",
        urlPatterns = {"/"}
)
public class HomeServlet extends HttpServlet implements IServlet {

    /**
     * Warming up the DB(Map)
     * The first request takes some time, so a request to the db, whatever it might be
     * just a ping will get it ready to go
     */
    public void init(){
        IDatabase database = LocalDatabase.getInstance();
        database.getIngredient("chicken");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
