package com.foodprint.parser;

import com.foodprint.interfaces.IParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RicParser implements IParser {

    @Override
    public List<Object> parseIngredients(String url) {
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            logger.error("Error connecting to url {}", url);
        }

        List<Object> ingredientsParsed = new ArrayList<>();

        assert doc != null;

        for (Element element: doc.getElementById("formIngredients").getElementsByTag("li")){

            ingredientsParsed.add(element.text());

        }

        return ingredientsParsed;
    }
}
