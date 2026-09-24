package src;

import java.util.ArrayList;

public class RecipeBook {

    private ArrayList<Recipe> recipes;

    public RecipeBook() {
        recipes = new ArrayList<Recipe>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public ArrayList<Recipe> getAllRecipes() {
        return recipes;
    }

    public void loadDefaultRecipes() {

        // Prevent duplicate recipes if this method is called twice
        if (!recipes.isEmpty()) {
            return;
        }

        // Scrambled Eggs
        ArrayList<Ingredient> scrambledEggIngredients =
                new ArrayList<Ingredient>();
        scrambledEggIngredients.add(new Ingredient("egg"));
        scrambledEggIngredients.add(new Ingredient("butter"));
        scrambledEggIngredients.add(new Ingredient("salt"));

        addRecipe(new Recipe(
                "Scrambled Eggs",
                scrambledEggIngredients));

        // Pancakes
        ArrayList<Ingredient> pancakeIngredients =
                new ArrayList<Ingredient>();
        pancakeIngredients.add(new Ingredient("flour"));
        pancakeIngredients.add(new Ingredient("egg"));
        pancakeIngredients.add(new Ingredient("milk"));
        pancakeIngredients.add(new Ingredient("sugar"));
        pancakeIngredients.add(new Ingredient("butter"));

        addRecipe(new Recipe(
                "Pancakes",
                pancakeIngredients));

        // Grilled Cheese
        ArrayList<Ingredient> grilledCheeseIngredients =
                new ArrayList<Ingredient>();
        grilledCheeseIngredients.add(new Ingredient("bread"));
        grilledCheeseIngredients.add(new Ingredient("cheese"));
        grilledCheeseIngredients.add(new Ingredient("butter"));

        addRecipe(new Recipe(
                "Grilled Cheese",
                grilledCheeseIngredients));

        // Pasta with Tomato Sauce
        ArrayList<Ingredient> pastaIngredients =
                new ArrayList<Ingredient>();
        pastaIngredients.add(new Ingredient("pasta"));
        pastaIngredients.add(new Ingredient("tomato sauce"));
        pastaIngredients.add(new Ingredient("salt"));

        addRecipe(new Recipe(
                "Pasta with Tomato Sauce",
                pastaIngredients));

        // Peanut Butter and Jelly
        ArrayList<Ingredient> pbjIngredients =
                new ArrayList<Ingredient>();
        pbjIngredients.add(new Ingredient("bread"));
        pbjIngredients.add(new Ingredient("peanut butter"));
        pbjIngredients.add(new Ingredient("jelly"));

        addRecipe(new Recipe(
                "Peanut Butter and Jelly",
                pbjIngredients));
    }
}