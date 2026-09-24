package src;



/**
 * Program entry point. Wires up the core objects (Pantry,
 * RecipeBook, Recommender), loads the starter recipes, and
 * hands control to the Menu, which runs the program from there.
 */
public class Main {

    public static void main(String[] args) {

        Pantry pantry = new Pantry();
        RecipeBook recipeBook = new RecipeBook();
        Recommender recommender = new Recommender();

        recipeBook.loadDefaultRecipes();

        Menu menu = new Menu(pantry, recipeBook, recommender);

        menu.start();
    }
}