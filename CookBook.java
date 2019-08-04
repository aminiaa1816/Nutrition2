import java.util.Scanner;

public class CookBook {
	public void allregensMenu(String[] allergensList) {

		System.out.println("\n******* ALLERGENS LIST *******");
		for (int i = 0; i < allergensList.length; i++) {
			System.out.println((i + 1) + ". " + allergensList[i]);
		}
		System.out.println("*****************************");
		System.out.print("Enter Choice (1-11) >> ");

	}
	
	public void userMenu() {

		System.out.println("\n********* USER MENU *********");

		System.out.println("1.Set Allergens");
		System.out.println("2.Set Recipe Category");
		System.out.println("3.Add Recipe List By Api");
		System.out.println("4.Add Recipe List By File");
		System.out.println("5.Print Recipe List (including allergens)");
		System.out.println("6.Print Grocery List (excluding allergens)");
		System.out.println("7.Exit");
		
		System.out.println("*****************************");
		System.out.print("Enter Choice (1-7) >> ");

	}
	

	public static void main(String[] args) throws Exception {
		CookBook cb=new CookBook();

		CategoryList categories = new CategoryList();
		Recipe recipe = null;
		String[] allergensList = { "Milk", "Egg", "Fish", "Peanut", "Wheat", "Rice", "Garlic", "Celery", "Mustard",
				"Fruit", "Sesame" };

		int allergenChoice = -1;		
		int categoryChoice = -1;
		int recipeChoice = -1;
		int choice=-1;

		RecipeList recipeList=null;
		GroceryList gorceryList=null;
		Scanner in = new Scanner(System.in);
		System.out.print("\n\n************** COOKBOOK **************\n");
		
	//	recipeList = new RecipeList(categories.getCategory(7),"Seafood.csv");
	
		do {
			cb.userMenu();
			choice = in.nextInt();
			while (choice < 1 || choice > 7) {
				System.out.println("Invalid option...");
				cb.userMenu();
				choice = in.nextInt();
				}
			
				switch(choice) {
					case 1:
						cb.allregensMenu(allergensList);
						allergenChoice = in.nextInt();
						while (allergenChoice < 1 || allergenChoice > 11) {
							System.out.println("Invalid option...");
							cb.allregensMenu(allergensList);
							allergenChoice = in.nextInt();

						}
						System.out.println("\nYour allergen is set to: " + allergensList[allergenChoice - 1]);						
						break;
					case 2:
						categories.printCategoriesMenu();

						categoryChoice = in.nextInt();
						while (categoryChoice < 1 || categoryChoice > categories.count()) {
							System.out.println("Invalid option...");
							categories.printCategoriesMenu();
							categoryChoice = in.nextInt();
						}
						System.out.println("\nYour recipe category is set to: " + categories.getCategory(categoryChoice - 1).getName());
						break;
					case 3:
						if (categoryChoice < 1 || categoryChoice > categories.count()) {
							System.out.println("\nChoose a category before getting the recipe list");
							break;
						}
						recipeList = new RecipeList(categories.getCategory(categoryChoice - 1),"api");

						recipeList.printRecipeMenuByCategory();
						
						recipeChoice = in.nextInt();
						while (recipeChoice < 1 || recipeChoice > recipeList.size()) {
							System.out.println("Invalid option...");
							recipeList.printRecipeMenuByCategory();
							recipeChoice = in.nextInt();
						}
						
						break;
					case 4:
						if (categoryChoice < 1 || categoryChoice > categories.count()) {
							System.out.println("\nChoose a category before getting the recipe list");
							break;
						}
						System.out.println("\nEnter filename to recipe list for "+categories.getCategory(categoryChoice - 1).getName()+" category");
						String filename=in.next();
						
						recipeList = new RecipeList(categories.getCategory(categoryChoice - 1),filename);

						recipeList.printRecipeMenuByCategory();
						
						recipeChoice = in.nextInt();
						while (recipeChoice < 1 || recipeChoice > recipeList.size()) {
							System.out.println("Invalid option...");
							recipeList.printRecipeMenuByCategory();
							recipeChoice = in.nextInt();
						}
						
						break;
					case 5:
						if (recipeChoice < 1 ) {
							System.out.println("\nChoose a recipe before getting the recipe details");
							break;
						}
						recipe = recipeList.getRecipe(recipeChoice-1);
						recipe.printRecipeDetails();
						break;
					case 6:
						if (categoryChoice < 1 ) {
							System.out.println("\nChoose a category before getting the grocery list");
							break;
						}
						if (allergenChoice < 1 ) {
							System.out.println("\nChoose an allergen before getting the grocery list");
							break;
						}
						if (recipeChoice < 1 ) {
							System.out.println("\nChoose a recipe before getting the grocery list");
							break;
						}
						gorceryList = new GroceryList(recipe,allergensList[allergenChoice-1]);
						gorceryList.printGroceryList();
												break;
					case 7:
						System.out.println("\n\n\n\t\t\tThank you!!!");
						break;
					default:
						System.out.println("\nInvalid Choice!!!");
						break;
						
						
					
				}
				
			
		}while(choice!=7);
		

		
		
	}

}
