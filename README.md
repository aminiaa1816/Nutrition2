# Nutrition2

Project Summary: Our project allows users to create grocery and recipe lists that factor in their specific allergy needs  
using an API or a list of 12 .csv files. The project has several classes to allow us to have category lists and items, create grocery lists, ingredient lists, to order by name, and to have recipes and recipe lists. 

Note: to run, the user does need to individually upload an external org.json library and will receive an initial error. 

In starting the CategoryList, there is an object constructor that will get a category list from the following API: https://www.themealdb.com/api/json/v1/1/categories.php.

A menu with 7 options is initially displayed that includes: 1) allergens, 2) choose category from a list of APIs, 3) create a recipe list from the API https://www.themealdb.com/api/json/v1/1/categories.php, 4) to add a recipe list by one of 12 .csv files, 5) to print a recipe list and instructions that will include allergens, 6) print the grocery list that excludes the allergens, and 7) to exit the program. 

In the menu, the user has the option of selecting one of the 11 most common allergens that are hardcoded. After picking the allergen, the user will need to set the recipe category from the options provided by the API. After setting the recipe category, the user has two options: to use the API or to use one of 12 .csv files for recipe choices. After the recipe is selected, the user can choose 5 to see the recipe list and directions with the allergens and/or to menu option 6 to get the actual grocery list with the ingredients. The user can then choose menu 7 to exit.
