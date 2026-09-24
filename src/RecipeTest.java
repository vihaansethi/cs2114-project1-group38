package src;

import java.util.ArrayList;


/**
 * This is the test class for Recipe
 */ 
public class RecipeTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        testGetName_normal();
        testGetIngredients_normal();
        testGetIngredients_empty();

        System.out.println("\n" + passed + " passed, " + failed + " failed");
    }

    // Normal: recipe name is "Pancakes" -> returns "Pancakes"
    static void testGetName_normal() {
        ArrayList<Ingredient> required = new ArrayList<>();
        required.add(new Ingredient("egg"));
        required.add(new Ingredient("flour"));

        Recipe recipe = new Recipe("Pancakes", required);

        check("getName - normal",
            recipe.getName().equals("Pancakes"));
    }

    // Normal: recipe has [egg, flour] -> returns both ingredients
    static void testGetIngredients_normal() {
        ArrayList<Ingredient> required = new ArrayList<>();
        required.add(new Ingredient("egg"));
        required.add(new Ingredient("flour"));

        Recipe recipe = new Recipe("Pancakes", required);

        ArrayList<Ingredient> result = recipe.getIngredients();

        boolean correct =
            result.size() == 2
            && result.get(0).getName().equals("egg")
            && result.get(1).getName().equals("flour");

        check("getIngredients - normal", correct);
    }

    // Edge case: recipe has no required ingredients -> returns empty list
    static void testGetIngredients_empty() {
        ArrayList<Ingredient> required = new ArrayList<>();

        Recipe recipe = new Recipe("Empty Recipe", required);

        check("getIngredients - empty list",
            recipe.getIngredients().isEmpty());
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