package com.example.game.FlipCardGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.game.UserInfoFacade;
import com.example.game.HomePage;
import com.example.game.LevelOnCreate;
import com.example.game.R;

public class FlipCardMainView extends AppCompatActivity implements  FlipCardMainPresenter.View{
  private UserInfoFacade currUser;
  private FlipCardMainPresenter presenter;
  private TextView flipCardScore;
  private Chronometer timer;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flip_card);
    this.flipCardScore = findViewById(R.id.flipCardScore);
    TableLayout tableLayout = findViewById(R.id.tableLayoutFlipCard);
    this.timer = findViewById(R.id.flipCardTimer);
    this.displayPopInstructions();
    currUser = new UserInfoFacade(this);
    this.currUser.setLevel(3);
    this.currUser.startMusic();
    this.presenter = new FlipCardMainPresenter(this.getApplicationContext(),this);
    this.presenter.startGame(this.currUser.getSelectedDifficulty(),this.getSelectedSymbol(),this.currUser.getSelectedColor(),tableLayout);
  }

  void displayPopInstructions()
  {
    LevelOnCreate level3 =
            new LevelOnCreate(this, "Match The Cards! (Timer goes off when you click on one of them)");
  }
  String getSelectedSymbol()
  {
    return getIntent().getStringExtra("symbolChoice");
  }
  @Override
  public void gameEnded(FlipCardResult newResult) {
    newResult.setFlipCardResult(this.currUser.getUser());
    Intent showResult = new Intent(this, FlipCardResultViewHandler.class);
    showResult.putExtra("FlipCardResult", newResult);
    this.currUser.stopMusic();
    startActivity(showResult);
    this.currUser.setLevel(0);
    finish();
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
}
