package com.foodprint;

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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class testin {

    public static void main(String[] args){
        try {

            try {


                FileInputStream serviceAccount =
                        new FileInputStream("C:\\Users\\AMC\\IdeaProjects\\MyFoodPrint-API-Java\\src\\main\\resources\\conf\\myfoodprint_firebase.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://myfoodprint-29dfa.firebaseio.com")
                        .build();

                FirebaseApp.initializeApp(options);


            } catch (Exception e) {


            }

            Firestore database = FirestoreClient.getFirestore();

            CollectionReference ingredients = database.collection("ingredients");
            CollectionReference users = database.collection("users");


            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\AMC\\IdeaProjects\\MyFoodPrint-API-Java\\src\\main\\resources\\food_json\\all_ingredients.json");
            Scanner scanner = new Scanner(fileInputStream);
            StringBuilder sb = new StringBuilder();
            while(scanner.hasNext()){
                sb.append(scanner.nextLine().trim());
            }

            JSONArray jsonObject = new JSONObject(sb.toString()).getJSONArray("ingredients");
            System.out.println(jsonObject.length());
            for(int i = 0; i < jsonObject.length(); i++){
                System.out.println(jsonObject.get(i));
                Ingredient a = new Ingredient(jsonObject.getJSONObject(i));
                ApiFuture<WriteResult> res;
                try {
                    res = ingredients.document(a.getName().trim().toLowerCase()).set(ObjectParser.getObjectMap(a));
                    System.out.println("Update time : " + res.get().getUpdateTime());
                }catch (IllegalArgumentException e){
                    System.out.println("There was an error with ingredient " + a);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
