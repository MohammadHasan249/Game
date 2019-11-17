package com.example.game.FlipCardGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.game.CurrUser;
import com.example.game.HomePage;
import com.example.game.LevelOnCreate;
import com.example.game.R;

public class FlipCardMain extends AppCompatActivity {
  CurrUser user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flip_card);
    LevelOnCreate level3 =
        new LevelOnCreate(this, "Match The Cards! (Timer goes off when you click on one of them)");
    user = new CurrUser(this);
    user.setCurrLevel(3);
    user.playMusic();
    TextView flipCardScore = findViewById(R.id.flipCardScore);
    TableLayout stk = findViewById(R.id.tableLayoutFlipCard);
    Chronometer timer = findViewById(R.id.flipCardTimer);
    FlipCardMainGame newGame =
        new FlipCardMainGame(
            user.getDifficultySelected(),
            user.getColorSelected(),
            flipCardScore,
            this.getApplicationContext(),
            stk,
            this,
            timer);
  }

  void endGame(FlipCardResult newResult) {
    newResult.setFlipCardResult(user);
    Intent showResult = new Intent(this, FlipCardResultViewHandler.class);
    showResult.putExtra("FlipCardResult", newResult);
    user.stopMusic();
    finish();
    startActivity(showResult);
  }

  @Override
  public void onBackPressed() {
    user.stopMusic();
    Intent start = new Intent(getApplicationContext(), HomePage.class);
    start.putExtra("androidBack", 1);
    startActivity(start);
    finish();
  }
}
