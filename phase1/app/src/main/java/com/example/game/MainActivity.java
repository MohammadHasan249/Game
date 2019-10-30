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
    public void goFlipCard(View view) {
        Intent start = new Intent(getApplicationContext(), FlipCardInit.class);
        startActivity(start);
    }

    public void goMathGame(View view) {
        Intent start = new Intent(getApplicationContext(), MathGame.class);
        startActivity(start);
    }

    public void goButtonClick(View view) {
        Intent start = new Intent(getApplicationContext(), ButtonClickStart.class);
        startActivity(start);
    }

/////////////////////////////////////////////////////////////////////////////////////////////

    Button btnSignUp, btnLogin;
    EditText editTextUserName, editTextPassword;
    SQLiteDatabase gameDB;
    String currUserName, currPassword, user;
    int passwordIndex;
    SharedPreferences sharedPreferences;

    public void login(){
        currUserName = editTextUserName.getText().toString();

        sharedPreferences.edit().putString("loggedInUsername", currUserName).apply();
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        startActivity(start);
    }

    public void btnSignUpFunc (View view){
        currUserName = editTextUserName.getText().toString();
        currPassword = editTextPassword.getText().toString();

        if (currUserName == "" || currUserName.length() < 3 ){
            Toast.makeText(getApplicationContext(), "Username must contain at least 3 letters", Toast.LENGTH_SHORT).show();
        }else if (currPassword == "" || currPassword.length() < 3){
            Toast.makeText(getApplicationContext(), "Password must contain at least 3 letters", Toast.LENGTH_SHORT).show();
        }else{
            Cursor c = gameDB.rawQuery("SELECT * FROM users WHERE username = '"+currUserName+"'", null);

            if (!c.moveToFirst()){  // c.moveToFirst() return true if there is data, false if no data
                // Sign up the user if the username typed in is not in database
                gameDB.execSQL("INSERT INTO users (username, password) VALUES ('"+currUserName+"', '"+currPassword+"')");
                // Unspecified column values by default become Int = 0, VARCHAR = null

                login();
            }else{
                Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnLoginFunc(View view){
        currUserName = editTextUserName.getText().toString();
        currPassword = editTextPassword.getText().toString();
        Cursor c = gameDB.rawQuery("SELECT * FROM users WHERE username = '"+currUserName+"'", null);

        if (c.moveToFirst()){
            passwordIndex = c.getColumnIndex("password");
            if(currPassword.equals(c.getString(passwordIndex))){
                //Found existing existing user with username / password matching inputs
                login();
            }else{
                Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Username does not exist", Toast.LENGTH_SHORT).show();
        }
    }

    public void hideKeyboard(View view){
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        gameDB = this.openOrCreateDatabase("gameDB", MODE_PRIVATE, null);

//        gameDB.execSQL("DROP TABLE users");
//        sharedPreferences.edit().putString("loggedInUsername", "NA").apply();

        gameDB.execSQL("CREATE TABLE IF NOT EXISTS users (username VARCHAR, password VARCHAR, currLevel INT(2), " +
                "colorSelected INT(2), difficultySelected VARCHAR, musicSelected VARCHAR, " +
                "l1EasyBestScore INT(4), l1HardBestScore INT(4), l1RecentScore INT(4), " +
                "l2EasyBestScore INT(4), l2HardBestScore INT(4), l2RecentScore INT(4), " +
                "l3EasyBestScore INT(4), l3HardBestScore INT(4), l3RecentScore INT(4))");

        user = sharedPreferences.getString("loggedInUsername", "NA");

        if (!user.equals("NA")){
            Intent start = new Intent(getApplicationContext(), HomePage.class);
            startActivity(start);
        }


    }
}
