package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndGameResultPage extends AppCompatActivity {
    CurrUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_result_page);
        user = new CurrUser(this);
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
        String l1score = Integer.toString(user.getL1RecentScore()) + " number of clicks";
        String l2score = Integer.toString(user.getL2RecentScore()) + " number correct";
        String l3score = Integer.toString(user.getL3RecentScore()) + " seconds";
        l1TextView.setText(l1score);
        l2TextView.setText(l2score);
        l3TextView.setText(l3score);
    }

    public void backToHome(){
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        start.putExtra("androidBack",1);
        startActivity(start);
        finish();
    }

    public void btnFinishFunc(View view){
        backToHome();
    }

    @Override
    public void onBackPressed() {
        backToHome();
    }
}
