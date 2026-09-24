package src;

import java.util.ArrayList;

public class Pantry
{
    private ArrayList<Ingredient> items;

    public Pantry()
    {
        items = new ArrayList<Ingredient>();

    }


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


    public ArrayList<Ingredient> getItems()
    {
        return items;
    }


    public boolean isEmpty()
    {
        return items.isEmpty();
    }
}
