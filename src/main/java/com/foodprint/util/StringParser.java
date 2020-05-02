package com.foodprint.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;

import java.io.IOException;
import java.util.List;

public class StringParser {

    public enum LANGUAGE {
        AMERICAN_ENGLISH,
        BRITISH_ENGLISH
    }

    private static Logger logger = LogManager.getLogger(StringParser.class);
    private static StringParser instance;
    private static JLanguageTool languageTool;
    private static LANGUAGE currentLanguage;

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

    private void _setLanguage(LANGUAGE desiredLanguage){
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


}
