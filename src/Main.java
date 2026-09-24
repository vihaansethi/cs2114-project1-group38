package src;

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