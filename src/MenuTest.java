package src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * This is the test class for menu
 *
 */
public class MenuTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        testAddIngredient_normal();
        testRemoveIngredient_normal();
        testRemoveIngredient_notFound();
        testViewPantry_normal();
        testViewPantry_empty();
        testRecommendations_emptyPantry();
        testStart_invalidNumber();
        testStart_nonNumeric();
        testStart_quit();

        System.out.println("\n" + passed + " passed, " + failed + " failed");
    }

    // Normal: add "egg" -> pantry contains egg
    static void testAddIngredient_normal() {
        Pantry pantry = new Pantry();
        RecipeBook book = new RecipeBook();
        Recommender recommender = new Recommender();

        Menu menu = createMenuWithInput(
            pantry, book, recommender, "egg\n");

        menu.handleAddIngredient();

        boolean correct =
            pantry.getItems().size() == 1
            && pantry.getItems().get(0).getName().equals("egg");

        check("handleAddIngredient - normal", correct);
    }

    // Normal: pantry has egg, remove egg -> pantry is empty
    static void testRemoveIngredient_normal() {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");

        RecipeBook book = new RecipeBook();
        Recommender recommender = new Recommender();

        Menu menu = createMenuWithInput(
            pantry, book, recommender, "egg\n");

        menu.handleRemoveIngredient();

        check("handleRemoveIngredient - normal",
            pantry.isEmpty());
    }

    // Bad input: remove milk when pantry only has egg
    static void testRemoveIngredient_notFound() {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");

        RecipeBook book = new RecipeBook();
        Recommender recommender = new Recommender();

        Menu menu = createMenuWithInput(
            pantry, book, recommender, "milk\n");

        String output = captureRemoveOutput(menu);

        boolean correct =
            pantry.getItems().size() == 1
            && output.contains("That item isn't in your pantry");

        check("handleRemoveIngredient - item not found", correct);
    }

    // Normal: pantry has egg -> view pantry prints egg
    static void testViewPantry_normal() {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");

        RecipeBook book = new RecipeBook();
        Recommender recommender = new Recommender();

        Menu menu = createMenuWithInput(
            pantry, book, recommender, "");

        String output = captureViewOutput(menu);

        check("handleViewPantry - normal",
            output.contains("egg"));
    }

    // Edge case: empty pantry -> prints empty pantry message
    static void testViewPantry_empty() {
        Pantry pantry = new Pantry();

        RecipeBook book = new RecipeBook();
        Recommender recommender = new Recommender();

        Menu menu = createMenuWithInput(
            pantry, book, recommender, "");

        String output = captureViewOutput(menu);

        check("handleViewPantry - empty",
            output.contains("Your pantry is empty"));
    }

    // Bad input: recommendations requested with empty pantry
    static void testRecommendations_emptyPantry() {
        Pantry pantry = new Pantry();

        RecipeBook book = new RecipeBook();
        book.loadDefaultRecipes();

        Recommender recommender = new Recommender();

        Menu menu = createMenuWithInput(
            pantry, book, recommender, "");

        String output = captureRecommendationOutput(menu);

        check("handleRecommendations - empty pantry",
            output.contains("Add some ingredients first!"));
    }

    // Bad input: menu choice 9 -> invalid option, then 5 quits
    static void testStart_invalidNumber() {
        Pantry pantry = new Pantry();
        RecipeBook book = new RecipeBook();
        Recommender recommender = new Recommender();

        Menu menu = createMenuWithInput(
            pantry, book, recommender, "9\n5\n");

        String output = captureStartOutput(menu);

        boolean correct =
            output.contains("Invalid option")
            && output.contains("Goodbye!");

        check("start - invalid number", correct);
    }

    // Bad input: menu choice "banana" -> invalid option, then 5 quits
    static void testStart_nonNumeric() {
        Pantry pantry = new Pantry();
        RecipeBook book = new RecipeBook();
        Recommender recommender = new Recommender();

        Menu menu = createMenuWithInput(
            pantry, book, recommender, "banana\n5\n");

        String output = captureStartOutput(menu);

        boolean correct =
            output.contains("Invalid option")
            && output.contains("Goodbye!");

        check("start - non-numeric input", correct);
    }

    // Normal: choose 5 -> program quits
    static void testStart_quit() {
        Pantry pantry = new Pantry();
        RecipeBook book = new RecipeBook();
        Recommender recommender = new Recommender();

        Menu menu = createMenuWithInput(
            pantry, book, recommender, "5\n");

        String output = captureStartOutput(menu);

        check("start - quit",
            output.contains("Goodbye!"));
    }

    // Creates a Menu using fake keyboard input
    static Menu createMenuWithInput(
        Pantry pantry,
        RecipeBook book,
        Recommender recommender,
        String input) {

        ByteArrayInputStream testInput =
            new ByteArrayInputStream(input.getBytes());

        System.setIn(testInput);

        return new Menu(pantry, book, recommender);
    }

    // Captures output from handleRemoveIngredient
    static String captureRemoveOutput(Menu menu) {
        ByteArrayOutputStream output =
            new ByteArrayOutputStream();

        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        menu.handleRemoveIngredient();

        System.setOut(originalOut);

        return output.toString();
    }

    // Captures output from handleViewPantry
    static String captureViewOutput(Menu menu) {
        ByteArrayOutputStream output =
            new ByteArrayOutputStream();

        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        menu.handleViewPantry();

        System.setOut(originalOut);

        return output.toString();
    }

    // Captures output from handleRecommendations
    static String captureRecommendationOutput(Menu menu) {
        ByteArrayOutputStream output =
            new ByteArrayOutputStream();

        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        menu.handleRecommendations();

        System.setOut(originalOut);

        return output.toString();
    }

    // Captures output from start
    static String captureStartOutput(Menu menu) {
        ByteArrayOutputStream output =
            new ByteArrayOutputStream();

        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        menu.start();

        System.setOut(originalOut);

        return output.toString();
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