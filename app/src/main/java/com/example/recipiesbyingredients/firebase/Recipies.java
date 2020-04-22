package com.example.recipiesbyingredients.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.recipiesbyingredients.models.Ingredient;
import com.example.recipiesbyingredients.models.Recipie;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Recipies {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static List<Recipie> search(String query) {
        Task<QuerySnapshot> task = db.collection("recipes").whereGreaterThan("name", query).get();

        while(!task.isComplete());

        List<Recipie> recipies = new ArrayList<Recipie>();
        Log.d("DB", "QUERYING DATABASE");
        QuerySnapshot snap = task.getResult();

        for(DocumentSnapshot document : snap.getDocuments()){
            Log.d("DB", document.getId());
            Map<String, Object> data = document.getData();

            List<Ingredient> ingredients = new ArrayList<Ingredient>();
            for(Map<String, Object> ingredient : (ArrayList<Map<String, Object>>) data.get("ingredients")) {
                ingredients.add(new Ingredient((String) ingredient.get("name"), (String) ingredient.get("quantity")));
            }

            recipies.add(new Recipie((String) data.get("name"), ingredients, (ArrayList<String>) data.get("instructions"), (String) data.get("img")));
        }

        return recipies;
    }

    public static void addNewRecipie(Recipie recipie) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", recipie.getName());
        data.put("img", recipie.getImgURL());
        data.put("instructions", recipie.getInstructions());

        List<Map<String, Object>> ingredients = new ArrayList<>();
        for(Ingredient ing : recipie.getIngredients()) {
            Map<String, Object> ingredient = new HashMap<>();
            ingredient.put("name", ing.getName());
            ingredient.put("quantity", ing.getQuantity());
            ingredients.add(ingredient);
        }

        data.put("ingredient", ingredients);

        db.collection("recipes").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("DB", "DocumentSnapshot added with ID: " + documentReference.getId());
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                 Log.w("DB", "Error adding document", e);
            }
        });
    }
}
