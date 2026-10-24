package com.example.smartpantrymanager;

public class Recipe {
    private String name;
    private String ingredients;
    private String instructions;
    private String matchingIngredients;
    private String missingIngredients;

    public Recipe() {
    }

    public Recipe(String name, String ingredients, String instructions, String matchingIngredients, String missingIngredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.matchingIngredients = matchingIngredients;
        this.missingIngredients = missingIngredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMatchingIngredients() {
        return matchingIngredients;
    }

    public void setMatchingIngredients(String matchingIngredients) {
        this.matchingIngredients = matchingIngredients;
    }

    public String getMissingIngredients() {
        return missingIngredients;
    }

    public void setMissingIngredients(String missingIngredients) {
        this.missingIngredients = missingIngredients;
    }
}
