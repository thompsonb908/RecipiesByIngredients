package com.example.recipiesbyingredients.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipiesbyingredients.R;
import com.example.recipiesbyingredients.fragments.dialogs.CreateIngredientFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;
import java.util.TimerTask;

public class IngredientsPageFragment extends Fragment {

    Timer refresher = null;
    TimerTask refresh = new TimerTask() {
        @Override
        public void run() {
            getFragmentManager().beginTransaction().detach(IngredientsPageFragment.this).attach(IngredientsPageFragment.this).commit();
            Log.d("Refresh", "Refreshing Ingredients List");
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        if(refresher == null) {
            Log.d("Timers", "Ingredient Refresh Started");
            refresher = new Timer();
            refresher.schedule(refresh, 0, 1000);
        }

        FloatingActionButton newIngredient = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        newIngredient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DialogFragment dialog = new CreateIngredientFragment();

                dialog.show(getFragmentManager(), "NewIng");
            }
        });

//        FloatingActionButton refresh = (FloatingActionButton) view.findViewById(R.id.floatingActionButton2);
//        refresh.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().detach(IngredientsPageFragment.this).attach(IngredientsPageFragment.this).commit();
//            }
//        });

        return view;
    }
}
