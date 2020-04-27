package com.foodprint;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.client.*;

@WebServlet("/calculate/*")
public class TestServlet extends HttpServlet {

    JSONObject jsonObject = new JSONObject();
    Map<String, String> map = new HashMap<>();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpGet get = new HttpGet();
        response.setContentType("text/html");
        map.put("hello", "world");
        jsonObject.append("test", map);
        PrintWriter out = response.getWriter();
        out.println(request.getRequestURI());
        out.println("</br>");
        out.println(jsonObject.toString(4));
        out.close();
    }

}
