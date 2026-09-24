package src;

import java.util.ArrayList;

public class Recommender {

    public ArrayList<Ingredient> findMissing(Recipe recipe, Pantry pantry) {
        ArrayList<Ingredient> missing = new ArrayList<>();
        ArrayList<Ingredient> pantryItems = pantry.getItems();

        for (Ingredient required : recipe.getIngredients()) {
            boolean found = false;
            for (Ingredient owned : pantryItems) {
                if (required.equals(owned)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                missing.add(required);
            }
        }
        return missing;
    }

    public ArrayList<Recipe> getFullMatches(RecipeBook book, Pantry pantry) {
        ArrayList<Recipe> fullMatches = new ArrayList<>();

        for (Recipe recipe : book.getAllRecipes()) {
            if (findMissing(recipe, pantry).isEmpty()) {
                fullMatches.add(recipe);
            }
        }
        return fullMatches;
    }

    public ArrayList<Recipe> getAlmostMatches(RecipeBook book, Pantry pantry, int maxMissing) {
        ArrayList<Recipe> almostMatches = new ArrayList<>();

        for (Recipe recipe : book.getAllRecipes()) {
            int missingCount = findMissing(recipe, pantry).size();
            if (missingCount >= 1 && missingCount <= maxMissing) {
                almostMatches.add(recipe);
            }
        }
        return almostMatches;
    }
}