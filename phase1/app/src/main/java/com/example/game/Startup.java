package com.example.game;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


public class Startup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_startup);
        //Wait for 3 seconds and start Activity Main
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Startup.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        },1500);
    }


    }

