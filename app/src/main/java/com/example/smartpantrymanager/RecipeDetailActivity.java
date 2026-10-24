package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView tvRecipeName = findViewById(R.id.tvRecipeName);
        TextView tvIngredients = findViewById(R.id.tvIngredients);
        TextView tvInstructions = findViewById(R.id.tvInstructions);
        TextView tvMatching = findViewById(R.id.tvMatching);
        TextView tvMissing = findViewById(R.id.tvMissing);
        Button btnBack = findViewById(R.id.btnBack);

        String recipeName = getIntent().getStringExtra("RECIPE_NAME");
        String ingredients = getIntent().getStringExtra("RECIPE_INGREDIENTS");
        String instructions = getIntent().getStringExtra("RECIPE_INSTRUCTIONS");
        String matching = getIntent().getStringExtra("RECIPE_MATCHING");
        String missing = getIntent().getStringExtra("RECIPE_MISSING");

        tvRecipeName.setText(recipeName);
        tvIngredients.setText("Ingredients:\n" + ingredients);
        tvInstructions.setText("Instructions:\n" + instructions);
        tvMatching.setText("You have: " + matching);
        tvMissing.setText("You need: " + missing);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
