package com.example.game.FlipCardGame;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.Gravity;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
  private FlipCardMain observer;
  private boolean firstClick;
  private Chronometer timer;

  FlipCardMainGame(
      String difficulty,
      int colorInt,
      TextView flipCardScore,
      Context packageContext,
      TableLayout stk,
      FlipCardMain observer,
      Chronometer timer) {
    this.flipCardGameManagerBuilder(difficulty,colorInt,flipCardScore,packageContext,stk,observer,timer);
  }

  private void flipCardGameManagerBuilder(
          String difficulty,
          int colorInt,
          TextView flipCardScore,
          Context packageContext,
          TableLayout stk,
          FlipCardMain observer,
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
    this.allCards = this.generateCards(this.numMatches);
    this.observer = observer;
    this.firstClick = false;
    this.timer = timer;
  }

  // this is an update class that is called when the cards are flipped
  // if this is the first click, it will start the timer then call updatecards
  // if the game is over when the player got all the matches then stop the timer and pass back
  // the flip card result class to the observer(FlipCardMain) to end the intent there
  @Override
  public void update() {
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

  // generates a random list of symbols with size numMatches times 2, so every match will have
  // 2 cards with the same symbol
  private ArrayList<Character> symbolGenerator(int numMatches) {
    Random rand = new Random();
    ArrayList<Character> charList = new ArrayList<>();
    for (int i = 0; i < numMatches; i++) {
      // want to generate a random character to use, since my max number of matches is 20
      // I have no problem going through the entire alphabet
      char c = (char) (rand.nextInt(26) + 'a');
      if (charList.contains(c)) {
        for (int j = 0; j < numMatches; j++) {
          char newchar = (char) (rand.nextInt(26) + 'a');
          if (!charList.contains(newchar)) {
            charList.add(newchar);
            charList.add(newchar);
            break;
          }
        }
      } else {
        charList.add(c);
        charList.add(c);
      }
    }
    Collections.shuffle(charList);
    return charList;
  }

  // set numMatches based on the difficulty of the game selected buy the user
  private void setNumMatches(String difficulty) {
    if (difficulty.equals("easy")) this.numMatches = 8;
    else this.numMatches = 16;
  }

  // generates a new table row in our table layout
  private TableRow generateNewRow() {
    TableRow tbrow = new TableRow(this.packageContext);
    tbrow.setGravity(Gravity.CENTER_HORIZONTAL);
    tbrow.setLayoutParams(
        new TableLayout.LayoutParams(
            TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
    return tbrow;
  }

  // generates cards of the game and adds them to a list to keep track of them.
  private ArrayList<FlipCards> generateCards(int numMatches) {
    ArrayList<Character> charList = this.symbolGenerator(numMatches);
    ArrayList<FlipCards> allCardCreation = new ArrayList<>();
    this.stk.setGravity(Gravity.CENTER_VERTICAL);
    int i = 0;
    TableRow tbrow = this.generateNewRow();
    for (Character s : charList) {
      if (i == 4) {
        this.stk.addView(tbrow);
        tbrow = this.generateNewRow();
        i = 0;
      }
      FlipCards f1 =
          new FlipCards(this.packageContext, cardBackColor, s.toString(), tbrow, 3, 3, this);
      allCardCreation.add(f1);
      i++;
    }
    stk.addView(tbrow);
    return allCardCreation;
  }
}
