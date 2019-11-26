package com.example.game.FlipCardGame;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

class FlipCardMainGame implements FlipCardManager{
  private String difficulty;
  private int numMatches;
  private int numCorrect;
  private int numMatchAttempt;
  private int cardBackColor;
  private TextView flipCardScore;
  private Context packageContext;
  private TableLayout stk;
  private ArrayList<FlipCards> allCards;
  private FlipCardMainView observer;
  private boolean firstClick;
  private Chronometer timer;
    private FlipCardsBuilder flipCardsBuilder;
  FlipCardMainGame(
      String difficulty,
      int colorInt,
      TextView flipCardScore,
      Context packageContext,
      TableLayout stk,
      FlipCardMainView observer,
      Chronometer timer) {
    this.flipCardGameManagerBuilder(difficulty,colorInt,flipCardScore,packageContext,stk,observer,timer);
  }

  private void flipCardGameManagerBuilder(
          String difficulty,
          int colorInt,
          TextView flipCardScore,
          Context packageContext,
          TableLayout stk,
          FlipCardMainView observer,
          Chronometer timer)
  {
    this.difficulty = difficulty;
    this.setNumMatches(this.difficulty);
    this.cardBackColor = colorInt;
    this.flipCardScore = flipCardScore;
    this.packageContext = packageContext;
    this.stk = stk;
    this.numCorrect = 0;
    this.numMatchAttempt = 0;
    this.observer = observer;
    this.firstClick = false;
    this.timer = timer;
      this.flipCardsBuilder = new FlipCardsBuilder(this.numMatches, "Ascii",
              this.packageContext, this.stk, this, this.cardBackColor);
      this.allCards = this.flipCardsBuilder.createCards();
  }

  // this is an update class that is called when the cards are flipped
  // if this is the first click, it will start the timer then call updatecards
  // if the game is over when the player got all the matches then stop the timer and pass back
  // the flip card result class to the observer(FlipCardMainView) to end the intent there
//  @Override
//  public void update() {
//    if (!this.firstClick) {
//      this.timer.setBase(SystemClock.elapsedRealtime());
//      this.timer.start();
//      this.firstClick = true;
//    }
//    this.updateCards();
//    if (this.numCorrect == this.numMatches) {
//      this.timer.stop();
//      long timeToCompleteMs = this.returnElapsedTime();
//      FlipCardResult newResult =
//          new FlipCardResult(
//              this.difficulty, this.numCorrect, this.numMatchAttempt, timeToCompleteMs);
//      this.observer.endGame(newResult);
//    }
//  }
  @Override
  public void update(FlipCards currflip) {
    if (!this.firstClick) {
      this.timer.setBase(SystemClock.elapsedRealtime());
      this.timer.start();
      this.firstClick = true;
    }
    this.updateCards();
    if (this.numCorrect == this.numMatches) {
      this.timer.stop();
      long timeToCompleteMs = this.returnElapsedTime();
      FlipCardResult newResult =
              new FlipCardResult(
                      this.difficulty, this.numCorrect, this.numMatchAttempt, timeToCompleteMs);
      this.observer.endGame(newResult);
    }
  }

  // calculating the time elapsed
  private long returnElapsedTime() {
    return SystemClock.elapsedRealtime() - this.timer.getBase();
  }

  // goes over the list of all cards, if 2 of them are flipped, check if they are a match
  // if they are then update cards and lock them
  // if they aren't then we put a delay then flip them back
  private void updateCards() {
    ArrayList<FlipCards> flipped = new ArrayList<>();
    for (FlipCards f : this.allCards) {
      if (f.isFlipped()) {
        flipped.add(f);
      }
      if (flipped.size() == 2) {
        FlipCards flippedCard1 = flipped.get(0);
        FlipCards flippedCard2 = flipped.get(1);
        if (flippedCard1.getSymbol().equals(flippedCard2.getSymbol())) {
          flippedCard1.lockCard();
          flippedCard2.lockCard();
          this.numCorrect++;
          this.numMatchAttempt++;
          this.updateScore();
        } else {
          FlipCards.disableCards = true;
          this.nonMatchCardDelay(flippedCard1, flippedCard2);
          this.numMatchAttempt++;
          this.updateScore();
        }
        break;
      }
    }
  }

  // putting delay then flipping the 2 in correctly matched cards back
  private void nonMatchCardDelay(final FlipCards f1, final FlipCards f2) {
    new CountDownTimer(450, 450) {
      public void onTick(long millisUntilFinished) {}

      public void onFinish() {
        f1.flipCard();
        f2.flipCard();
        FlipCards.disableCards = false;
      }
    }.start();
  }

  // update score board
  private void updateScore() {
    String toShow = (this.numCorrect) + " | " + (this.numMatchAttempt);
    this.flipCardScore.setText(toShow);
  }

  // set numMatches based on the difficulty of the game selected buy the user
  private void setNumMatches(String difficulty) {
    if (difficulty.equals("easy")) this.numMatches = 8;
    else this.numMatches = 16;
  }

}
