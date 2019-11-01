package com.example.game;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonClickResult extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_result);
        // PLS CHANGE THIS TO A BUTTON
        this.tol2Game();
    }


    //PLEASE BIND THIS METHOD TO A BUTTON SO IT GOES TO THE NEXT GAME
    private void tol2Game() {
        Intent l2Game = new Intent(this, MathGame.class);
        startActivity(l2Game);
        finish();
    }
}
