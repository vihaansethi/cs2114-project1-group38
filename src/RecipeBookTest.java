package src;

import java.util.ArrayList;

/**
 * This is the test class for RecipeBook
 */
public class RecipeBookTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        testNewRecipeBook_empty();
        testAddRecipe_normal();
        testAddRecipe_multiple();
        testLoadDefaultRecipes_normal();
        testLoadDefaultRecipes_contents();
        testLoadDefaultRecipes_twice();

        System.out.println("\n" + passed + " passed, " + failed + " failed");
    }

    // Normal: new RecipeBook -> contains no recipes
    static void testNewRecipeBook_empty() {
        RecipeBook book = new RecipeBook();

        check("new RecipeBook - empty",
            book.getAllRecipes().isEmpty());
    }

    // Normal: add one recipe -> book contains that recipe
    static void testAddRecipe_normal() {
        RecipeBook book = new RecipeBook();

        ArrayList<Ingredient> required = new ArrayList<>();
        required.add(new Ingredient("egg"));

        Recipe recipe = new Recipe("Fried Egg", required);
        book.addRecipe(recipe);

        boolean correct =
            book.getAllRecipes().size() == 1
            && book.getAllRecipes().get(0).getName().equals("Fried Egg");

        check("addRecipe - normal", correct);
    }

    // Normal: add two recipes -> both are stored
    static void testAddRecipe_multiple() {
        RecipeBook book = new RecipeBook();

        ArrayList<Ingredient> firstIngredients = new ArrayList<>();
        firstIngredients.add(new Ingredient("egg"));

        Recipe first = new Recipe("Fried Egg", firstIngredients);

        ArrayList<Ingredient> secondIngredients = new ArrayList<>();
        secondIngredients.add(new Ingredient("bread"));
        secondIngredients.add(new Ingredient("cheese"));

        Recipe second =
            new Recipe("Grilled Cheese", secondIngredients);

        book.addRecipe(first);
        book.addRecipe(second);

        boolean correct =
            book.getAllRecipes().size() == 2
            && book.getAllRecipes().get(0).getName().equals("Fried Egg")
            && book.getAllRecipes().get(1).getName().equals("Grilled Cheese");

        check("addRecipe - multiple recipes", correct);
    }

    // Normal: loadDefaultRecipes -> starter recipes are added
    static void testLoadDefaultRecipes_normal() {
        RecipeBook book = new RecipeBook();

        book.loadDefaultRecipes();

        check("loadDefaultRecipes - normal",
            book.getAllRecipes().size() == 5);
    }

    // Normal: verify the expected starter recipes were loaded
    static void testLoadDefaultRecipes_contents() {
        RecipeBook book = new RecipeBook();

        book.loadDefaultRecipes();

        boolean correct =
            book.getAllRecipes().size() == 5
            && book.getAllRecipes().get(0).getName()
                .equals("Scrambled Eggs")
            && book.getAllRecipes().get(1).getName()
                .equals("Pancakes")
            && book.getAllRecipes().get(2).getName()
                .equals("Grilled Cheese")
            && book.getAllRecipes().get(3).getName()
                .equals("Pasta with Tomato Sauce")
            && book.getAllRecipes().get(4).getName()
                .equals("Peanut Butter and Jelly");

        check("loadDefaultRecipes - correct recipes", correct);
    }

    // Edge case: calling loadDefaultRecipes twice should not duplicate recipes
    static void testLoadDefaultRecipes_twice() {
        RecipeBook book = new RecipeBook();

        book.loadDefaultRecipes();
        book.loadDefaultRecipes();

        check("loadDefaultRecipes - called twice",
            book.getAllRecipes().size() == 5);
    }

    // Prints PASS or FAIL and keeps track of totals
    static void check(String testName, boolean condition) {
        if (condition) {
            System.out.println("PASS: " + testName);
            passed++;
        }
        else {
            System.out.println("FAIL: " + testName);
            failed++;
        }
    }
}