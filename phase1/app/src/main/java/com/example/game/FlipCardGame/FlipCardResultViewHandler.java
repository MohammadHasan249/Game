package com.example.game.FlipCardGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.CurrUser;
import com.example.game.EndGameResultPage;
import com.example.game.R;

public class FlipCardResultViewHandler extends AppCompatActivity {
  CurrUser user;

  public void endGame(View view) {
    user.setCurrLevel(0);
    Intent endGameResult = new Intent(this, EndGameResultPage.class);
    startActivity(endGameResult);
    finish();
  }

  private void displayScores(
      FlipCardResult newResult,
      TextView difficulty,
      TextView timeToCompletion,
      TextView numCorrect,
      TextView numIncorrect) {
    difficulty.setText(newResult.getStrDifficulty());
    timeToCompletion.setText(newResult.getStrTimeToCompletion());
    numCorrect.setText(newResult.getStrNumCorrect());
    numIncorrect.setText(newResult.getStrNumIncorrect());
  }

  private void setScore(FlipCardResult newResult, CurrUser user) {
    user.setL3RecentScore(newResult.getTimeToCompletion());
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
      // put user back to level 0 since they are done all their games
      user = new CurrUser(this);
      this.setScore(newResult, user);
    }
  }
}
