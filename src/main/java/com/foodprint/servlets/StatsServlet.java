package com.foodprint.servlets;

import com.foodprint.interfaces.IServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(
        name = "StatsServlet",
        description = "Statistics about hosting env.",
        urlPatterns = {
                "/stats",
                "/stats/"
        },
        loadOnStartup = 1
)
public class StatsServlet extends HttpServlet implements IServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(getStatistics());
        System.gc();
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

    private static String getStatistics() {

        StringBuilder sb = new StringBuilder();

        sb.append("Available processors (cores): ").append(Runtime.getRuntime().availableProcessors());

        sb.append("\n");

        sb.append("Free memory (bytes): ").append(Runtime.getRuntime().freeMemory());

        sb.append("\n");

        long maxMemory = Runtime.getRuntime().maxMemory();

        sb.append("Maximum memory (bytes): ").append(maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory);

        sb.append("\n");

        sb.append("Total memory (bytes): ").append(Runtime.getRuntime().totalMemory());

        sb.append("\n");

        File[] roots = File.listRoots();

        /* For each filesystem root, print some info */
        for (File root : roots) {
            sb.append("File system root: ").append(root.getAbsolutePath()).append("\n");
            sb.append("Total space (bytes): ").append(root.getTotalSpace()).append("\n");
            sb.append("Free space (bytes): ").append(root.getFreeSpace()).append("\n");
            sb.append("Usable space (bytes): ").append(root.getUsableSpace()).append("\n");
        }

        return sb.toString();
    }

}
