package src;

import java.util.ArrayList;

public class Pantry {
	priate ArrayList<Ingredient> items;

	public Pantry() {
		//TODO: initiate items as an empty ArrayList
		items = new ArrayList<Ingredient>();

	}

	public void addIngredient(String name) {
		//TODO: validate name is not blank
		//TODO: check if already in pantry
		//TODO: create Ingredient and add to items
	}

	public boolean removeIngredient(String name) {
		//TODO: search items for matching ingredient
		//TODO: remove if found, return true; otherwise 
		//return false
		return false;
	}

	public boolean isEmpty() {
		//TODO: return true if item has no elements
		return items.isEmpty();
	}
}