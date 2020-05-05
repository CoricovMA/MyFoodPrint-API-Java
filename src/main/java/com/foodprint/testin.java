package com.foodprint;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class testin {

    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(testin.class);
        long startTime = System.currentTimeMillis();
        logger.info("Hello");
        String ingredientString = "1kg chopped beef";
        MaxentTagger tagger = new MaxentTagger("C:\\Users\\AMC\\IdeaProjects\\MyFoodPrint-API-Java\\src\\main\\java\\com\\foodprint\\tagger\\models\\bidirectional-distsim-wsj-0-18.tagger");
        tagger.tagString("hello");
//        String taggedString = StringParser.getTagger().tagString(ingredientString);
//        System.out.println(taggedString);
//        System.out.println(StringParser.getInstance().getUnitsFromString(ingredientString));
//        System.out.println(StringParser.getInstance().getVolumeFromString(ingredientString));
//        System.out.println(System.currentTimeMillis()-startTime + "ms");
    }
}
