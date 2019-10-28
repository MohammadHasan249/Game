package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textViewUsername;
    String username;


    public void btnLogoutFunc(View view){
        sharedPreferences.edit().putString("loggedInUsername", "NA").apply();
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        username = sharedPreferences.getString("loggedInUsername", "NAA");
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewUsername.setText(username);

    }
}
