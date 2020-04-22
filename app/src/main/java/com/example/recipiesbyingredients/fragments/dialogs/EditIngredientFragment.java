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

import com.example.recipiesbyingredients.R;
import com.example.recipiesbyingredients.models.DatabaseHelper;
import com.example.recipiesbyingredients.models.Ingredient;

public class EditIngredientFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_edit_ingredient, null);

        Button update = (Button) view.findViewById(R.id.update_ingredient);
        Button delete = (Button) view.findViewById(R.id.delete_ingredient);

        final Bundle args = this.getArguments();

        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText quantityUpdate = (EditText) view.findViewById(R.id.updateIngredientQuantity);
                String quantity = quantityUpdate.getText().toString();

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
                Ingredient ing = new Ingredient(args.getString("name"), "0");
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
}
