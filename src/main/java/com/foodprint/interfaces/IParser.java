package com.foodprint.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public interface IParser {

    static final Logger logger = LogManager.getLogger(IParser.class);

    List<Object> parseIngredients(String url);

}
