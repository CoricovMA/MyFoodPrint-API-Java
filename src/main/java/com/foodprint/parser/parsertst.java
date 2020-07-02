package com.foodprint.parser;

import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class parsertst {

    public static void main(String [] args){
        try {

            int table = 22;
            int category = 33;
            int size = 44;
            String url = String.format("https://services.runescape.com/m=hiscore/ranking.json?table=%s&category=%s&size=%s", table, category, size);
            System.out.println(url);

            Document doc = Jsoup.connect("https://services.runescape.com/m=hiscore/ranking.json?table=9&category=0&size=50").ignoreContentType(true).get();

            JSONObject json = new JSONObject().put("ranking", new JSONArray(doc.getElementsByTag("body").text()));

            System.out.println(json);

            for(Object object: json.getJSONArray("ranking")){
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
