package com.example.game.FlipCardGame.FlipCardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.EndGameResultPage;
import com.example.game.FlipCardGame.FlipCardResult.FlipCardResult;
import com.example.game.R;
import com.example.game.UserInfoFacade;

public class FlipCardResultView extends AppCompatActivity {
  UserInfoFacade user;
  private FlipCardResult result;
  public void endGame(View view) {
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


  // receives an instance of the FlipCardResult Class then display the scores within that object
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flip_card_result);
    this.user = new UserInfoFacade(this);
    Bundle receiver = getIntent().getExtras();
    if (receiver != null) {
      this.result = (FlipCardResult) receiver.get("FlipCardResult");
      this.setResult(this.result);
    }
  }

  private void setResult(FlipCardResult result) {
    TextView difficulty = findViewById(R.id.DifficultyTextView);
    TextView timeToCompletion = findViewById(R.id.TimetoCompletionTextView);
    TextView numCorrect = findViewById(R.id.NumCorrectMatchesTextView);
    TextView numIncorrect = findViewById(R.id.NumIncorrectTextView);
    this.displayScores(result, difficulty, timeToCompletion, numCorrect, numIncorrect);
    this.user.setFlipCardScore(result.getTimeToCompletion());
  }
}
