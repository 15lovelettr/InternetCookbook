package finalproject.rlovelett.internetcookbook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by rlovelett on 4/19/2017.
 */

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private Context context;
    private RecipeList recipeList;

    public RecipeAdapter(Context context, int resource, int textViewResourceId, RecipeList recipeList) {
        super(context, resource, textViewResourceId, recipeList.getList());

        this.context = context;
        this.recipeList = recipeList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recipe_list_row, null);
        Recipe rcp = recipeList.getRecipe(position);

        TextView recipeName = (TextView) view.findViewById(R.id.textViewName);
        recipeName.setText("Name: " + rcp.getName());

        TextView recipeTime = (TextView) view.findViewById(R.id.textViewTime);
        recipeTime.setText("Cook Time: " + rcp.getCookTime());

        TextView recipeType = (TextView) view.findViewById(R.id.textViewType);
        recipeType.setText("Dish Type: " + rcp.getDishType());

        return view;
    }
}
