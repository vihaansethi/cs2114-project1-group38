package src;

import java.util.ArrayList;

/**
 * Represents a recipe with a name and a list of required ingredients.
 * A recipe stores the ingredients needed so that it can be compared
 * with the ingredients currently available in a pantry.
 *
 * @author Preston Moore
 * @version 2026.09.24
 */
public class Recipe {

    private String name;
    private ArrayList<Ingredient> requiredIngredients;

    /**
     * Creates a new recipe with the given name and required ingredients.
     *
     * @param name
     *     the name of the recipe
     * @param ingredients
     *     the ingredients required to make the recipe
     */
    public Recipe(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.requiredIngredients = ingredients;
    }

    /**
     * Returns the name of the recipe.
     *
     * @return the recipe name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of ingredients required to make the recipe.
     *
     * @return the required ingredients
     */
    public ArrayList<Ingredient> getIngredients() {
        return requiredIngredients;
    }
}