package finalproject.rlovelett.internetcookbook;

import java.io.Serializable;

/**
 * Class Recipe - Object class that stores the information about a recipe saved on the app. Includes a name,
 * how long it will take to make the recipe, what meal/course the recipe can be made for, and the web address
 * (if any) for the recipe.
 *
 * Created by rlovelett on 4/10/2017.
 */

public class Recipe implements Serializable{ //implements serializable for AddRecipe intent return

    private String name;//name of the Recipe/Food
    private String cookTime;//Time required to make the recipe: prep + cook time
    private String dishType;//What course is it served for? Breakfast? Lunch? Dinner?
    private String webURL;//WebURL of recipe, if any

    public Recipe(String name, String cookTime, String dishType, String webURL) {
        this.name = name;
        this.cookTime = cookTime;
        this.dishType = dishType;
        this.webURL = webURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }
}
