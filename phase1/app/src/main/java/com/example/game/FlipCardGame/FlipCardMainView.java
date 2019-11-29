package com.example.game.FlipCardGame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flip_card);
    this.flipCardScore = findViewById(R.id.flipCardScore);
    this.tableLayout = findViewById(R.id.tableLayoutFlipCard);
    this.timer = findViewById(R.id.flipCardTimer);
    currUser = new UserInfoFacade(this);
    this.currUser.setLevel(3);
    this.currUser.startMusic();
    this.startPresenter();
  }

  private void startPresenter() {
    Bundle receiver = getIntent().getExtras();
    if (receiver != null) {
      FlipCardMainPresenter presenter = (FlipCardMainPresenter) receiver.get("presenter");
      if (presenter != null) {
        presenter.setView(this);
        presenter.startDisplay();
      }
    }
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
    // ADDD THE PASS THRU HERE AND PASS THE OTHER PRESENTER BACK AND CALL THE displaythere
    newResult.setFlipCardResult(this.currUser.getUser());
    Intent showResult = new Intent(this, FlipCardResultView.class);
    showResult.putExtra("FlipCardResult", newResult);
    this.currUser.stopMusic();
    //since we finished the flipcard game, we are at level 0
    this.currUser.setLevel(0);
    startActivity(showResult);
    finish();
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
}