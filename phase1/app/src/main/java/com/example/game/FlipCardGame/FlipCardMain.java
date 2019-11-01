package com.example.game.FlipCardGame;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.game.CurrUser;
import com.example.game.R;


public class FlipCardMain extends AppCompatActivity {
    CurrUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card);

        user = new CurrUser(this);
        user.setCurrLevel(3);
        user.playMusic();
            TextView flipCardScore = findViewById(R.id.flipCardScore);
            TableLayout stk = findViewById(R.id.tableLayoutFlipCard);
            Chronometer timer = findViewById(R.id.flipCardTimer);
        FlipCardGameManager newGame = new FlipCardGameManager(user.getDifficultySelected(), user.getColorSelected(),
                    flipCardScore, this.getApplicationContext(), stk, this, timer);

    }

    void endGame(FlipCardResult newResult) {
        newResult.setFlipCardResult(user);
        Intent showResult = new Intent(this, FlipCardResultViewHandler.class);
        showResult.putExtra("FlipCardResult", newResult);
        user.stopMusic();
        finish();
        startActivity(showResult);
    }

    @Override
    public void onBackPressed() {
        user.stopMusic();
        finish();
    }
}

