package com.foodprint.database;

import com.foodprint.Ingredients.Ingredient;
import com.foodprint.util.ObjectParser;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
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


            FileInputStream serviceAccount =
                    new FileInputStream("/var/lib/jetty/resources/myfoodprint_firebase.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://myfoodprint-29dfa.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);


        } catch (Exception e) {

            logger.warn("Something went wrong while instantiating database. ", e);

        }

        database = FirestoreClient.getFirestore();

        ingredients = database.collection("ingredients");
        users = database.collection("users");

    }


    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void reInit(){
        instance = null;
        instance = new Database();
    }

    public void insertIngredient(Ingredient ingredient, HttpServletRequest request, HttpServletResponse response) throws ExecutionException, InterruptedException, IOException {
        System.out.println(ingredient);
        ApiFuture<WriteResult> future = users.document("LA").set(ObjectParser.getObjectMap(ingredient));
        response.getWriter().println("Update time : " + future.get().getUpdateTime());
    }

}
