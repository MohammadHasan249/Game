package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game.FlipCardGame.FlipCardMain;

public class HomePage extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textViewUsername;
    CurrUser user;

    public void btnContinueFunc(View view){
        int lev = user.getCurrLevel();
        if (lev == 0){
            Toast.makeText(getApplicationContext(), "You do not have any un-finished games click NEW GAME to start", Toast.LENGTH_LONG).show();
        }else{
            goCurrentLevel();
        }


    }

    public void btnNewGameFunc(View view){
        Intent start = new Intent(getApplicationContext(), CustomizationScreen.class);
        startActivity(start);
    }

    public void logout(){
        // Separating this into a new function in case want to override Android back button with logout function
        sharedPreferences.edit().putString("loggedInUsername", "NA").apply();
        finish();
    }

    public void btnLogoutFunc(View view){
        logout();
    }

    public void goCurrentLevel(){
        int level = user.getCurrLevel();

        if (level == 1){
            Intent start = new Intent(getApplicationContext(), ButtonClickMain.class);
            startActivity(start);
        }else if (level == 2){
            Intent start = new Intent(getApplicationContext(), MathGame.class);
            startActivity(start);
        }else if (level == 3){
            Intent start = new Intent(getApplicationContext(), FlipCardMain.class);
            startActivity(start);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        user = new CurrUser(this);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewUsername.setText(user.getUsername());
        goCurrentLevel();

    }
}
