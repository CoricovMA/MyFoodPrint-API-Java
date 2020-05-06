package com.foodprint.database;//package com.foodprint.database;
//
//import com.foodprint.Ingredients.Ingredient;
//import com.google.api.core.ApiFuture;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.firestore.CollectionReference;
//import com.google.cloud.firestore.DocumentReference;
//import com.google.cloud.firestore.DocumentSnapshot;
//import com.google.cloud.firestore.Firestore;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.cloud.FirestoreClient;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.json.JSONObject;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.concurrent.ExecutionException;
//
//public class Database {
//
//    private static Database instance;
//
//    private static final Logger logger = LogManager.getLogger();
//    private CollectionReference ingredients;
//    private CollectionReference users;
//    private Firestore database;
//
//    private Database() {
//        logger.info("Instantiating database.");
//
//        try {
//
//
//            FileInputStream serviceAccount =
//                    new FileInputStream("/var/lib/jetty/resources/myfoodprint_firebase.json");
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://myfoodprint-29dfa.firebaseio.com")
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//
//
//        } catch (Exception e) {
//
//            logger.warn("Something went wrong while instantiating database. ", e);
//
//        }
//
//        database = FirestoreClient.getFirestore();
//
//        ingredients = database.collection("ingredients");
//        users = database.collection("users");
//
//    }
//
//
//    public static synchronized Database getInstance() {
//        if (instance == null) {
//            instance = new Database();
//        }
//
//        return instance;
//    }
//
//    public void reInit() {
//        instance = null;
//        instance = new Database();
//    }
//
//    public Ingredient getIngredient(String name) throws IOException {
//        logger.info("Fetching ingredient \"{}\".", name);
//        Ingredient ingredientToReturn = null;
//
//        DocumentReference ingredientReference = ingredients.document(name);
//
//        ApiFuture<DocumentSnapshot> future = ingredientReference.get();
//
//        DocumentSnapshot ingredientDoc = null;
//
//        long startTime = System.currentTimeMillis();
//        long endTime = 0;
//        try {
//            ingredientDoc = future.get();
//            endTime = System.currentTimeMillis()-startTime;
//        } catch (InterruptedException e) {
//            logger.warn("Interrupted exception while getting ingredient.", e);
//        } catch (ExecutionException e) {
//            logger.warn("Execution exception while getting ingredient.", e);
//        }
//
//        if (ingredientDoc != null) {
//            JSONObject docJson = new JSONObject(ingredientDoc.getData());
//            ingredientToReturn = new Ingredient(docJson);
//        }
//
//        logger.info("Ingredient \"{}\" fetched successfully in {} ms.", name, (endTime));
//        return ingredientToReturn;
//    }
//
//
//}


public class Database{

}