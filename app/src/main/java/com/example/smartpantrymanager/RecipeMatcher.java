package com.example.smartpantrymanager;

import java.util.ArrayList;
import java.util.List;

public class RecipeMatcher {

    private List<Recipe> allRecipes;

    public RecipeMatcher() {
        allRecipes = new ArrayList<>();
        initializeRecipes();
    }

    private void initializeRecipes() {
        // Recipe 1: Pasta
        allRecipes.add(new Recipe(
                "Spaghetti Bolognese",
                "Spaghetti, Ground beef, Tomato sauce, Onion, Garlic, Olive oil",
                "1. Cook spaghetti according to package directions\n2. Brown ground beef in a pan\n3. Add chopped onion and garlic\n4. Pour in tomato sauce and simmer\n5. Serve sauce over pasta",
                "",
                ""
        ));

        // Recipe 2: Salad
        allRecipes.add(new Recipe(
                "Garden Salad",
                "Lettuce, Tomato, Cucumber, Olive oil, Lemon juice",
                "1. Wash and chop lettuce, tomato, and cucumber\n2. Mix in a bowl\n3. Drizzle with olive oil and lemon juice\n4. Toss and serve",
                "",
                ""
        ));

        // Recipe 3: Omelette
        allRecipes.add(new Recipe(
                "Cheese Omelette",
                "Eggs, Cheese, Butter, Salt, Pepper",
                "1. Beat eggs with salt and pepper\n2. Melt butter in a pan\n3. Pour in eggs and cook until set\n4. Add cheese in the center\n5. Fold and serve",
                "",
                ""
        ));

        // Recipe 4: Sandwich
        allRecipes.add(new Recipe(
                "Grilled Cheese Sandwich",
                "Bread, Cheese, Butter",
                "1. Butter bread slices\n2. Place cheese between bread\n3. Grill in a pan until golden\n4. Flip and grill other side\n5. Serve hot",
                "",
                ""
        ));

        // Recipe 5: Soup
        allRecipes.add(new Recipe(
                "Vegetable Soup",
                "Carrots, Potatoes, Onion, Vegetable broth, Salt, Pepper",
                "1. Chop vegetables\n2. Boil in vegetable broth\n3. Simmer until tender\n4. Season with salt and pepper\n5. Serve hot",
                "",
                ""
        ));

        // Recipe 6: Stir Fry
        allRecipes.add(new Recipe(
                "Vegetable Stir Fry",
                "Broccoli, Bell pepper, Soy sauce, Garlic, Oil",
                "1. Heat oil in a wok\n2. Add garlic and stir\n3. Add vegetables\n4. Pour in soy sauce\n5. Stir fry until tender",
                "",
                ""
        ));

        // Recipe 7: Rice Bowl
        allRecipes.add(new Recipe(
                "Fried Rice",
                "Rice, Eggs, Soy sauce, Green onions, Oil",
                "1. Cook rice and let cool\n2. Scramble eggs in a pan\n3. Add rice and soy sauce\n4. Stir fry together\n5. Top with green onions",
                "",
                ""
        ));

        // Recipe 8: Toast
        allRecipes.add(new Recipe(
                "Avocado Toast",
                "Bread, Avocado, Salt, Pepper, Lemon juice",
                "1. Toast bread\n2. Mash avocado with lemon juice\n3. Season with salt and pepper\n4. Spread on toast\n5. Serve immediately",
                "",
                ""
        ));
    }

    public List<Recipe> findMatchingRecipes(List<PantryItem> pantryItems) {
        List<Recipe> matchingRecipes = new ArrayList<>();

        // Create a list of pantry item names for easy matching
        List<String> pantryNames = new ArrayList<>();
        for (PantryItem item : pantryItems) {
            pantryNames.add(item.getName().toLowerCase());
        }

        for (Recipe recipe : allRecipes) {
            String[] recipeIngredients = recipe.getIngredients().split(", ");
            List<String> matching = new ArrayList<>();
            List<String> missing = new ArrayList<>();

            for (String ingredient : recipeIngredients) {
                String ingredientLower = ingredient.toLowerCase();
                boolean found = false;
                for (String pantryName : pantryNames) {
                    if (pantryName.contains(ingredientLower) || ingredientLower.contains(pantryName)) {
                        matching.add(ingredient);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    missing.add(ingredient);
                }
            }

            // Only add recipes that have at least one matching ingredient
            if (!matching.isEmpty()) {
                Recipe matchedRecipe = new Recipe(
                        recipe.getName(),
                        recipe.getIngredients(),
                        recipe.getInstructions(),
                        String.join(", ", matching),
                        String.join(", ", missing)
                );
                matchingRecipes.add(matchedRecipe);
            }
        }

        return matchingRecipes;
    }
}
