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
import com.example.recipiesbyingredients.firebase.Recipies;
import com.example.recipiesbyingredients.fragments.viewadapter.MyIngredientRecyclerViewAdapter;
import com.example.recipiesbyingredients.fragments.viewadapter.MyRecipeRecyclerViewAdapter;
import com.example.recipiesbyingredients.models.DatabaseHelper;
import com.example.recipiesbyingredients.models.Ingredient;
import com.example.recipiesbyingredients.models.Recipie;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private IngredientsFragment.OnIngredientsListFragmentInteractionListener iListener;

    private RecipeFragment.OnRecipeListFragmentInteractionListener mListener;

    //Searches the database for ingredients.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        final SearchView search = (SearchView) view.findViewById(R.id.recipeSearch);

        //For the ingredients
        final RecyclerView ingredient_results = (RecyclerView) view.findViewById(R.id.search_result_ingredients);

        //For the recipies
        final RecyclerView recipie_results = (RecyclerView) view.findViewById(R.id.search_result_recipes);

        //----------------------------------------- Adding Recipes to the database. Will Delete this later --------------------------------------------//

        //Look at the constructor and see what I need to build the recipe out of.
        //Recipies.addNewRecipie("Tester Sandwhich", null, null, "https://i2.wp.com/gimmedelicious.com/wp-content/uploads/2019/09/Popeyes-Chicken-Sandwich_-9.jpg");


        //---------------------------------------- End of Adding Recipes to the Database -------------------------------------------------------------//

        //Adding for the ingriedents
        ingredient_results.setAdapter(new MyIngredientRecyclerViewAdapter(new ArrayList<Ingredient>(), iListener));

        recipie_results.setAdapter(new MyRecipeRecyclerViewAdapter(new ArrayList<Recipie>(), mListener));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("Lifecycle", "Search");
                DatabaseHelper db = DatabaseHelper.getInstance(getContext());
                List<Ingredient> resultsI = db.searchIngredients(search.getQuery().toString());


                //TODO
                //Doing something similar but getting it from the search array in the recipes firebase. Then pass it to the recyclerview.
                ingredient_results.setAdapter(new MyIngredientRecyclerViewAdapter(resultsI, iListener));

                List<Recipie> resultsR = Recipies.search(search.getQuery().toString());

                recipie_results.setAdapter(new MyRecipeRecyclerViewAdapter(resultsR, mListener));
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
        if (context instanceof RecipeFragment.OnRecipeListFragmentInteractionListener && context instanceof IngredientsFragment.OnIngredientsListFragmentInteractionListener) {
            //if (context instanceof IngredientsFragment.OnIngredientsListFragmentInteractionListener) {
            iListener = (IngredientsFragment.OnIngredientsListFragmentInteractionListener) context;

            mListener = (RecipeFragment.OnRecipeListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }
}
