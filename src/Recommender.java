import java.util.ArrayList;
public class Recommender {
	public ArrayList<Ingredient> findMissing(Recipe recipe, Pantry pantry) {
		// TODO: loop through recipe.getIngredients()
        // TODO: for each, check if it exists in pantry.getItems()
        // TODO: if not found, add to a "missing" list
        // TODO: return missing list
        return null;
	} 

	public ArrayList<Recipe> getFullMatches(RecipeBook book, Pantry pantry) {
        // TODO: loop through book.getAllRecipes()
        // TODO: use findMissing() - if missing list is empty, it's a full match
        // TODO: return list of full-match recipes
        return null;
    }

    public ArrayList<Recipe> getAlmostMatches(RecipeBook book, Pantry pantry, int maxMissing) {
        // TODO: loop through book.getAllRecipes()
        // TODO: use findMissing() - if missing.size() is between 1 and maxMissing, include it
        // TODO: return list of almost-match recipes
        return null;
    }
}