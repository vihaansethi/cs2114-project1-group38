package src;

import junit.framework.TestCase;

/**
 * Tests the Pantry class, covering adding, removing, and viewing
 * ingredients, along with input validation for blank, too-long,
 * duplicate, and null names.
 */
public class PantryTest extends TestCase
{
    private Pantry pantry;

    /**
     * Sets up a fresh, empty pantry before each test.
     */
    public void setUp()
    {
        pantry = new Pantry();
    }


    /**
     * Builds a string of the given character repeated a number of times.
     * @param c the character to repeat
     * @param count how many times to repeat it
     * @return the built string
     */
    private String makeString(char c, int count)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++)
        {
            sb.append(c);
        }
        return sb.toString();
    }


    /**
     * Tests that a new pantry starts out empty.
     */
    public void testConstructor()
    {
        assertTrue(pantry.isEmpty());
        assertNotNull(pantry.getItems());
        assertEquals(0, pantry.getItems().size());
    }


    /**
     * Tests adding a valid ingredient.
     */
    public void testAddIngredientValid()
    {
        pantry.addIngredient("Salt");
        assertFalse(pantry.isEmpty());
        assertEquals(1, pantry.getItems().size());
        assertEquals(new Ingredient("Salt"), pantry.getItems().get(0));
    }


    /**
     * Tests adding several different ingredients keeps them in order.
     */
    public void testAddMultipleIngredients()
    {
        pantry.addIngredient("Salt");
        pantry.addIngredient("Pepper");
        pantry.addIngredient("Flour");
        assertEquals(3, pantry.getItems().size());
        assertEquals(new Ingredient("Salt"), pantry.getItems().get(0));
        assertEquals(new Ingredient("Pepper"), pantry.getItems().get(1));
        assertEquals(new Ingredient("Flour"), pantry.getItems().get(2));
    }


    /**
     * Tests that leading and trailing whitespace is trimmed before
     * the ingredient is stored.
     */
    public void testAddIngredientTrimsWhitespace()
    {
        pantry.addIngredient("   Sugar   ");
        assertEquals(1, pantry.getItems().size());
        assertEquals(new Ingredient("Sugar"), pantry.getItems().get(0));
    }


    /**
     * Tests that a null name is rejected.
     */
    public void testAddIngredientNull()
    {
        pantry.addIngredient(null);
        assertTrue(pantry.isEmpty());
    }


    /**
     * Tests that an empty name is rejected.
     */
    public void testAddIngredientEmpty()
    {
        pantry.addIngredient("");
        assertTrue(pantry.isEmpty());
    }


    /**
     * Tests that a name made only of whitespace is rejected.
     */
    public void testAddIngredientWhitespaceOnly()
    {
        pantry.addIngredient("     ");
        assertTrue(pantry.isEmpty());
    }


    /**
     * Tests that a name longer than 50 characters is rejected.
     */
    public void testAddIngredientTooLong()
    {
        pantry.addIngredient(makeString('a', 51));
        assertTrue(pantry.isEmpty());
    }


    /**
     * Tests that a name of exactly 50 characters is accepted.
     */
    public void testAddIngredientExactlyFiftyChars()
    {
        pantry.addIngredient(makeString('b', 50));
        assertEquals(1, pantry.getItems().size());
    }


    /**
     * Tests that a long name padded with whitespace is judged by its
     * trimmed length, so it is still accepted.
     */
    public void testAddIngredientLongWithPadding()
    {
        pantry.addIngredient("  " + makeString('c', 50) + "  ");
        assertEquals(1, pantry.getItems().size());
    }


    /**
     * Tests that adding the same ingredient twice only stores it once.
     */
    public void testAddIngredientDuplicate()
    {
        pantry.addIngredient("Eggs");
        pantry.addIngredient("Eggs");
        assertEquals(1, pantry.getItems().size());
    }


    /**
     * Tests that a duplicate with extra whitespace is still caught.
     */
    public void testAddIngredientDuplicateWithWhitespace()
    {
        pantry.addIngredient("Milk");
        pantry.addIngredient("  Milk  ");
        assertEquals(1, pantry.getItems().size());
    }


    /**
     * Tests removing an ingredient that is in the pantry.
     */
    public void testRemoveIngredientPresent()
    {
        pantry.addIngredient("Butter");
        assertTrue(pantry.removeIngredient("Butter"));
        assertTrue(pantry.isEmpty());
    }


    /**
     * Tests removing an ingredient that is not in the pantry.
     */
    public void testRemoveIngredientAbsent()
    {
        pantry.addIngredient("Butter");
        assertFalse(pantry.removeIngredient("Cheese"));
        assertEquals(1, pantry.getItems().size());
    }


    /**
     * Tests removing from an empty pantry.
     */
    public void testRemoveIngredientFromEmpty()
    {
        assertFalse(pantry.removeIngredient("Rice"));
        assertTrue(pantry.isEmpty());
    }


    /**
     * Tests that removing a null name returns false and changes nothing.
     */
    public void testRemoveIngredientNull()
    {
        pantry.addIngredient("Rice");
        assertFalse(pantry.removeIngredient(null));
        assertEquals(1, pantry.getItems().size());
    }


    /**
     * Tests that the name passed to remove is trimmed first.
     */
    public void testRemoveIngredientTrimsWhitespace()
    {
        pantry.addIngredient("Garlic");
        assertTrue(pantry.removeIngredient("   Garlic  "));
        assertTrue(pantry.isEmpty());
    }


    /**
     * Tests that removing one ingredient leaves the others in place
     * and in order.
     */
    public void testRemoveIngredientFromMiddle()
    {
        pantry.addIngredient("Onion");
        pantry.addIngredient("Carrot");
        pantry.addIngredient("Celery");
        assertTrue(pantry.removeIngredient("Carrot"));
        assertEquals(2, pantry.getItems().size());
        assertEquals(new Ingredient("Onion"), pantry.getItems().get(0));
        assertEquals(new Ingredient("Celery"), pantry.getItems().get(1));
    }


    /**
     * Tests that removing the same ingredient twice fails the second time.
     */
    public void testRemoveIngredientTwice()
    {
        pantry.addIngredient("Tomato");
        assertTrue(pantry.removeIngredient("Tomato"));
        assertFalse(pantry.removeIngredient("Tomato"));
    }


    /**
     * Tests that an ingredient can be added back after being removed.
     */
    public void testAddAfterRemove()
    {
        pantry.addIngredient("Basil");
        pantry.removeIngredient("Basil");
        pantry.addIngredient("Basil");
        assertEquals(1, pantry.getItems().size());
    }


    /**
     * Tests that isEmpty updates correctly as items come and go.
     */
    public void testIsEmpty()
    {
        assertTrue(pantry.isEmpty());
        pantry.addIngredient("Oil");
        assertFalse(pantry.isEmpty());
        pantry.removeIngredient("Oil");
        assertTrue(pantry.isEmpty());
    }
}