package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonClickResult extends AppCompatActivity {

    int numberClicks;
    int correctScore;
    CurrUser user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_result);
        user = new CurrUser(this);
        TextView numClicksText = findViewById(R.id.numClicksScore);
        TextView numCorrectClicksText = findViewById(R.id.numCorrectClicks);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            numberClicks = (int) bundle.get("Total Clicks");
            correctScore = (int) bundle.get("Score");
        }
        numClicksText.setText(String.valueOf(numberClicks));
        numCorrectClicksText.setText(String.valueOf(correctScore));
        user.setL1RecentScore(correctScore);
        user.updateL1BestScore();
        user.setCurrLevel(2);
    }

    public void tol2Game(View view) {
        Intent l2Game = new Intent(this, MathGame.class);
        startActivity(l2Game);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        start.putExtra("androidBack", 1);
        startActivity(start);
        finish();
    }
}
