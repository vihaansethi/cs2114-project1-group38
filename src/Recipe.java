package src;

import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<Ingredient> requiredIngredients;

    public Recipe(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.requiredIngredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return requiredIngredients;
    }
}