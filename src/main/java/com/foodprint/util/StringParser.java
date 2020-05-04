package com.foodprint.util;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.tagger.*;

public class StringParser {

    public enum LANGUAGE {
        AMERICAN_ENGLISH,
        BRITISH_ENGLISH
    }

    private static Map<String, Double> volumes = new HashMap<>();

    private static Logger logger = LogManager.getLogger(StringParser.class);
    private static StringParser instance;
    private static JLanguageTool languageTool;
    private static LANGUAGE currentLanguage;
    private static MaxentTagger tagger = new MaxentTagger();

    private StringParser() {
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

    public static MaxentTagger getTagger(){
        return tagger;
    }

    private void _setLanguage(LANGUAGE desiredLanguage) {
        currentLanguage = desiredLanguage;
    }

    public static StringParser getInstance() {
        if (instance == null) {
            instance = new StringParser();
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

    public String getUnitsFromString(String givenIngredientString) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("1 kg chicken");
        if (m.find())
            return m.group();
        else
            return null;
    }

    public String getVolumeFromString(String givenIngredientString) {
        String[] stringAsArray = removeIntegersFromString(givenIngredientString).split(" ");
        String volumeFromString = null;
        for(String word: stringAsArray){
            if (volumes.containsKey(word)){
                volumeFromString = word;
                break;
            }
        }

        return volumeFromString;
    }

    public static double getQuantityFromVol(String givenVol){
        return volumes.get(givenVol);
    }

    public String removeIntegersFromString(String givenString){
        return givenString.replaceAll("\\d", "");
    }

}
