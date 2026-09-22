import java.util.ArrayList;

public class Recipe {
	private String name;
	private ArrayList<Ingredient> requiredIngredients;

	public Recipe(String name, ArrayList<Ingredient> Ingrediens) {
		//TODO: store name and ingredients
		this.name = name;
		this.requiredIngredients = ingredients;
	}

	public String getName() {
		//TODO: return recipe name
		return name;
	}

	public ArrayList<Ingredient> getIngredients() {
		//TODO: return requiredIngredients
		return requiredIngredients;
	}
}