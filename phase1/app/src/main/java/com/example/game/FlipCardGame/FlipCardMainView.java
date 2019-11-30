package com.example.game.FlipCardGame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.HomePage;
import com.example.game.LevelOnCreate;
import com.example.game.R;
import com.example.game.UserInfoFacade;

public class FlipCardMainView extends AppCompatActivity implements FlipCardGameView {
  private UserInfoFacade currUser;
  private TextView flipCardScore;
  private Chronometer timer;
  private TableLayout tableLayout;
  private Button btnInstantreplay;
  private Button btnFlipCardResult;
  private FlipCardResult result;
  private FlipCardGamePresenter presenter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flip_card);
    this.flipCardScore = findViewById(R.id.flipCardScore);
    this.tableLayout = findViewById(R.id.tableLayoutFlipCard);
    this.btnFlipCardResult = findViewById(R.id.btnFlipCardResult);
    this.btnInstantreplay = findViewById(R.id.btnInstantReplay);
    this.btnInstantreplay.setVisibility(View.INVISIBLE);
    this.btnFlipCardResult.setVisibility(View.INVISIBLE);
    this.timer = findViewById(R.id.flipCardTimer);
    currUser = new UserInfoFacade(this);
    this.currUser.setLevel(3);
    this.currUser.startMusic();
    this.presenter = new FlipCardGamePresenter(this);
    this.presenter.startDisplay();
  }

  @Override
  public void displayInstructions(String instructions)
  {
    LevelOnCreate level3 =
            new LevelOnCreate(this, instructions);
  }

  @Override
  public void onBackPressed() {
    this.currUser.stopMusic();
    Intent start = new Intent(getApplicationContext(), HomePage.class);
    start.putExtra("androidBack", 1);
    startActivity(start);
    finish();
  }

  @Override
  public String getSymbolChoice() {
    return getIntent().getStringExtra("symbolChoice");
  }

  @Override
  public int getColor() {
    return this.currUser.getSelectedColor();
  }

  @Override
  public String getDifficulty() {
    return this.currUser.getSelectedDifficulty();
  }

  @Override
  public TableLayout getTableLayout() {
    return this.tableLayout;
  }

  @Override
  public void gameEnded(FlipCardResult newResult) {
    this.btnInstantreplay.setVisibility(View.VISIBLE);
    this.btnFlipCardResult.setVisibility(View.VISIBLE);
    this.result = newResult;

  }
  @Override
  public void startTime()
  {
    this.timer.setBase(SystemClock.elapsedRealtime());
    this.timer.start();
  }
  @Override
  public void stopTime()
  {
    this.timer.stop();
  }
  @Override
  public long timeElapsed()
  {
    return SystemClock.elapsedRealtime() - this.timer.getBase();
  }
  @Override
  public void updateScore(String toShow)
  {
    this.flipCardScore.setText(toShow);
  }

  @Override
  public Context getContext() {
    return this.getApplicationContext();
  }

  @Override
  public FlipCardResult getResults() {
    Bundle receiver = getIntent().getExtras();
    if (receiver != null) {
      return (FlipCardResult) receiver.get("flipCardResults");
    }
    return null;
  }

  @Override
  public FlipCardMainGameModel getCurrGame() {
    return this.presenter.getCurrGame();
  }

  public void btnFlipCardResult(View view) {
    Intent showResult = new Intent(this, FlipCardResultView.class);
    showResult.putExtra("FlipCardResult", this.result);
    this.updateUserScore();
    startActivity(showResult);
    finish();
  }

  private void updateUserScore() {
    this.currUser.stopMusic();
    this.currUser.setLevel(0);
    this.currUser.updateFlipCardScore(this.result.getTimeToCompletion());
  }

  public void btnInstantReplay(View view) {
    this.btnInstantreplay.setVisibility(View.INVISIBLE);
    FlipCardMainGameModel currModel = this.presenter.getCurrGame();

  }
}