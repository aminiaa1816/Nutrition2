import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeList {

	ArrayList<Recipe> recipeList;
	private CategoryItem category;
	private static final String RECIPE_API_BASE_URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
	private static final String RECIPE_DETAIL_API_BASE_URL = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=";
	private static final String RECIPE_BY_CATEGORY_API_BASE_URL = "https://www.themealdb.com/api/json/v1/1/filter.php?c=";
	private static final String USER_AGENT = "Mozilla/5.0";

	public RecipeList(CategoryItem category,String source) throws Exception {
		if(source.equalsIgnoreCase("api"))
			recipeList = getRecipeByApiCategory(category);
		else
			recipeList = getRecipeByFile(category,source);
	}

	public ArrayList<Recipe> getRecipeByFile(CategoryItem cat,String filename) throws IOException {
		category = cat;

			
		ArrayList<Recipe> tempRecipeList=new ArrayList<Recipe>(111);

		try (FileReader reader = new FileReader(filename); BufferedReader br = new BufferedReader(reader)) {

			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] csvSplitArray = line.split(",");


				int recipeId = Integer.parseInt((csvSplitArray[0]));
				String recipeName = (csvSplitArray[1]);


				String[] ingredients = new String[25];
				String[] measures = new String[25];
				
				
				for (int i = 1; i <= 20; i++) {
					if((i+6)<csvSplitArray.length)
					ingredients[i - 1] = (csvSplitArray[i+6]);
					if((i+26)<csvSplitArray.length)
					measures[i - 1] = csvSplitArray[26+ i];
				}
				Recipe recipe = new Recipe(recipeId, recipeName, "", ingredients, measures, category);

				tempRecipeList.add(recipe);


			//	break;
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}		
		
		return tempRecipeList;


		
	}
	
	public ArrayList<Recipe> getRecipeByApiCategory(CategoryItem cat) throws IOException, JSONException {
		category = cat;

		URL obj = new URL(RECIPE_BY_CATEGORY_API_BASE_URL + category.getName());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONObject json = new JSONObject(response.toString());
		JSONArray jsonarray;

		jsonarray = json.getJSONArray("meals");
		ArrayList<Integer> tempRecipeIDs = new ArrayList<Integer>(jsonarray.length() + 100);

		for (int i = 0; i < jsonarray.length(); i++) {
			json = new JSONObject(jsonarray.get(i).toString());
			tempRecipeIDs.add((Integer.parseInt(json.getString("idMeal"))));
			// System.out.println(json.getString("idMeal"));

		}
		ArrayList<Recipe> tempRecipeList = getRecipeListByRecipeIDS(tempRecipeIDs);

		return tempRecipeList;

	}

	public ArrayList<Recipe> getRecipeListByRecipeIDS(ArrayList<Integer> ids) throws IOException, JSONException {

		ArrayList<Recipe> tempRecipeList = new ArrayList<Recipe>(ids.size() + 10);

		for (int i = 0; i < ids.size(); i++) {
			tempRecipeList.add(getRecipehDetailsByIdApi(ids.get(i)));
			// break;
		}
        Collections.sort(tempRecipeList, new OrderByName());
		return tempRecipeList;

	}

	public Recipe getRecipehDetailsByIdApi(int id) throws IOException, JSONException {

		URL obj = new URL(RECIPE_DETAIL_API_BASE_URL + id);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONObject json = new JSONObject(response.toString());
		JSONArray jsonarray;

		jsonarray = json.getJSONArray("meals");
		ArrayList<Integer> tempRecipeIDs = new ArrayList<Integer>(jsonarray.length() + 100);

		json = new JSONObject(jsonarray.get(0).toString());

		int recipeId = Integer.parseInt(json.getString("idMeal"));
		String recipeName = json.getString("strMeal");
		String recipeInstructions = json.getString("strInstructions");

		String[] ingredients = new String[25];
		String[] measures = new String[25];

		for (int i = 1; i <= 20; i++) {
			ingredients[i - 1] = json.getString("strIngredient" + i);
			measures[i - 1] = json.getString("strMeasure" + i);
		}
		Recipe recipe = new Recipe(recipeId, recipeName, recipeInstructions, ingredients, measures, category);

		return recipe;

	}

	public int size() {
		return recipeList.size();
	}

	public Recipe getRecipe(int i) {
		return recipeList.get(i);
	}

	public void printRecipeMenuByCategory() {
		System.out.println("\n******* RECIPE LIST (" + category.getName() + ") *******");
		for (int i = 0; i < size(); i++) {
			System.out.println((i + 1) + ". " + recipeList.get(i).getName());
		}
		System.out.println("*****************************");
		System.out.print("Enter Choice (1-" + size() + ") >> ");

	}

}
