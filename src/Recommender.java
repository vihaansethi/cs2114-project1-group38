package src;

import java.util.ArrayList;

/**
 * Recommends recipes based on the ingredients currently in a pantry. Can find
 * which ingredients a recipe is missing, which recipes can be made right now,
 * and which recipes are only a few ingredients away.
 */

public class Recommender
{

    /**
     * Finds the ingredients a recipe requires that the pantry does not contain.
     * Ingredients are compared using {@link Ingredient#equals(Object)}.
     *
     * @param recipe
     *            the recipe whose required ingredients are checked
     * @param pantry
     *            the pantry to check the ingredients against
     * @return a new list of the recipe's ingredients not found in the pantry,
     *             in the recipe's order; empty if nothing is missing
     */
    public ArrayList<Ingredient> findMissing(Recipe recipe, Pantry pantry)
    {
        ArrayList<Ingredient> missing = new ArrayList<>();
        ArrayList<Ingredient> pantryItems = pantry.getItems();

        for (Ingredient required : recipe.getIngredients())
        {
            boolean found = false;
            for (Ingredient owned : pantryItems)
            {
                if (required.equals(owned))
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                missing.add(required);
            }
        }
        return missing;
    }


    /**
     * Finds every recipe in the book that can be made entirely from the pantry,
     * meaning no required ingredients are missing.
     *
     * @param book
     *            the recipe book to search
     * @param pantry
     *            the pantry whose ingredients are available
     * @return a new list of recipes with zero missing ingredients, in the
     *             book's order; empty if the book is empty or nothing matches
     */
    public ArrayList<Recipe> getFullMatches(RecipeBook book, Pantry pantry)
    {
        ArrayList<Recipe> fullMatches = new ArrayList<>();

        for (Recipe recipe : book.getAllRecipes())
        {
            if (findMissing(recipe, pantry).isEmpty())
            {
                fullMatches.add(recipe);
            }
        }
        return fullMatches;
    }


    /**
     * Finds recipes that are close to being made: those missing at least one
     * ingredient, but no more than {@code maxMissing}. Recipes with nothing
     * missing are excluded (see {@link #getFullMatches}).
     *
     * @param book
     *            the recipe book to search
     * @param pantry
     *            the pantry whose ingredients are available
     * @param maxMissing
     *            the largest number of missing ingredients allowed (inclusive)
     * @return a new list of recipes missing between 1 and {@code maxMissing}
     *             ingredients, in the book's order; empty if none qualify
     */

    public
        ArrayList<Recipe>
        getAlmostMatches(RecipeBook book, Pantry pantry, int maxMissing)
    {
        ArrayList<Recipe> almostMatches = new ArrayList<>();

        for (Recipe recipe : book.getAllRecipes())
        {
            int missingCount = findMissing(recipe, pantry).size();
            if (missingCount >= 1 && missingCount <= maxMissing)
            {
                almostMatches.add(recipe);
            }
        }
        return almostMatches;
    }
}
