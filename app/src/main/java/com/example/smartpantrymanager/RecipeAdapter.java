package com.example.smartpantrymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        super(context, 0, recipes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Recipe recipe = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recipe, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvRecipeName);
        TextView tvMatching = convertView.findViewById(R.id.tvMatchingCount);

        tvName.setText(recipe.getName());
        tvMatching.setText("You have: " + recipe.getMatchingIngredients());

        return convertView;
    }
}
