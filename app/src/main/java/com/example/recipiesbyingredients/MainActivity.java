package com.example.recipiesbyingredients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.recipiesbyingredients.firebase.Recipies;
import com.example.recipiesbyingredients.fragments.dialogs.EditIngredientFragment;
import com.example.recipiesbyingredients.fragments.IngredientsFragment;
import com.example.recipiesbyingredients.fragments.IngredientsPageFragment;
import com.example.recipiesbyingredients.fragments.RecipeFragment;
import com.example.recipiesbyingredients.fragments.SavedFragment;
import com.example.recipiesbyingredients.fragments.SearchFragment;
import com.example.recipiesbyingredients.models.Ingredient;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import com.example.recipiesbyingredients.dummy.DummyContent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements RecipeFragment.OnRecipeListFragmentInteractionListener, IngredientsFragment.OnIngredientsListFragmentInteractionListener {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user = mAuth.getCurrentUser();
        Toast.makeText(this,"Welcom " + user.getEmail(), Toast.LENGTH_LONG);

        Recipies.search("Test");

        ViewPager vp = (ViewPager) findViewById(R.id.content_container);
        AppPageAdapter pa = new AppPageAdapter(getSupportFragmentManager());
        vp.setOffscreenPageLimit(3);

        final IngredientsPageFragment ingredientsPage = new IngredientsPageFragment();

        pa.addView(new SearchFragment(), getString(R.string.search_button));
        pa.addView(new SavedFragment(), getString(R.string.saved_button));
        pa.addView(ingredientsPage, getString(R.string.ingredients_button));

        vp.setAdapter(pa);

        TabLayout tl = (TabLayout) findViewById(R.id.main_nav);
        tl.setupWithViewPager(vp);

    }

    //Saved Recipe is Selected
    public void OnRecipeListFragmentInteractionListener(DummyContent.DummyItem item) {
        Log.d("Recipe List Select", "Item: " + item.id);
        Toast toast = Toast.makeText(this, "Item "+ item.id + " selected", Toast.LENGTH_LONG);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void OnIngredientsListFragmentInteractionListener(Ingredient item) {
        Log.d("Ingredients List Select", "Item: " + item.getName());

        DialogFragment dialog = new EditIngredientFragment();
        Bundle args = new Bundle();
        args.putInt("id", item.getId());
        args.putString("name", item.getName());
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "UpdateIng");
    }

    private class AppPageAdapter extends FragmentStatePagerAdapter {

        ArrayList<Fragment> views = new ArrayList<Fragment>();
        ArrayList<String> titleList = new ArrayList<String>();

        public AppPageAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addView(Fragment f, String title) {
            views.add(f);
            titleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return views.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }
    }
}


