import java.util.Scanner;

public class Menu {
    private Pantry pantry;
    private RecipeBook recipeBook;
    private Recommender recommender;
    private Scanner scanner;

    public Menu(Pantry pantry, RecipeBook recipeBook, Recommender recommender) {
        // TODO: store references, initialize scanner
        this.pantry = pantry;
        this.recipeBook = recipeBook;
        this.recommender = recommender;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        // TODO: loop: print menu options, read user choice
        // TODO: validate input is a number 1-5 (handle non-numeric input)
        // TODO: route to correct handle___() method based on choice
        // TODO: exit loop when user chooses "quit"
    }

    public void handleAddIngredient() {
        // TODO: prompt user for ingredient name
        // TODO: call pantry.addIngredient(name)
    }

    public void handleRemoveIngredient() {
        // TODO: prompt user for ingredient name
        // TODO: call pantry.removeIngredient(name), print result message
    }

    public void handleViewPantry() {
        // TODO: print all items in pantry.getItems()
    }

    public void handleRecommendations() {
        // TODO: check pantry.isEmpty() first - if empty, print message and return
        // TODO: call recommender.getFullMatches() and print results
        // TODO: call recommender.getAlmostMatches() and print results
    }
}