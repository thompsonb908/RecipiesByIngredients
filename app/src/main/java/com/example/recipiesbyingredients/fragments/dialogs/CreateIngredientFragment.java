package com.example.recipiesbyingredients.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.recipiesbyingredients.R;
import com.example.recipiesbyingredients.fragments.IngredientsFragment;
import com.example.recipiesbyingredients.models.DatabaseHelper;
import com.example.recipiesbyingredients.models.Ingredient;


public class CreateIngredientFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_create_ingredient, null);

        Button submit = (Button) view.findViewById(R.id.createIngredient);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText nameText = (EditText) view.findViewById(R.id.newIngredientName);
                final EditText quantityText = (EditText) view.findViewById(R.id.newIngredientQuantity);

                DatabaseHelper db = DatabaseHelper.getInstance(v.getContext());

                String name = nameText.getText().toString();
                String quantityString = quantityText.getText().toString();
                String quantity = quantityString;
                db.createIngredient(new Ingredient(name, quantity));

                closeFragment();
            }
        });

        builder.setView(view);



        return builder.create();
    }

    public void closeFragment() {
        this.dismiss();
    }
}