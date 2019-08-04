
public class Ingredient {
	private String ingredientName;
	private String ingredientMeasure;

	public Ingredient(String name, String measure) {
		setName(name);
		setMeasure(measure);

	}

	public Ingredient(String name) {
		setName(name);
		setMeasure("");
	}

	public void setName(String name) {
		ingredientName = name;
	}

	public void setMeasure(String measure) {
		ingredientMeasure = measure;
	}

	public String getName() {
		return ingredientName;
	}

	public String getMeasure() {
		return ingredientMeasure;
	}

}
