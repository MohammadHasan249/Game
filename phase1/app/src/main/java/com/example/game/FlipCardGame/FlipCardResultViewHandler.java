package com.example.game.FlipCardGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.R;

public class FlipCardResultViewHandler extends AppCompatActivity {

    public void mainBack(View view) {
        Intent mainActivityIntent = new Intent(this, FlipCardInit.class);
        startActivity(mainActivityIntent);
    }

    private void displayScores(FlipCardResult newResult, TextView difficulty,
                               TextView timeToCompletion, TextView numCorrect, TextView numIncorrect) {
        difficulty.setText(newResult.getStrDifficulty());
        timeToCompletion.setText(newResult.getStrTimeToCompletion());
        numCorrect.setText(newResult.getStrNumCorrect());
        numIncorrect.setText(newResult.getStrNumIncorrect());
    }

    // recieves an instance of the FlipCardResult Class then display the scores within that object
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card_result);
        Bundle receiver = getIntent().getExtras();
        if (receiver != null) {
            FlipCardResult newResult = (FlipCardResult) receiver.get("FlipCardResult");
            TextView difficulty = findViewById(R.id.DifficultyTextView);
            TextView timeToCompletion = findViewById(R.id.TimetoCompletionTextView);
            TextView numCorrect = findViewById(R.id.NumCorrectMatchesTextView);
            TextView numIncorrect = findViewById(R.id.NumIncorrectTextView);
            this.displayScores(newResult, difficulty, timeToCompletion, numCorrect, numIncorrect);
        }
    }

}
