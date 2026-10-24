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

        // Recipe 9: Pancakes
        allRecipes.add(new Recipe(
                "Simple Pancakes",
                "Flour, Milk, Eggs, Butter, Sugar",
                "1. Mix flour, sugar, and baking powder\n2. Add milk and eggs\n3. Whisk until smooth\n4. Cook on buttered pan\n5. Flip when bubbles form",
                "",
                ""
        ));

        // Recipe 10: Scrambled Eggs
        allRecipes.add(new Recipe(
                "Scrambled Eggs",
                "Eggs, Butter, Salt, Pepper",
                "1. Melt butter in pan\n2. Beat eggs\n3. Pour into pan\n4. Stir gently until cooked\n5. Season and serve",
                "",
                ""
        ));

        // Recipe 11: Mashed Potatoes
        allRecipes.add(new Recipe(
                "Mashed Potatoes",
                "Potatoes, Butter, Milk, Salt",
                "1. Boil potatoes until tender\n2. Drain and mash\n3. Add butter and milk\n4. Season with salt\n5. Whip until smooth",
                "",
                ""
        ));

        // Recipe 12: Tomato Soup
        allRecipes.add(new Recipe(
                "Tomato Soup",
                "Tomatoes, Onion, Garlic, Butter, Cream",
                "1. Sauté onion and garlic in butter\n2. Add chopped tomatoes\n3. Simmer until soft\n4. Blend until smooth\n5. Stir in cream and serve",
                "",
                ""
        ));

        // Recipe 13: Pasta Carbonara
        allRecipes.add(new Recipe(
                "Pasta Carbonara",
                "Spaghetti, Eggs, Bacon, Parmesan cheese, Black pepper",
                "1. Cook pasta\n2. Fry bacon until crispy\n3. Beat eggs with cheese\n4. Mix hot pasta with bacon\n5. Add egg mixture and toss",
                "",
                ""
        ));

        // Recipe 14: French Toast
        allRecipes.add(new Recipe(
                "French Toast",
                "Bread, Eggs, Milk, Butter, Cinnamon",
                "1. Whisk eggs with milk and cinnamon\n2. Dip bread slices\n3. Fry in butter until golden\n4. Flip and cook other side\n5. Serve with syrup",
                "",
                ""
        ));

        // Recipe 15: Caesar Salad
        allRecipes.add(new Recipe(
                "Caesar Salad",
                "Lettuce, Croutons, Parmesan cheese, Caesar dressing",
                "1. Wash and chop lettuce\n2. Add croutons\n3. Sprinkle with parmesan\n4. Add dressing\n5. Toss and serve",
                "",
                ""
        ));

        // Recipe 16: Rice and Beans
        allRecipes.add(new Recipe(
                "Rice and Beans",
                "Rice, Black beans, Onion, Garlic, Cumin",
                "1. Cook rice\n2. Sauté onion and garlic\n3. Add beans and cumin\n4. Simmer until heated\n5. Serve over rice",
                "",
                ""
        ));

        // Recipe 17: Grilled Vegetables
        allRecipes.add(new Recipe(
                "Grilled Vegetables",
                "Zucchini, Bell pepper, Eggplant, Olive oil, Salt",
                "1. Slice vegetables\n2. Brush with olive oil\n3. Season with salt\n4. Grill until tender\n5. Serve hot",
                "",
                ""
        ));

        // Recipe 18: Quesadilla
        allRecipes.add(new Recipe(
                "Cheese Quesadilla",
                "Tortilla, Cheese, Butter",
                "1. Heat tortilla in pan\n2. Add cheese\n3. Fold in half\n4. Flip and cook until cheese melts\n5. Cut and serve",
                "",
                ""
        ));

        // Recipe 19: Smoothie
        allRecipes.add(new Recipe(
                "Fruit Smoothie",
                "Banana, Yogurt, Milk, Honey",
                "1. Add banana to blender\n2. Add yogurt and milk\n3. Blend until smooth\n4. Add honey to taste\n5. Serve chilled",
                "",
                ""
        ));

        // Recipe 20: Baked Potato
        allRecipes.add(new Recipe(
                "Baked Potato",
                "Potato, Butter, Salt, Sour cream",
                "1. Scrub potato\n2. Poke holes with fork\n3. Bake at 400°F for 1 hour\n4. Cut open and add butter\n5. Top with sour cream",
                "",
                ""
        ));
    }

    public List<Recipe> findMatchingRecipes(List<PantryItem> pantryItems) {
        List<Recipe> matchingRecipes = new ArrayList<>();

        // Create a list of pantry item names for easy matching
        List<String> pantryNames = new ArrayList<>();
        for (PantryItem item : pantryItems) {
            pantryNames.add(item.getName().toLowerCase().trim());
        }

        for (Recipe recipe : allRecipes) {
            String[] recipeIngredients = recipe.getIngredients().split(", ");
            List<String> matching = new ArrayList<>();
            List<String> missing = new ArrayList<>();

            for (String ingredient : recipeIngredients) {
                String ingredientLower = ingredient.toLowerCase().trim();
                boolean found = false;
                for (String pantryName : pantryNames) {
                    // Very flexible matching: check if pantry name contains ingredient or vice versa
                    // Also handle plural forms by removing trailing 's'
                    String pantryRoot = pantryName.endsWith("s") ? pantryName.substring(0, pantryName.length() - 1) : pantryName;
                    String ingredientRoot = ingredientLower.endsWith("s") ? ingredientLower.substring(0, ingredientLower.length() - 1) : ingredientLower;

                    if (pantryName.contains(ingredientLower) ||
                        ingredientLower.contains(pantryName) ||
                        pantryRoot.contains(ingredientRoot) ||
                        ingredientRoot.contains(pantryRoot)) {
                        matching.add(ingredient);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    missing.add(ingredient);
                }
            }

            // Strict-matching: only add recipes that have ALL ingredients present
            if (missing.isEmpty()) {
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
