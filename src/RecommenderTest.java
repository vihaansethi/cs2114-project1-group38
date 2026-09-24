package src;

import java.util.ArrayList;

/**
 * Manual test driver for {@link Recommender}. Each test method builds a small
 * pantry and/or recipe book, calls a Recommender method, and reports results
 * through {@link #check(String, boolean)}. A final meta-test confirms that
 * {@code check} itself records failures correctly.
 */
public class RecommenderTest
{

    /** Number of checks that have passed so far. */
    static int passed = 0;

    /** Number of checks that have failed so far. */
    static int failed = 0;

    /**
     * Runs every test in order and prints the pass/fail totals.
     *
     * @param args
     *            command-line arguments (unused)
     */
    public static void main(String[] args)
    {
        new RecommenderTest();
        testFindMissing_normal();
        testFindMissing_badInput();
        testGetFullMatches_normal();
        testGetFullMatches_emptyBook();
        testGetAlmostMatches_withinRange();
        testGetAlmostMatches_outsideRange();

        testCheck_reportsFailureCorrectly();

        System.out.println("\n" + passed + " passed, " + failed + " failed");
    }


    /**
     * Normal case for {@code findMissing}: the recipe needs [egg, flour] and
     * the pantry has both, so the returned list should be empty.
     */
    static void testFindMissing_normal()
    {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");
        pantry.addIngredient("flour");

        ArrayList<Ingredient> required = new ArrayList<>();
        required.add(new Ingredient("egg"));
        required.add(new Ingredient("flour"));
        Recipe recipe = new Recipe("Pancakes", required);

        ArrayList<Ingredient> missing =
            new Recommender().findMissing(recipe, pantry);
        check("findMissing - normal (full match)", missing.isEmpty());
    }


    /**
     * Bad-input case for {@code findMissing}: the recipe needs [egg, flour,
     * sugar] but the pantry only has [egg], so the result should be [flour,
     * sugar].
     */
    static void testFindMissing_badInput()
    {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");

        ArrayList<Ingredient> required = new ArrayList<>();
        required.add(new Ingredient("egg"));
        required.add(new Ingredient("flour"));
        required.add(new Ingredient("sugar"));
        Recipe recipe = new Recipe("Cookies", required);

        ArrayList<Ingredient> missing =
            new Recommender().findMissing(recipe, pantry);
        check(
            "findMissing - missing 2 ingredients (size)",
            Integer.valueOf(missing.size()).equals(2));
        check(
            "findMissing - first is flour",
            missing.get(0).getName().equals("flour"));
        check(
            "findMissing - second is sugar",
            missing.get(1).getName().equals("sugar"));
    }


    /**
     * Normal case for {@code getFullMatches}: the pantry fully matches 2 of 5
     * recipes (Pancakes and Cookies), so only those two should be returned.
     */
    static void testGetFullMatches_normal()
    {
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

        ArrayList<Recipe> fullMatches =
            new Recommender().getFullMatches(book, pantry);
        check(
            "getFullMatches - size is 2",
            Integer.valueOf(fullMatches.size()).equals(2));
        check(
            "getFullMatches - first is Pancakes",
            fullMatches.get(0).getName().equals("Pancakes"));
        check(
            "getFullMatches - second is Cookies",
            fullMatches.get(1).getName().equals("Cookies"));
    }


    /**
     * Bad-input case for {@code getFullMatches}: an empty recipe book should
     * produce an empty list without crashing.
     */
    static void testGetFullMatches_emptyBook()
    {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");
        RecipeBook emptyBook = new RecipeBook();

        ArrayList<Recipe> fullMatches =
            new Recommender().getFullMatches(emptyBook, pantry);
        check(
            "getFullMatches - empty book returns empty list",
            fullMatches.isEmpty());
    }


    /**
     * Normal case for {@code getAlmostMatches}: a recipe missing exactly one
     * ingredient with {@code maxMissing = 1} should be included.
     */
    static void testGetAlmostMatches_withinRange()
    {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");
        pantry.addIngredient("flour");

        RecipeBook book = new RecipeBook();
        ArrayList<Ingredient> needsMilk = new ArrayList<>();
        needsMilk.add(new Ingredient("egg"));
        needsMilk.add(new Ingredient("flour"));
        needsMilk.add(new Ingredient("milk"));
        book.addRecipe(new Recipe("Waffles", needsMilk));

        ArrayList<Recipe> almost =
            new Recommender().getAlmostMatches(book, pantry, 1);
        check(
            "getAlmostMatches - size is 1",
            Integer.valueOf(almost.size()).equals(1));
        check(
            "getAlmostMatches - is Waffles",
            almost.get(0).getName().equals("Waffles"));
    }


    /**
     * Bad-input case for {@code getAlmostMatches}: a recipe missing 3
     * ingredients is excluded when {@code maxMissing = 1}, and a recipe missing
     * 0 ingredients is excluded too, so the result should be empty.
     */
    static void testGetAlmostMatches_outsideRange()
    {
        Pantry pantry = new Pantry();
        pantry.addIngredient("egg");

        RecipeBook book = new RecipeBook();

        ArrayList<Ingredient> fullMatch = new ArrayList<>();
        fullMatch.add(new Ingredient("egg"));
        book.addRecipe(new Recipe("Boiled Egg", fullMatch)); // 0 missing,
                                                             // should NOT
                                                             // appear

        ArrayList<Ingredient> tooFar = new ArrayList<>();
        tooFar.add(new Ingredient("flour"));
        tooFar.add(new Ingredient("sugar"));
        tooFar.add(new Ingredient("butter"));
        book.addRecipe(new Recipe("Cake", tooFar)); // 3 missing, should NOT
                                                    // appear with maxMissing=1

        ArrayList<Recipe> almost =
            new Recommender().getAlmostMatches(book, pantry, 1);
        check(
            "getAlmostMatches - excludes 0-missing and over-threshold",
            almost.isEmpty());
    }


    /**
     * Records the result of a single test condition. Increments {@link #passed}
     * and prints a PASS line if the condition is true; otherwise increments
     * {@link #failed} and prints a FAIL line.
     *
     * @param testName
     *            a description printed alongside the result
     * @param condition
     *            {@code true} if the test passed, {@code false} if not
     */
    static void check(String testName, boolean condition)
    {
        if (condition)
        {
            passed++;
            System.out.println("PASS: " + testName);
        }
        else
        {
            failed++;
            System.out.println("FAIL: " + testName);
        }
    }


    /**
     * Meta-test that verifies {@link #check(String, boolean)} detects a failing
     * condition. It deliberately fails one check, confirms that {@code failed}
     * went up by one and {@code passed} did not change, then restores the
     * counters so the deliberate failure isn't counted in the final totals.
     */
    static void testCheck_reportsFailureCorrectly()
    {
        int passedBefore = passed;
        int failedBefore = failed;

        check("[meta-test] intentional failing condition", false);

        boolean failedIncremented =
            Integer.valueOf(failed).equals(failedBefore + 1);
        boolean passedUnchanged = Integer.valueOf(passed).equals(passedBefore);

        passed = passedBefore;
        failed = failedBefore;

        check("check() increments failed on false", failedIncremented);
        check("check() does not increment passed on false", passedUnchanged);
    }
}
