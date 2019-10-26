package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public void goFlipCard(View view) {
        Intent start = new Intent(getApplicationContext(), FlipCardInit.class);
        startActivity(start);
    }

    public void goMathGame(View view) {
        Intent start = new Intent(getApplicationContext(), MathGame.class);
        startActivity(start);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // That intent line that creates a Intent object
        // is a way to transition to another Java + XML file, so as input i put MathGame.class so it transitions to the MathGame.java activity file
        // So as soon as this app launches, the main activity's onCreate method will be called (aka where this code is located)
        // then the intent line will transition to the MathGame.java
    }
}
