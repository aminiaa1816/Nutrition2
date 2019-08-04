
public class CategoryItem {
	private int categoryId;
	private String categoryDescription;
	private String categoryName;

	public CategoryItem(int id, String name, String description) {
		categoryId = id;
		categoryName = name;
		categoryDescription = description;

	}

	public void setId(int id) {
		categoryId = id;

	}

	public void setName(String name) {
		categoryName = name;

	}

	public void setDescription(String description) {
		categoryDescription = description;

	}

	public int getId() {
		return categoryId;
	}

	public String getName() {
		return categoryName;

	}

	public String getDescription() {
		return categoryDescription;

	}

}
