import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Unit tests for Pantry - covers adding, removing, and checking
 * pantry contents, including bad-input cases.
 */
public class PantryTest {
    private Pantry pantry;

    /**
     * This is the setUp for PantryTest
     */
    public void setUp() {
        pantry = new Pantry();
    }

    /**
     * Normal case: adding a valid ingredient should make the
     * pantry non-empty and contain exactly one item.
     */
    public void testAddIngredientNormalCase() {
        pantry.addIngredient("egg");
        assertFalse(pantry.isEmpty());
        assertEquals(1, pantry.getItems().size());
    }

    /**
     * Bad input: adding a blank ingredient name should be
     * rejected, leaving the pantry empty.
     */
    public void testAddIngredientBlankInput() {
        pantry.addIngredient("");
        assertTrue(pantry.isEmpty());
    }

    /**
     * Bad input: adding the same ingredient twice should not
     * create a duplicate entry.
     */
    public void testAddIngredientDuplicateNotAddedTwice() {
        pantry.addIngredient("egg");
        pantry.addIngredient("egg");
        assertEquals(1, pantry.getItems().size());
    }

    /**
     * Normal case: removing an ingredient that exists should
     * succeed and leave the pantry empty.
     */
    public void testRemoveIngredientNormalCase() {
        pantry.addIngredient("egg");
        boolean result = pantry.removeIngredient("egg");
        assertTrue(result);
        assertTrue(pantry.isEmpty());
    }

    /**
     * Bad input: removing an ingredient that was never added
     * should fail gracefully instead of crashing.
     */
    public void testRemoveIngredientNotInPantry() {
        boolean result = pantry.removeIngredient("milk");
        assertFalse(result);
    }
}