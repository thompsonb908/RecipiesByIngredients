package com.example.recipiesbyingredients.fragments.viewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipiesbyingredients.R;
import com.example.recipiesbyingredients.dummy.DummyContent.DummyItem;
import com.example.recipiesbyingredients.fragments.RecipeFragment.OnRecipeListFragmentInteractionListener;
import com.example.recipiesbyingredients.models.Recipie;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnRecipeListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyRecipeRecyclerViewAdapter extends RecyclerView.Adapter<MyRecipeRecyclerViewAdapter.ViewHolder> {

    private final List<Recipie> mValues;
    private final OnRecipeListFragmentInteractionListener mListener;

    public MyRecipeRecyclerViewAdapter(List<Recipie> items, OnRecipeListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    //Telling you it's going to grab the recipe fragment. Then create it.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipe, parent, false);
        return new ViewHolder(view);
    }

    //That's whats binding the data to the layout. This allows for the layout to get extended.
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        //Was throwing issues for the searching.
        //holder.mContentView.setText(mValues.get(position).content);

        //Will set the recipe to the name that is passed.
        holder.mNameView.setText(holder.mItem.getName());
        Picasso.with(holder.mView.getContext()).load(holder.mItem.getImgURL()).into(holder.mImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnRecipeListFragmentInteractionListener(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final ImageView mImage;
        public Recipie mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            mImage = (ImageView) view.findViewById(R.id.recipe_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mItem.getName() + "'";
        }
    }
}
