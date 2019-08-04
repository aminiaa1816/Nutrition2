import java.util.ArrayList;

public class Recipe {
	private int recipeId;
	private String recipeName;
	private String recipeInstructions;
	private CategoryItem recipeCategory;

	private ArrayList<Ingredient> recipeIngredients;

	public Recipe(int id, String name, String instructions, String[] ingredients, String[] measures,
			CategoryItem category) {
		recipeIngredients = new ArrayList<Ingredient>(25);
		setId(id);
		setName(name);
		setInstructions(instructions);
		setRecipeCategory(category);
		addRecipeIngredients(ingredients, measures);
	}

	public int getId() {
		return recipeId;
	}

	public String getName() {
		return recipeName;
	}

	public String getInstructions() {
		return recipeInstructions;
	}

	public CategoryItem getRecipeCategory() {
		return recipeCategory;
	}

	public ArrayList<Ingredient> getRecipeIngredients() {
		return recipeIngredients;
	}

	public void setId(int id) {
		recipeId = id;
	}

	public void setName(String name) {
		recipeName = name;
	}

	public void setInstructions(String instructions) {
		recipeInstructions = instructions;
	}

	public void setRecipeCategory(CategoryItem category) {
		recipeCategory = category;
	}

	public void addRecipeIngredients(String ingredientName, String measure) {

		recipeIngredients.add(new Ingredient(ingredientName, measure));
	}

	public void addRecipeIngredients(String[] ingredientNames, String[] measures) {
		for (int i = 0; i < 20; i++) {
			if ( ingredientNames[i].trim() == null ||ingredientNames[i].trim() == "" || (ingredientNames[i].trim()).length()==0)
				break;
			addRecipeIngredients(ingredientNames[i], measures[i]);
		}
	}

	  
    public int compareTo(Recipe s)
    {
        return this.recipeName.compareTo(s.recipeName);     
    }
     
	public void printRecipeDetails() {
		System.out.println("\n************** RECIPE DETAILS ("+recipeName+" recipe) **************");
		System.out.println("Recipe ID: "+getId());
		System.out.println("Recipe Name: "+getName());
		System.out.println("Recipe Instructions :\n\t\t "+getInstructions());
		System.out.println("\nRecipe Category : "+getRecipeCategory().getName());
		System.out.println("\nRecipe Ingredients (including your allergens) : ");
		
		for(int i=0;i<recipeIngredients.size();i++) {
			if(recipeIngredients.get(i).getName()!=null||recipeIngredients.get(i).getName()!="")
			System.out.println("\t"+(i+1)+". "+recipeIngredients.get(i).getName()+" --> "+recipeIngredients.get(i).getMeasure());
		}
		
		System.out.println("***************************************************************");
	}	
	
}
