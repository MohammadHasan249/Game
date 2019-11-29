package com.example.game.FlipCardGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.EndGameResultPage;
import com.example.game.R;
import com.example.game.UserInfoFacade;

public class FlipCardResultView extends AppCompatActivity {
  UserInfoFacade user;
  private FlipCardReplay replay;
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
    this.user.setLevel(0);
    Bundle receiver = getIntent().getExtras();
    if (receiver != null) {
      this.result = (FlipCardResult) receiver.get("FlipCardResult");
      this.setResult(this.result);
      this.replay = (FlipCardReplay) receiver.get("replay");
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
  public void startInstantReplay(View view) {
    Intent mainGameIntent = new Intent(this, FlipCardMainView.class);
    mainGameIntent.putExtra("presenter", new FlipCardReplayPresenter());
    mainGameIntent.putExtra("results", this.result);
    mainGameIntent.putExtra("replay", this.replay);
    startActivity(mainGameIntent);
    finish();
  }
}
