package finalproject.rlovelett.internetcookbook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class Main2ActivityCookbook extends AppCompatActivity {

    RecipeList recipeList; //The list of recipe objects
    ArrayAdapter<Recipe> recipeAdapter; //Helps display list of recipes using the recipe_list_row layout

    TextView textView1;
    ListView recipeListView;
    Button signOutBttn, addRCPBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_cookbook);

        recipeList = new RecipeList(); //Class RecipeList that stores the list of recipes we've added

        textView1 = (TextView) findViewById(R.id.textView1);
        recipeListView = (ListView) findViewById(R.id.recipeList);
        signOutBttn = (Button) findViewById(R.id.buttonSignOut);
        addRCPBttn = (Button) findViewById(R.id.buttonAddRecipe);

        //creates new RecipeAdapter object to display our recipe_list_row in the list of this activity.
        recipeAdapter = new RecipeAdapter(this, R.layout.recipe_list_row, R.id.textView1, recipeList);
        recipeAdapter.setDropDownViewResource(R.layout.recipe_list_row);
        recipeListView.setAdapter(recipeAdapter);

        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) { //Open up webpage with the found website link or selected recipe, if there is any
                Recipe rcp = (Recipe) parent.getItemAtPosition(position);
                String selectedRecipeURL = rcp.getWebURL();
                if(selectedRecipeURL != null) { //if there is a website to go to, open the web to go to it.
                    //start second implicit intent for selected recipe
                    Uri webpage = Uri.parse(selectedRecipeURL);
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }
        });

        signOutBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //A listener for the Sign Out button, returns user to starting screen
                Intent returnIntent = getIntent();
                returnIntent.putExtra("result", 0); //returns code to previous activity to ensure the right activity is closing
                setResult(Activity.RESULT_OK, returnIntent); //ensures the activity closes OK/Successfully
                Toast.makeText(Main2ActivityCookbook.this, "Sign-out Successful.",
                        Toast.LENGTH_SHORT).show(); //notify user their signout attempt was successful
                finish(); //close activity
            }
        });

        addRCPBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //A listener for the Add Recipe button, takes user to add recipe activity to add new recipe to array
                Intent thrdActIntent = new Intent(Main2ActivityCookbook.this, AddRecipe.class); //start new activity and wait for info to create new recipe
                startActivityForResult(thrdActIntent, 1); //call this method to process the add recipe request from second activity.
            }
        });
    }

    /**
     * onActivityResult() - once second activity finishes, and returns an intent, add new recipe to recipe-array.
     * @param requestCode - code that ensures the proper activity has closed
     * @param resultCode - second code from activity itself that ensures the activity closed okay
     * @param data - the Intent returned from the second activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                //String result = data.getStringExtra("result");
                String name = data.getStringExtra("newName"); //get recipe name from the returned intent
                String time = data.getStringExtra("newTime"); //get prep time from returned intent
                String dishType = data.getStringExtra("newDish"); //get dish type from returned intent
                String webAddress = data.getStringExtra("newURL"); //get recipe web address from returned intent
                Recipe newRecipe = new Recipe(name, time, dishType, webAddress); //create new recipe with intent information
                recipeAdapter.add(newRecipe); //add recipe to array to be displayed with the adapter
            }
        }
    }
}
