package finalproject.rlovelett.internetcookbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddRecipe extends AppCompatActivity {

    TextView textView1, textView2, textView3, textView4, textView5;
    EditText editName, editTime, editWeb;
    Spinner spinnerDish;
    Button bttnWeb, bttnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }



}
