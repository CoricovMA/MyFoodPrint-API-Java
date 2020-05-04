package com.foodprint;

import com.foodprint.database.Database;
import com.foodprint.response.FoodPrintRequest;
import com.foodprint.response.FoodPrintResponse;
import com.foodprint.util.StringParser;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testin {

    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(testin.class);
        long startTime = System.currentTimeMillis();
        logger.info("Hello");
        String ingredientString = "1kg chopped beef";
        MaxentTagger tagger = new MaxentTagger();
        tagger.tagString("hello");
//        String taggedString = StringParser.getTagger().tagString(ingredientString);
//        System.out.println(taggedString);
//        System.out.println(StringParser.getInstance().getUnitsFromString(ingredientString));
//        System.out.println(StringParser.getInstance().getVolumeFromString(ingredientString));
//        System.out.println(System.currentTimeMillis()-startTime + "ms");
    }
}
