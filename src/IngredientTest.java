package src;

public class IngredientTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        testConstructor_lowercase();
        testGetName_normal();
        testEquals_sameName();
        testEquals_differentCase();
        testEquals_differentName();
        testEquals_null();

        System.out.println(
            "\n" + passed + " passed, " + failed + " failed");
    }


    // Normal: lowercase ingredient name stays lowercase
    static void testConstructor_lowercase() {
        Ingredient ingredient = new Ingredient("egg");

        check("constructor - lowercase",
            ingredient.getName().equals("egg"));
    }


    // Normal: getName returns the ingredient name
    static void testGetName_normal() {
        Ingredient ingredient = new Ingredient("milk");

        check("getName - normal",
            ingredient.getName().equals("milk"));
    }


    // Normal: two ingredients with same name -> true
    static void testEquals_sameName() {
        Ingredient first = new Ingredient("egg");
        Ingredient second = new Ingredient("egg");

        check("equals - same name",
            first.equals(second));
    }


    // Normal: "Egg" and "egg" should match
    static void testEquals_differentCase() {
        Ingredient first = new Ingredient("Egg");
        Ingredient second = new Ingredient("egg");

        check("equals - different case",
            first.equals(second));
    }


    // Bad input: different ingredient names -> false
    static void testEquals_differentName() {
        Ingredient first = new Ingredient("egg");
        Ingredient second = new Ingredient("eggplant");

        check("equals - different name",
            !first.equals(second));
    }


    // Bad input: comparing with null -> false
    static void testEquals_null() {
        Ingredient ingredient = new Ingredient("egg");

        check("equals - null",
            !ingredient.equals(null));
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