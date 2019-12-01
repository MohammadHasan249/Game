package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginManager{

    SharedPreferences sharedPreferences;
    SQLiteDatabase gameDB;
    Context context;

    LoginManager(Context context, SQLiteDatabase gameDB, SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
        this.gameDB = gameDB;
        this.context = context;
    }

    public void notifyLoginSuccess(String username){
        sharedPreferences.edit().putString("loggedInUsername", username).apply();
    }

    public boolean attemptLogin (String username, String password){
        Cursor c = gameDB.rawQuery("SELECT * FROM users WHERE username = '" + username + "'", null);
        int passwordIndex;

        if (c.moveToFirst()) {
            passwordIndex = c.getColumnIndex("password");
            if (password.equals(c.getString(passwordIndex))) {
                //Found existing existing user with username / password matching inputs
                c.close();
                notifyLoginSuccess(username);
                return true;
            } else {
                Toast.makeText(this.context, "Incorrect Password", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this.context, "Username does not exist", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean attemptSignUp(String username, String password){

        Cursor c = gameDB.rawQuery("SELECT * FROM users WHERE username = '" + username + "'", null);

        if (!c.moveToFirst()) {  // c.moveToFirst() return true if there is data, false if no data
            // Sign up the user if the username typed in is not in database
            gameDB.execSQL("INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')");
            // Unspecified column values by default become Int = 0, VARCHAR = null
            c.close();
            notifyLoginSuccess(username);
            return true;
        }
        return false;
    }
}
