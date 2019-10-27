package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class FlipCardInit extends AppCompatActivity {
    // easy is level 1, hard is level 2 and insane is level 3
    public void startGame(View view) {
        String difficulty;
        RadioButton easy = findViewById(R.id.rdEasy);
        RadioButton hard = findViewById(R.id.rdHard);
        if (easy.isChecked())
            difficulty = "easy";
        else if (hard.isChecked())
            difficulty = "hard";
        else
            difficulty = "insane";
        Intent startGame = new Intent(this, FlipCardMain.class);
        startGame.putExtra("Level", difficulty);
        startActivity(startGame);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card_init);
    }

}
