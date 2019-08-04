import java.util.ArrayList;

public class GroceryList {
	String allergens;
	Recipe recipe;
	ArrayList<Ingredient> groceryList;

	public GroceryList(Recipe newrecipe, String allergen) {
		allergens = allergen;
		recipe = newrecipe;
		groceryList = makeGroceryList();

	}

	public ArrayList<Ingredient> makeGroceryList() {
		ArrayList<Ingredient> tempGroceryList = new ArrayList<Ingredient>(recipe.getRecipeIngredients().size());
		for (int i = 0; i < recipe.getRecipeIngredients().size(); i++) {
			if (recipe.getRecipeIngredients().get(i).getName().toLowerCase().contains(allergens.toLowerCase()))
				continue;
			tempGroceryList.add(recipe.getRecipeIngredients().get(i));
		}
		return tempGroceryList;
	}

	public void printGroceryList() {

		System.out.println("\n******* GROCERY LIST (excluding your allergens)*******");
		for (int i = 0; i < groceryList.size(); i++) {
			System.out.println((i + 1) + ". " + groceryList.get(i).getName()+" --> "+groceryList.get(i).getMeasure());
		}
		System.out.println("***************************************************");
	} 
}
