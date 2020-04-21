package com.example.recipiesbyingredients.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipiesbyingredients.R;
import com.example.recipiesbyingredients.models.DatabaseHelper;
import com.example.recipiesbyingredients.models.Ingredient;
import com.example.recipiesbyingredients.models.Recipie;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeDescription#newInstance} factory method to
 * create an instance of this fragment.
 */
/*public class RecipeDescription extends DialogFragment {
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_recipe_description, null);

        //Importing
        final Bundle args = this.getArguments();
        final TextView recipe_name = view.findViewById(R.id.recipe_Name);
        final TextView recipe_instructions = view.findViewById(R.id.recipe_Instructions);
        final List<Ingredient> iValues;

        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText quantityUpdate = (EditText) view.findViewById(R.id.updateIngredientQuantity);
                int quantity = Integer.parseInt(quantityUpdate.getText().toString());

                DatabaseHelper db = DatabaseHelper.getInstance(v.getContext());

                Ingredient ing = new Ingredient(args.getString("name"), quantity);
                ing.setId(args.getInt("id"));

                db.updateIngredient(ing);

                close();
            };
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatabaseHelper db = DatabaseHelper.getInstance(v.getContext());
                Ingredient ing = new Ingredient(args.getString("name"), 0);
                ing.setId(args.getInt("id"));
                Log.d("DB", "Deleting " + ing.getName() + " ID: " + ing.getId());
                db.deleteIngredient(ing);
                close();
            };
        });

        builder.setView(view);
        return builder.create();
    }

    public void close() {
        this.dismiss();
    }
}*/
