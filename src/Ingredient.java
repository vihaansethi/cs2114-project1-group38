package src;

/**
 * Represents a single food item that can be stored in a Pantry
 * or required by a Recipe.
 *@author Fernando Zuniga Leon 
 *
 */
public class Ingredient
{
    private String name;


    /**
     * Creates a new Ingredient.
     * @param name the ingredient's name
     */
    public Ingredient(String name)
    {
        this.name = name.toLowerCase();
    }


    /**
     * @return the ingredient's name
     */
    public String getName()
    {
        return name;
    }


     /**
     * Compares this ingredient to another by name.
     * @param other the ingredient to compare against
     * @return true if both ingredients have the same name
     */
    public boolean equals(Ingredient other)
    {
        if (other == null)
        {
            return false;
        }
        return this.name.equals(other.getName());
    }
}
