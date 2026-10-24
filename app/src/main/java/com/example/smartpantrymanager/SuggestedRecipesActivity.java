package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private RecipeAdapter adapter;
    private List<Recipe> recipes;
    private TextView tvNoRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        dbHelper = new DatabaseHelper(this);
        recipes = new ArrayList<>();

        ListView listView = findViewById(R.id.listViewRecipes);
        Button btnBack = findViewById(R.id.btnBack);
        tvNoRecipes = findViewById(R.id.tvNoRecipes);

        adapter = new RecipeAdapter(this, recipes);
        listView.setAdapter(adapter);

        loadRecipes();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = recipes.get(position);
                Intent intent = new Intent(SuggestedRecipesActivity.this, RecipeDetailActivity.class);
                intent.putExtra("RECIPE_NAME", recipe.getName());
                intent.putExtra("RECIPE_INGREDIENTS", recipe.getIngredients());
                intent.putExtra("RECIPE_INSTRUCTIONS", recipe.getInstructions());
                intent.putExtra("RECIPE_MATCHING", recipe.getMatchingIngredients());
                intent.putExtra("RECIPE_MISSING", recipe.getMissingIngredients());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecipes();
    }

    private void loadRecipes() {
        List<PantryItem> pantryItems = dbHelper.getAllPantryItems();
        RecipeMatcher matcher = new RecipeMatcher();
        recipes = matcher.findMatchingRecipes(pantryItems);

        if (recipes.isEmpty()) {
            tvNoRecipes.setVisibility(View.VISIBLE);
        } else {
            tvNoRecipes.setVisibility(View.GONE);
        }

        adapter.notifyDataSetChanged();
    }
}
