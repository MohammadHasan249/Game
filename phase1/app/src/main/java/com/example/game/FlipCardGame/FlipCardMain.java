package com.example.game.FlipCardGame;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.game.R;

public class FlipCardMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card);
        Bundle receiver = getIntent().getExtras();
        if (receiver != null)
        {
            String difficulty = receiver.getString("Level");
            TextView flipCardScore = findViewById(R.id.flipCardScore);
            TableLayout stk = findViewById(R.id.tableLayoutFlipCard);
            Chronometer timer = findViewById(R.id.flipCardTimer);
            FlipCardGameManager newGame = new FlipCardGameManager(difficulty, Color.RED,
                    flipCardScore, this.getApplicationContext(), stk, this, timer);
        }
    }

    void endGame(FlipCardResult newResult) {
        Intent showResult = new Intent(this, FlipCardResultViewHandler.class);
        showResult.putExtra("FlipCardResult", newResult);
        finish();
        startActivity(showResult);
    }
    }

