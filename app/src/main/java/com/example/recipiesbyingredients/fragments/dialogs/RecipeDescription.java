package com.example.recipiesbyingredients.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.recipiesbyingredients.R;
import com.example.recipiesbyingredients.fragments.IngredientsFragment;
import com.example.recipiesbyingredients.fragments.viewadapter.MyIngredientRecyclerViewAdapter;
import com.example.recipiesbyingredients.models.DatabaseHelper;
import com.example.recipiesbyingredients.models.Ingredient;
import com.example.recipiesbyingredients.models.Recipie;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeDescription#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDescription extends DialogFragment {
    private int mColumnCount = 2;
//    private IngredientsFragment.OnIngredientsListFragmentInteractionListener mListener;
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_recipe_description, null);

        SharedPreferences pref = getActivity().getSharedPreferences("MyPref",MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        final Gson gson = new Gson();

        //Utilizing XML id's to create variables for manipulation later.
        final Bundle args = this.getArguments();
        final TextView recipe_name = (TextView) view.findViewById(R.id.recipe_Name);
        final ImageView recipe_image = (ImageView) view.findViewById(R.id.recipe_Image);
        final TextView recipe_instructions = (TextView) view.findViewById(R.id.recipe_Instructions);
        final TextView recipe_ingredients = (TextView) view.findViewById(R.id.ingredients_list);
        //final RecyclerView recipe_Results = (RecyclerView) view.findViewById(R.id.ingredient_List);
        Button save = (Button) view.findViewById(R.id.button_save);

        //Setting movement for user to scroll
        recipe_ingredients.setMovementMethod(new ScrollingMovementMethod());
        recipe_instructions.setMovementMethod(new ScrollingMovementMethod());

        //Sets recipe name.
        recipe_name.setText(args.getString("name"));


        String ingredients = "";
        String instructions = "";

        for (String s:args.getStringArrayList("ingredients")) {
            ingredients += s + "\n";
        }
        recipe_ingredients.setText(ingredients);

        ArrayList<String> instructions_n = args.getStringArrayList("instruction");

        for (int i = 0; i < instructions_n.size(); i++) {
            instructions += (i + 1) + ": " + instructions_n.get(i) + "\n";
        }
        recipe_instructions.setText(instructions);

        //TODO Add saving Functionality for Button
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String json = gson.toJson(args.getSerializable("recipe"));
                editor.putString(args.getString("name")+' '+"recipe", json);
                editor.commit();
                close();
            };
        });



        builder.setView(view);
        return builder.create();


    }

    public void close() {
        this.dismiss();
    }
}
