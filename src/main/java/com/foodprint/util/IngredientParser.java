package com.foodprint.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientParser {

    public enum LANGUAGE {
        AMERICAN_ENGLISH,
        BRITISH_ENGLISH
    }

    private static Map<String, Double> volumes = new HashMap<>();

    private static Logger logger = LogManager.getLogger(IngredientParser.class);
    private static IngredientParser instance;
    private static JLanguageTool languageTool;
    private static LANGUAGE currentLanguage;

    private IngredientParser() {
        languageTool = new JLanguageTool(new AmericanEnglish());
        currentLanguage = LANGUAGE.AMERICAN_ENGLISH;
        logger.info("Initialized language tool to American english (default).");
        logger.info("Doing first check for good subsequent speed.");
        try {
            languageTool.check("test");
        } catch (IOException e) {
            logger.info("Error doing first check. ", e);
        }

        volumes.put("ml", 0.001);
        volumes.put("g", 0.001);
        volumes.put("grams", 0.001);
        volumes.put("tsp", 0.005);
        volumes.put("tbsp", 0.015);
        volumes.put("oz", 0.028);
        volumes.put("cup", 0.23658);
        volumes.put("cups", 0.236588);
        volumes.put("kg", 1.0);
        volumes.put("lb", 0.453592);
        volumes.put("pinch", 0.00036);
        volumes.put("kilogram", 1.0);
        volumes.put("kilograms", 1.0);
        volumes.put("pound", 0.453592);
        volumes.put("pounds", 0.453592);
        volumes.put("cloves", 0.013);
        volumes.put("clove", 0.013);
    }

    public void setLanguage(LANGUAGE desiredLanguage) {
        logger.info("Setting language tool to {}", desiredLanguage);
        switch (desiredLanguage) {
            case AMERICAN_ENGLISH:
                if (desiredLanguage == currentLanguage) {
                    return;
                }
                languageTool = new JLanguageTool(new AmericanEnglish());
                break;
            case BRITISH_ENGLISH:
                if (desiredLanguage == currentLanguage) {
                    return;
                }
                languageTool = new JLanguageTool(new BritishEnglish());
                break;
            default:
                languageTool = new JLanguageTool(new AmericanEnglish());
                break;
        }

        this._setLanguage(desiredLanguage);
    }

    private void _setLanguage(LANGUAGE desiredLanguage) {
        currentLanguage = desiredLanguage;
    }

    public static IngredientParser getInstance() {
        if (instance == null) {
            instance = new IngredientParser();
        }
        return instance;
    }

    /**
     * Checks a given string for spelling errors, and returns
     * suggested replacements in case there are errors
     *
     * @param givenString String to check for spelling errors
     * @return list of possible replacements
     */
    public List<String> getSpellingMatches(String givenString) {
        List<String> matches = null;
        try {
            matches = languageTool.check(givenString).get(0).getSuggestedReplacements();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matches;
    }

    public double getQuantityFromString(String givenIngredientString) {
        String[] ingredientStringArr = givenIngredientString.split(" ");
        double toReturn = 0.0;
        for(String word: ingredientStringArr){
            try {
                toReturn = Double.parseDouble(word);
                break;
            } catch (NumberFormatException e){

            }
        }

        return toReturn;
    }

    public String getVolumeFromString(String givenIngredientString) {
        String[] stringAsArray = removeQuantityFromString(givenIngredientString).split(" ");
        String volumeFromString = null;
        for(String word: stringAsArray){
            if (volumes.containsKey(word)){
                volumeFromString = word;
                break;
            }
        }

        return volumeFromString;
    }

    public double getQuantityFromVol(String givenVol){
        return volumes.get(givenVol);
    }

    public String removeQuantityFromString(String givenString){
        double quantity = getQuantityFromString(givenString);

        if(hasParentheses(givenString)){
            return givenString.substring(givenString.indexOf(")")+1).trim();
        }

        if(givenString.contains(String.valueOf(quantity))){
            givenString = givenString.replace(String.valueOf(quantity), "");
        }else if(givenString.contains(String.valueOf((int)Math.floor(quantity)))){
            givenString = givenString.replace(String.valueOf((int)Math.floor(quantity)), "");
        }else if(quantity % 1 != 0.0){
            givenString = givenString.replace(String.valueOf(Double.doubleToLongBits(quantity)), "");
        }

        return givenString.trim();
    }

    public static boolean hasParentheses(String givenIngredient) {
        return givenIngredient.contains("(") && givenIngredient.contains(")");
    }

}
