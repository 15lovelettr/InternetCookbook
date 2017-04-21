package finalproject.rlovelett.internetcookbook;

import java.util.ArrayList;
import java.util.List;

/**
 * Class RecipeList - Holds and manages an arraylist of Recipes.
 *
 * Created by rlovelett on 4/19/2017.
 */

public class RecipeList {
    //The arraylist of Recipe Objects we are tracking
    private ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();

    /**
     * Constructor that initializes the array list
     */
    public RecipeList() {
        recipeArrayList = new ArrayList<Recipe>();
    }

    /**
     * Provides direct access to the entire arraylist of recipes
     * @return a List of Recipe objects
     */
    public List getList() {
        return recipeArrayList;
    }

    /**
     * Procides access to recipe object
     * @param index The array index of the heart rate object in the array list
     * @return The Recipe object corresponding to the array index given
     */
    public Recipe getRecipe(Integer index) {
        return recipeArrayList.get(index);
    }

    /**
     * Removes a specified Recipe Object from the list of recipes.
     * @param index - the position of the recipe in the ArrayList we wish to remove.
     */
    public void remove(Integer index){
        recipeArrayList.remove(index);
    }

}
