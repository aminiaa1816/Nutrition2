import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CategoryList {
	ArrayList<CategoryItem> categoryList;
	private static final String CATEGORIES_API_URL = "https://www.themealdb.com/api/json/v1/1/categories.php";
	private static final String USER_AGENT = "Mozilla/5.0";

	public CategoryList() throws Exception {
		categoryList = getCategoryListFromApi();
	}

	public ArrayList<CategoryItem> getCategoryListFromApi() throws IOException, JSONException {

		URL obj = new URL(CATEGORIES_API_URL);
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

		jsonarray = json.getJSONArray("categories");
		ArrayList<CategoryItem> tempCategories = new ArrayList<CategoryItem>(jsonarray.length() + 100);

		for (int i = 0; i < jsonarray.length(); i++) {
			json = new JSONObject(jsonarray.get(i).toString());
			tempCategories.add(new CategoryItem(Integer.parseInt(json.getString("idCategory")),
					json.getString("strCategory"), json.getString("strCategoryDescription")));

		}

		return tempCategories;

	}

	public int count() {
		return categoryList.size();
	}

	public CategoryItem getCategory(int index) {
		return categoryList.get(index);
	}

	public ArrayList<CategoryItem> getCategories() {
		return categoryList;
	}

	public int getCategoryId(int index) {
		return categoryList.get(index).getId();
	}

	public String getCategoryName(int index) {
		return categoryList.get(index).getName();
	}

	public String getCategoryDescription(int index) {
		return categoryList.get(index).getDescription();
	}

	public void printCategoriesMenu() {
		System.out.println("\n******* CATEGORY LIST *******");
		for (int i = 0; i < count(); i++) {
			System.out.println(getCategory(i).getId() + ". " + getCategory(i).getName());
		}
		System.out.println("*****************************");
		System.out.print("Enter Choice (1-" + count() + ") >> ");

	}

}
