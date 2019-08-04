import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {
	
	@Test
	void CategoryItemTest() {
		CategoryItem ci=new CategoryItem(1,"category1","this is category 1 test");
		assertTrue(ci.getId()==1);
		assertFalse(ci.getId()!=1);
		assertTrue(ci.getName().equals("category1"));
	}

	@Test
	void IngredientTest() {
		Ingredient ing=new Ingredient("Ingredient1","Measure1");
		assertTrue(ing.getName().equals("Ingredient1"));
		assertTrue(ing.getMeasure().equals("Measure1"));
	}

	@Test
	void CategoryListApiTest() throws Exception {
		CategoryList cl=new CategoryList();
		assertTrue(cl.count()==12);
		assertTrue(cl.getCategory(0).getName().equals("Beef"));

	}


	@Test
	void RecipeListByApiTest() throws Exception {
		CategoryList cl=new CategoryList();
		assertTrue(cl.count()==12);
		RecipeList rpl=new RecipeList(cl.getCategory(7),"api");
		assertTrue(rpl.size()==19);
		assertTrue(rpl.getRecipe(0).getName().equals("Baked salmon with fennel & tomatoes"));
	}

	@Test
	void RecipeListByFileTest() throws Exception {
		CategoryList cl=new CategoryList();
		assertTrue(cl.count()==12);
		RecipeList rpl=new RecipeList(cl.getCategory(7),"Seafood.csv");
		assertTrue(rpl.size()==19);
		assertTrue(rpl.getRecipe(1).getName().equals("Cajun spiced fish tacos"));
	}


	@Test
	void RecipeTest() throws Exception {
		CategoryList cl=new CategoryList();
		RecipeList rpl=new RecipeList(cl.getCategory(7),"Seafood.csv");
		assertTrue(rpl.getRecipe(2).getId()==52944);
		assertTrue(rpl.getRecipe(2).getName().equals("Escovitch Fish"));
	}
	

}
