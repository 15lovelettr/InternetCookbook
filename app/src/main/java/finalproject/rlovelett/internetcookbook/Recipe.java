package finalproject.rlovelett.internetcookbook;

import java.io.Serializable;

/**
 * Created by rlovelett on 4/10/2017.
 */

public class Recipe implements Serializable{ //implements serializable for AddRecipe intent return

    private String name;//name of the Recipe/Food
    private String cookTime;//Time required to make the recipe: prep + cook time
    private String dishType;//What course is it served for? Breakfast? Lunch? Dinner?
    private String webURL;//WebURL of recipe, if any

    String courseNames[] = {"Breakfast", "Lunch", "Dinner"};//Names used for DishType variable. FINISH!!!!!!!

    public Recipe(String name, String cookTime, String dishType) {
        this.name = name;
        this.cookTime = cookTime;
        this.dishType = dishType;
    }

    public Recipe(String name, String cookTime, String dishType, String webURL) {
        this.name = name;
        this.cookTime = cookTime;
        this.dishType = dishType;
        this.webURL = webURL;
    }

    /*public String determineCourse(String dishType){
        String courseName = "";

        if(dishType.equals(0)) {
            courseName = courseNames[0];
        }
        else if(dishType.equals(1)) {
            courseName = courseNames[1];
        }
        else if(dishType.equals(2)) {
            courseName = courseNames[2];
        }
        else {
            courseName = "None";
        }

        return courseName;
    }
    */

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

    //toString method?
}
