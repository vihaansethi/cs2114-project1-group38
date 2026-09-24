package src;

import java.util.ArrayList;


/**
 * Holds the list of ingredients the user currently has on hand.
 * Supports adding, removing, and viewing pantry contents, and
 * validates input so bad data (blank names, duplicates) never
 * makes it into the list.
 */
public class Pantry
{
    private ArrayList<Ingredient> items;

    /**
     * Creates an empty pantry.
     */
    public Pantry()
    {
        items = new ArrayList<Ingredient>();

    }


    /**
     * Adds an ingredient to the pantry, unless it's blank, too long,
     * or already present.
     * @param name the ingredient name to add
     */
    public void addIngredient(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            System.out.println("Ingredient name can't be blank.");
            return;
        }
        if (name.trim().length() > 50)
        {
            System.out
                .println("That doesn't look like a valid ingredient name.");
            return;
        }

        Ingredient newIngredient = new Ingredient(name.trim());

        for (Ingredient existing : items)
        {
            if (existing.equals(newIngredient))
            {
                System.out.println(name + " is already in your pantry.");
                return;
            }
        }
        items.add(newIngredient);
        System.out.println(name + " added to pantry.");

    }


    /**
     * Removes an ingredient from the pantry if it exists.
     * @param name 
     *      the ingredient name to remove
     * @return 
     *      true if the ingredient was found and removed, false otherwise
     */
    public boolean removeIngredient(String name)
    {
        if (name == null)
        {
            return false;
        }
        Ingredient target = new Ingredient(name.trim());

        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).equals(target))
            {
                items.remove(i);
                return true;
            }
        }
        return false;
    }


    /**
     * @return the list of ingredients currently in the pantry
     */
    public ArrayList<Ingredient> getItems()
    {
        return items;
    }


    /**
     * @return true if the pantry has no ingredients
     */
    public boolean isEmpty()
    {
        return items.isEmpty();
    }
}
