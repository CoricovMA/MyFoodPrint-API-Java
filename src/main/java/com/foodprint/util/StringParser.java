package com.foodprint.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StringParser {

    public enum LANGUAGE{
        AMERICAN_ENGLISH,
        BRITISH_ENGLISH
    }
    private static Logger logger = LogManager.getLogger(StringParser.class);
    private static StringParser instance;
    private static JLanguageTool languageTool;
    private static LANGUAGE currentLanguage;

    private StringParser(){
        languageTool = new JLanguageTool(new AmericanEnglish());
        currentLanguage = LANGUAGE.AMERICAN_ENGLISH;
        logger.info("Initialized language tool to American english (default).");
        logger.info("Doing check to instantiate object and make consequent checks faster.");
        try {
            languageTool.check("test");
        } catch (IOException e) {
            logger.info("Error doing first check. ", e);
        }
    }

    public void setLanguage(LANGUAGE desiredLanguage){
        logger.info("Setting language tool to {}", desiredLanguage);
        switch(desiredLanguage){
            case AMERICAN_ENGLISH:
                if(desiredLanguage == currentLanguage){
                    return;
                }
                languageTool = new JLanguageTool(new AmericanEnglish());
                currentLanguage = LANGUAGE.AMERICAN_ENGLISH;
                break;
            case BRITISH_ENGLISH:
                if(desiredLanguage == currentLanguage){
                    return;
                }
                languageTool = new JLanguageTool(new BritishEnglish());
                currentLanguage = LANGUAGE.BRITISH_ENGLISH;
                break;
            default:
                languageTool = new JLanguageTool(new AmericanEnglish());
                currentLanguage = LANGUAGE.AMERICAN_ENGLISH;
                break;
        }
    }

    public static StringParser getInstance(){
        if(instance == null){
            instance = new StringParser();
        }
        return instance;
    }

    /**
     *
     * @param givenString String to check for spelling errors
     * @return list of possible replacements
     */
    public List getMatches(String givenString){
        AtomicReference<List> matches = null;
        Thread t = new Thread(() -> {
            try {
                matches.set(languageTool.check(givenString).get(0).getSuggestedReplacements());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return (List) matches;
    }
}
