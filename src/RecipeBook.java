package src;

import java.util.ArrayList;

/**
 * Stores the collection of recipes available in PantryPal.
 * Recipes can be added to the recipe book, retrieved for use by
 * the recommender, or loaded from the built-in starter recipes.
 *
 * @author Preston Moore
 * @version 2026.09.24
 */
public class RecipeBook {

    private ArrayList<Recipe> recipes;

    /**
     * Creates an empty recipe book.
     */
    public RecipeBook() {
        recipes = new ArrayList<Recipe>();
    }

    /**
     * Adds a recipe to the recipe book.
     *
     * @param recipe
     *     the recipe to add
     */
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    /**
     * Returns all recipes currently stored in the recipe book.
     *
     * @return the list of recipes
     */
    public ArrayList<Recipe> getAllRecipes() {
        return recipes;
    }

    /**
     * Loads the built-in starter recipes into the recipe book.
     * If recipes have already been added, the method does not
     * load the default recipes again. This prevents duplicate
     * recipes if the method is called more than once.
     */
    public void loadDefaultRecipes() {

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