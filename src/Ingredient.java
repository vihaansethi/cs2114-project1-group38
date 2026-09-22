public class Ingredient {
	private String name;

	public Ingredient(String name) {
		//TODO: sotre name in lowercase for case insensitive match
		this.name = name;
	}

	public String getName() {
		//TODO: return the ingredient name
		return name;
	}

	public boolean equals(Ingredient other) {
		//TODO: return true if name match
		return false;
	}
}