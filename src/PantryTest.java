package src;

public class PantryTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        testAddIngredient_normal();
        testAddIngredient_blank();
        testAddIngredient_null();
        testAddIngredient_tooLong();
        testAddIngredient_duplicate();
        testAddIngredient_duplicateDifferentCase();
        testAddIngredient_trimsSpaces();

        testRemoveIngredient_normal();
        testRemoveIngredient_notFound();
        testRemoveIngredient_null();
        testRemoveIngredient_differentCase();

        testGetItems_normal();

        testIsEmpty_true();
        testIsEmpty_false();

        System.out.println(
            "\n" + passed + " passed, " + failed + " failed");
    }


    // Normal: add "egg" -> pantry contains egg
    static void testAddIngredient_normal() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("egg");

        boolean correct =
            pantry.getItems().size() == 1
            && pantry.getItems().get(0).getName().equals("egg");

        check("addIngredient - normal", correct);
    }


    // Bad input: blank ingredient -> pantry stays empty
    static void testAddIngredient_blank() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("");

        check("addIngredient - blank",
            pantry.isEmpty());
    }


    // Bad input: null ingredient -> pantry stays empty
    static void testAddIngredient_null() {
        Pantry pantry = new Pantry();

        pantry.addIngredient(null);

        check("addIngredient - null",
            pantry.isEmpty());
    }


    // Bad input: ingredient over 50 characters -> not added
    static void testAddIngredient_tooLong() {
        Pantry pantry = new Pantry();

        String longName =
            "abcdefghijklmnopqrstuvwxyz"
            + "abcdefghijklmnopqrstuvwxyz";

        pantry.addIngredient(longName);

        check("addIngredient - too long",
            pantry.isEmpty());
    }


    // Bad input: same ingredient added twice -> only one stored
    static void testAddIngredient_duplicate() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("egg");
        pantry.addIngredient("egg");

        check("addIngredient - duplicate",
            pantry.getItems().size() == 1);
    }


    // Edge case: Egg and egg should count as duplicates
    static void testAddIngredient_duplicateDifferentCase() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("Egg");
        pantry.addIngredient("egg");

        check("addIngredient - duplicate different case",
            pantry.getItems().size() == 1);
    }


    // Edge case: spaces around name should be removed
    static void testAddIngredient_trimsSpaces() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("   egg   ");

        boolean correct =
            pantry.getItems().size() == 1
            && pantry.getItems().get(0).getName().equals("egg");

        check("addIngredient - trims spaces", correct);
    }


    // Normal: remove existing ingredient -> returns true
    static void testRemoveIngredient_normal() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("egg");

        boolean result = pantry.removeIngredient("egg");

        boolean correct =
            result
            && pantry.isEmpty();

        check("removeIngredient - normal", correct);
    }


    // Bad input: remove ingredient not in pantry -> returns false
    static void testRemoveIngredient_notFound() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("egg");

        boolean result = pantry.removeIngredient("milk");

        boolean correct =
            !result
            && pantry.getItems().size() == 1;

        check("removeIngredient - not found", correct);
    }


    // Bad input: remove null -> returns false
    static void testRemoveIngredient_null() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("egg");

        boolean result = pantry.removeIngredient(null);

        boolean correct =
            !result
            && pantry.getItems().size() == 1;

        check("removeIngredient - null", correct);
    }


    // Edge case: remove "EGG" when pantry contains "egg"
    static void testRemoveIngredient_differentCase() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("egg");

        boolean result = pantry.removeIngredient("EGG");

        boolean correct =
            result
            && pantry.isEmpty();

        check("removeIngredient - different case", correct);
    }


    // Normal: getItems returns all pantry ingredients
    static void testGetItems_normal() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("egg");
        pantry.addIngredient("milk");
        pantry.addIngredient("flour");

        boolean correct =
            pantry.getItems().size() == 3
            && pantry.getItems().get(0).getName().equals("egg")
            && pantry.getItems().get(1).getName().equals("milk")
            && pantry.getItems().get(2).getName().equals("flour");

        check("getItems - normal", correct);
    }


    // Normal: new pantry should be empty
    static void testIsEmpty_true() {
        Pantry pantry = new Pantry();

        check("isEmpty - true",
            pantry.isEmpty());
    }


    // Normal: pantry with an ingredient should not be empty
    static void testIsEmpty_false() {
        Pantry pantry = new Pantry();

        pantry.addIngredient("egg");

        check("isEmpty - false",
            !pantry.isEmpty());
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