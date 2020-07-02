package com.foodprint.parser;

import com.foodprint.interfaces.IParser;

public class ParserFactory {

    public static IParser getParser(String givenString){
        if (givenString.contains("ricardocuisine.com")) {
            return new RicParser();
        }
        return null;
    }

}
