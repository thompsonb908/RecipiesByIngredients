package com.example.recipiesbyingredients.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.recipiesbyingredients.R;
import com.example.recipiesbyingredients.fragments.viewadapter.MyIngredientRecyclerViewAdapter;
import com.example.recipiesbyingredients.models.DatabaseHelper;
import com.example.recipiesbyingredients.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private IngredientsFragment.OnIngredientsListFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        final SearchView search = (SearchView) view.findViewById(R.id.recipeSearch);
        final RecyclerView ingredient_results = (RecyclerView) view.findViewById(R.id.search_result_ingredients);

        ingredient_results.setAdapter(new MyIngredientRecyclerViewAdapter(new ArrayList<Ingredient>(), mListener));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("Lifecycle", "Search");
                DatabaseHelper db = DatabaseHelper.getInstance(getContext());
                List<Ingredient> results = db.searchIngredients(search.getQuery().toString());

                ingredient_results.setAdapter(new MyIngredientRecyclerViewAdapter(results, mListener));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IngredientsFragment.OnIngredientsListFragmentInteractionListener) {
            mListener = (IngredientsFragment.OnIngredientsListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }
}
