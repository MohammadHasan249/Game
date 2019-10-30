package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    SharedPreferences sharedPreferences;
//    String username;
    TextView textViewUsername;

    CurrUser user;


    public void btnLogoutFunc(View view){
        sharedPreferences.edit().putString("loggedInUsername", "NA").apply();
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        user = new CurrUser(this);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewUsername.setText(user.getUsername());
        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

    }
}
