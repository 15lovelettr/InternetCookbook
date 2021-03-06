package finalproject.rlovelett.internetcookbook;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTitle, textView1, textView2;
    private EditText editTextEmail, editTextPass;
    private Button loginBttn, createUserBttn;

    private FirebaseAuth mAuth; //Used for Firebase user authentication. Allows user into app.
    private FirebaseAuth.AuthStateListener mAuthListener; //Helps with Firebase user authentication.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPass = (EditText) findViewById(R.id.editTextPass);
        loginBttn = (Button) findViewById(R.id.buttonLogin);
        createUserBttn = (Button) findViewById(R.id.buttonCreateUser);

        //Initialize FirebaseAuth instance and the AuthStateListener to track whenever a user signs in and out
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) { //If a user is signed in, sign them out and write a message of it in the app logs.
                    // User is signed in
                    Log.d("IntCook-MainActivity", "onAuthStateChanged:signed_in:" + user.getUid());
                    signOut();
                    Log.d("IntCook-MainActivity", "onAuthStateChanged:signing_user_out:" + user.getUid());
                } else { //Otherwise, if user is signed out, do nothing.
                    // User is signed out
                    Log.d("IntCook-MainActivity", "onAuthStateChanged:signed_out");
                }
            }
        };

        loginBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //A Listener for the Login Button, allows a user to "Login" into Firebase
                signIn(editTextEmail.getText().toString(), editTextPass.getText().toString());
            }
        });

        createUserBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //A listener for the Create User Button, creates a new user account in Firebase
                createAccount(editTextEmail.getText().toString(), editTextPass.getText().toString());
            }
        });
    }

    /*
    * onStart() - When the activity starts, start the FirebaseAuth listener to listen for logins
    **/
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    /*
    * onStop() - When an the activity stops, remove the FirebaseAuth listener from the activity
    **/
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    /*
    * createAccount() - takes in an email address and password, validates them, and creates a new user
    * in the Firebase database.
    **/
    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("IntCook-MainActivity", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {//If account creation fails, notify the user to try again.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {//If account creation succeeds, start a new intent to let user to use app.
                            Log.d("IntCook-MainActivity", "signInWithEmail:onComplete:" + task.isSuccessful());
                            Toast.makeText(MainActivity.this, "Sign-in Successful.",
                                    Toast.LENGTH_SHORT).show(); //popup message for user
                            Intent secActIntent = new Intent(MainActivity.this, Main2ActivityCookbook.class);//start new activity and wait for signout result to return
                            startActivityForResult(secActIntent, 0); //call this method to process the signout request from second activity.
                        }
                    }
                });
    }

    /*
    * signIn() - takes in an email address and password, validates them, and then signs a
    * user into the Firebase database.
    **/
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("IntCook-MainActivity", "signInWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("IntCook-MainActivity", "signInWithEmail", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Log.d("IntCook-MainActivity", "signInWithEmail:onComplete:" + task.isSuccessful());
                            Toast.makeText(MainActivity.this, "Sign-in Successful.",
                                    Toast.LENGTH_SHORT).show();//popup message for user
                            Intent secActIntent = new Intent(MainActivity.this, Main2ActivityCookbook.class); //start new activity and wait for signout result to return
                            startActivityForResult(secActIntent, 0); //call this method to process the signout request from second activity.
                        }
                    }
                });
    }

    /*
    * signOut() - signs a user out of the Firebase database. Login info is saved though.
    **/
    private void signOut () {
        mAuth.signOut();
    }

    /**
     * onActivityResult() - once second activity finishes, and returns an intent, signout the current user.
     * @param requestCode - code that ensures the proper activity has closed
     * @param resultCode - second code from activity itself that ensures the activity closed okay
     * @param data - the Intent returned from the second activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                signOut();
            }
        }
    }//onActivityResult
}

