package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonClickResult extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_result);
        // PLS CHANGE THIS TO A BUTTON
    }


    public void tol2Game(View view) {
        Intent l2Game = new Intent(this, MathGame.class);
        startActivity(l2Game);
        finish();
    }
}
