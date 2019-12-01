package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

/////////////////////////////////////////////////////////////////////////////////////////////

    Button btnSignUp, btnLogin;
    EditText editTextUserName, editTextPassword;
    SQLiteDatabase gameDB;
    String currUserName, currPassword, user;
    SharedPreferences sharedPreferences;
    GenerateDataBase generateDataBase;
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get View objects
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Initiate data storing objects
        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        gameDB = this.openOrCreateDatabase("gameDB", MODE_PRIVATE, null);

        // Initiate storage function objects
        loginManager = new LoginManager(this, gameDB, sharedPreferences);
        generateDataBase = new GenerateDataBase(gameDB);

        // Generate SQL tables required for game (if not already created)
        generateDataBase.GenerateTables();

        // Check if user had previously logged in and haven't logged out
        user = sharedPreferences.getString("loggedInUsername", "NA");
        if (!user.equals("NA")) {
            Intent start = new Intent(getApplicationContext(), HomePage.class);
            startActivity(start);
        }
    }


    public void btnSignUpFunc(View view) {
        currUserName = editTextUserName.getText().toString();
        currPassword = editTextPassword.getText().toString();

        if (currUserName.isEmpty() || currUserName.length() < 3) {
            Toast.makeText(getApplicationContext(), "Username must contain at least 3 letters", Toast.LENGTH_SHORT).show();
        } else if (currPassword.isEmpty() || currPassword.length() < 3) {
            Toast.makeText(getApplicationContext(), "Password must contain at least 3 letters", Toast.LENGTH_SHORT).show();
        } else {
            if(loginManager.attemptSignUp(currUserName, currPassword)){
                goHomePage();
            }else{
                Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnLoginFunc(View view) {
        currUserName = editTextUserName.getText().toString();
        currPassword = editTextPassword.getText().toString();
        if (loginManager.attemptLogin(currUserName, currPassword)){
            goHomePage();
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mgr != null)
            mgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void goHomePage(){
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        startActivity(start);
    }
}
