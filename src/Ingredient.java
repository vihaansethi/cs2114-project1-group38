package src;

public class Ingredient
{
    private String name;

    public Ingredient(String name)
    {
        this.name = name.toLowerCase();
    }


    public String getName()
    {
        return name;
    }


    public boolean equals(Ingredient other)
    {
        if (other == null)
        {
            return false;
        }
        return this.name.equals(other.getName());
    }
}
