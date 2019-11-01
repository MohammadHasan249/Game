package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textViewUsername;
    CurrUser user;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        user = new CurrUser(this);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewUsername.setText(user.getUsername());
        System.out.println("HOME MARKER");
    }
}
