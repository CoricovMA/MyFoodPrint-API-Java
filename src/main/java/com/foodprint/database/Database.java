package com.foodprint.database;

import com.foodprint.Ingredients.Ingredient;
import com.foodprint.util.ObjectParser;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Database {

    private static Database instance;

    private static final Logger logger = LogManager.getLogger();
    private CollectionReference ingredients;
    private CollectionReference users;
    private Firestore database;

    private Database() {
        logger.info("Instantiating database.");

        try {

            FileInputStream serviceAccount = new FileInputStream("/var/lib/jetty/resources/myfoodprint_db_config.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e) {

            logger.warn("Something went wrong while instantiating database. ", e);

        }

        database = FirestoreClient.getFirestore();

        ingredients = database.collection("ingredients");
        users = database.collection("users");

    }


    public static synchronized Database getInstance(){

        if(instance == null){
            instance = new Database();
        }

        return instance;
    }

    public void insertIngredient(Ingredient ingredient) throws ExecutionException, InterruptedException {
        System.out.println(ingredient);
        ApiFuture<DocumentReference> addedDocRef = ingredients.add(ObjectParser.getObjectMap(ingredient));
        addedDocRef.get();

    }

}
