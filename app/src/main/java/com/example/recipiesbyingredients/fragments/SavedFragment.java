package com.example.recipiesbyingredients.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipiesbyingredients.R;
import com.example.recipiesbyingredients.fragments.viewadapter.MyRecipeRecyclerViewAdapter;
import com.example.recipiesbyingredients.models.Recipie;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Map;

public class SavedFragment extends Fragment {

    private RecipeFragment.OnRecipeListFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        SharedPreferences pref = getActivity().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        Gson gson = new Gson();

        ArrayList<Recipie> recipes = new ArrayList<>();
        Map<String,?> map = pref.getAll();
        for (String s: map.keySet()) {
            Log.d("Test", (String) map.get(s));
            Recipie recipie = gson.fromJson((String) map.get(s), Recipie.class);
            recipes.add(recipie);
        }

        final RecyclerView recipe = (RecyclerView) view.findViewById(R.id.saved_recipes);

        recipe.setAdapter(new MyRecipeRecyclerViewAdapter(recipes, mListener));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecipeFragment.OnRecipeListFragmentInteractionListener) {
            //if (context instanceof IngredientsFragment.OnIngredientsListFragmentInteractionListener) {

            mListener = (RecipeFragment.OnRecipeListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }
}
