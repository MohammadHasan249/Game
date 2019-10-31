package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EndGameResultPage extends AppCompatActivity {
    CurrUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_result_page);
        TextView diffcultyTextView = findViewById(R.id.EndGameDifficultyTextView);
        TextView l1TextView = findViewById(R.id.Level1ScoreTextView);
        TextView l2TextView = findViewById(R.id.Level2ScoreTextView);
        TextView l3TextView = findViewById(R.id.Level3ScoreTextView);
        this.setScore(user, diffcultyTextView, l1TextView, l2TextView, l3TextView);
        user = new CurrUser(this);
        user.setCurrLevel(0);
    }

    private void setScore(CurrUser user, TextView diffcultyTextView, TextView l1TextView, TextView l2TextView, TextView l3TextView) {
        diffcultyTextView.setText(user.getDifficultySelected());
        String l1score = Integer.toString(user.getL1RecentScore());
        String l2score = Integer.toString(user.getL2RecentScore());
        String l3score = Integer.toString(user.getL3RecentScore());
        l1TextView.setText(l1score);
        l2TextView.setText(l2score);
        l3TextView.setText(l3score);
    }
}