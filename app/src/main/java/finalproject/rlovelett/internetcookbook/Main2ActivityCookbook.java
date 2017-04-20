package finalproject.rlovelett.internetcookbook;

import android.app.Activity;
import android.content.Intent;
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
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //Open up webpage with the found website link?
                //start second implicit intent?

            }
        });

        signOutBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //A listner for the Sign Out button, returns user to starting screen
                Intent returnIntent = getIntent();
                returnIntent.putExtra("result", 0);
                setResult(Activity.RESULT_OK, returnIntent);
                Toast.makeText(Main2ActivityCookbook.this, "Sign-out Successful.",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        addRCPBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //A listner for the Add Recipe button, takes user to add recipe activity to add new recipe to array
                Intent thrdActIntent = new Intent(Main2ActivityCookbook.this, AddRecipe.class);
                startActivityForResult(thrdActIntent, 1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                //add whatever code to update recipe list list here?

            }
        }
    }//onActivityResult

}
