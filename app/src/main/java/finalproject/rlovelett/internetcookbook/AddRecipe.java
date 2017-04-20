package finalproject.rlovelett.internetcookbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddRecipe extends AppCompatActivity {

    TextView textView1, textView2, textView3, textView4, textView5;
    EditText editName, editTime, editWeb;
    Spinner spinnerDish; //use spinner or checkboxes/radiobuttons?
    Button bttnWeb, bttnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        editName = (EditText) findViewById(R.id.editTextName);
        editTime = (EditText) findViewById(R.id.editTextTime);
        editWeb = (EditText) findViewById(R.id.editTextWebAddress);
        spinnerDish = (Spinner) findViewById(R.id.spinnerDishType);
        bttnAdd = (Button) findViewById(R.id.buttonAdd);
        bttnWeb = (Button) findViewById(R.id.buttonWeb);

        bttnWeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //A listener for the Search Web button, implicitly opens web app to search web
                //implicit intent here, opens up web.
                //but must copy web address into text field in app
            }
        });

        bttnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //A listener for the Add Recipe button, adds recipe to array and returns to previous activity
                //new recipe

                //return to previous activity
                Intent returnIntent = getIntent();
                returnIntent.putExtra("result", 1);
                setResult(Activity.RESULT_OK, returnIntent);
                Toast.makeText(AddRecipe.this, "Adding Recipe...",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }



}
