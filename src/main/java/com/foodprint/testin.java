package com.foodprint;

import com.foodprint.util.StringParser;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

public class testin {

    public static void main(String[] args) throws InterruptedException {
        StringParser parse = StringParser.getInstance();
        // comment in to use statistical ngram data:
        //langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
        List matches = null;
        Object [] mat;
        Thread.sleep(1);
        String [] checkin = {"1 kg chiken", "1 kg beeef", "1 tbsp cabage"};
        for(int i = 0 ; i < 2000; i ++) {
            for (String ingr : checkin) {
                long startTime = System.currentTimeMillis();
                matches = parse.getMatches(ingr);
                System.out.println((float) (System.currentTimeMillis() - startTime));
            }
        }


        System.out.println("hello world");
    }
}
