package src;
import java.util.ArrayList;

public class RecommenderTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        testFindMissing_normal();
        testFindMissing_badInput();
        testGetFullMatches_normal();
        testGetFullMatches_emptyBook();
        testGetAlmostMatches_withinRange();
        testGetAlmostMatches_outsideRange();

        System.out.println("\n" + passed + " passed, " + failed + " failed");
    }

    // Normal: recipe needs [egg, flour], pantry has both -> returns empty list
    static void testFindMissing_normal() {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");
        pantry.addIngredient("flour");

        ArrayList<Ingredient> required = new ArrayList<>();
        required.add(new Ingredient("egg"));
        required.add(new Ingredient("flour"));
        Recipe recipe = new Recipe("Pancakes", required);

        ArrayList<Ingredient> missing = new Recommender().findMissing(recipe, pantry);
        check("findMissing - normal (full match)", missing.isEmpty());
    }

    // Bad input: recipe needs [egg, flour, sugar], pantry has only [egg] -> returns [flour, sugar]
    static void testFindMissing_badInput() {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");

        ArrayList<Ingredient> required = new ArrayList<>();
        required.add(new Ingredient("egg"));
        required.add(new Ingredient("flour"));
        required.add(new Ingredient("sugar"));
        Recipe recipe = new Recipe("Cookies", required);

        ArrayList<Ingredient> missing = new Recommender().findMissing(recipe, pantry);
        boolean ok = missing.size() == 2
                && missing.get(0).getName().equals("flour")
                && missing.get(1).getName().equals("sugar");
        check("findMissing - missing 2 ingredients", ok);
    }

    // Normal: pantry matches 2 of 5 recipes fully -> returns those 2
    static void testGetFullMatches_normal() {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");
        pantry.addIngredient("flour");
        pantry.addIngredient("sugar");

        RecipeBook book = new RecipeBook();

        ArrayList<Ingredient> r1 = new ArrayList<>();
        r1.add(new Ingredient("egg"));
        r1.add(new Ingredient("flour"));
        book.addRecipe(new Recipe("Pancakes", r1));

        ArrayList<Ingredient> r2 = new ArrayList<>();
        r2.add(new Ingredient("egg"));
        r2.add(new Ingredient("flour"));
        r2.add(new Ingredient("sugar"));
        book.addRecipe(new Recipe("Cookies", r2));

        ArrayList<Ingredient> r3 = new ArrayList<>();
        r3.add(new Ingredient("egg"));
        r3.add(new Ingredient("milk"));
        book.addRecipe(new Recipe("Omelette", r3));

        ArrayList<Ingredient> r4 = new ArrayList<>();
        r4.add(new Ingredient("beef"));
        book.addRecipe(new Recipe("Steak", r4));

        ArrayList<Ingredient> r5 = new ArrayList<>();
        r5.add(new Ingredient("rice"));
        book.addRecipe(new Recipe("Rice Bowl", r5));

        ArrayList<Recipe> fullMatches = new Recommender().getFullMatches(book, pantry);
        boolean ok = fullMatches.size() == 2
                && fullMatches.get(0).getName().equals("Pancakes")
                && fullMatches.get(1).getName().equals("Cookies");
        check("getFullMatches - 2 of 5 recipes match", ok);
    }

    // Bad input: empty RecipeBook -> returns empty list, no crash
    static void testGetFullMatches_emptyBook() {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");
        RecipeBook emptyBook = new RecipeBook();

        ArrayList<Recipe> fullMatches = new Recommender().getFullMatches(emptyBook, pantry);
        check("getFullMatches - empty book returns empty list", fullMatches.isEmpty());
    }

    // Normal: recipe missing exactly 1 ingredient, maxMissing=1 -> included
    static void testGetAlmostMatches_withinRange() {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");
        pantry.addIngredient("flour");

        RecipeBook book = new RecipeBook();
        ArrayList<Ingredient> needsMilk = new ArrayList<>();
        needsMilk.add(new Ingredient("egg"));
        needsMilk.add(new Ingredient("flour"));
        needsMilk.add(new Ingredient("milk"));
        book.addRecipe(new Recipe("Waffles", needsMilk));

        ArrayList<Recipe> almost = new Recommender().getAlmostMatches(book, pantry, 1);
        boolean ok = almost.size() == 1 && almost.get(0).getName().equals("Waffles");
        check("getAlmostMatches - missing 1, maxMissing 1 -> included", ok);
    }

    // Bad input: recipe missing 3 ingredients, maxMissing=1 -> excluded; also excludes 0-missing
    static void testGetAlmostMatches_outsideRange() {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");

        RecipeBook book = new RecipeBook();

        ArrayList<Ingredient> fullMatch = new ArrayList<>();
        fullMatch.add(new Ingredient("egg"));
        book.addRecipe(new Recipe("Boiled Egg", fullMatch)); // 0 missing, should NOT appear

        ArrayList<Ingredient> tooFar = new ArrayList<>();
        tooFar.add(new Ingredient("flour"));
        tooFar.add(new Ingredient("sugar"));
        tooFar.add(new Ingredient("butter"));
        book.addRecipe(new Recipe("Cake", tooFar)); // 3 missing, should NOT appear with maxMissing=1

        ArrayList<Recipe> almost = new Recommender().getAlmostMatches(book, pantry, 1);
        check("getAlmostMatches - excludes 0-missing and over-threshold", almost.isEmpty());
    }

    static void check(String testName, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("PASS: " + testName);
        } else {
            failed++;
            System.out.println("FAIL: " + testName);
        }
    }
}
