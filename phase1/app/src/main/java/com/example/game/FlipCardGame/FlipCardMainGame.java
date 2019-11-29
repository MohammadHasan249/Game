package com.example.game.FlipCardGame;

import android.os.CountDownTimer;

import java.util.ArrayList;

class FlipCardMainGame implements FlipCardGame {
  private String difficulty;
  private int numMatches;
  private int numCorrect;
  private int numMatchAttempt;
  private ArrayList<FlipCards> allCards;
  private FlipCardGamePresenter presenter;
  private boolean firstClick;
  FlipCardMainGame(
          String difficulty,
          FlipCardGamePresenter presenter) {
    this.flipCardGameManagerBuilder(difficulty,presenter);
  }

  private void flipCardGameManagerBuilder(
          String difficulty,
          FlipCardGamePresenter presenter)
  {
    this.difficulty = difficulty;
    this.setNumMatches(this.difficulty);
    this.numCorrect = 0;
    this.numMatchAttempt = 0;
    this.presenter = presenter;
    this.firstClick = false;
  }

  int getNumMatches(){return this.numMatches;}
  void setCards(ArrayList<FlipCards> cardList)
  {
    this.allCards = cardList;
  }

  @Override
  public void update(FlipCards cardCalled) {
    if (!this.firstClick) {
      this.presenter.startTimer();
      this.firstClick = true;
    }
    cardCalled.flipCard();
    this.updateCards();
    if (this.numCorrect == this.numMatches) {
      this.presenter.stopTimer();
      long timeToCompleteMs = this.returnElapsedTime();
      FlipCardResult newResult =
              new FlipCardResult(
                      this.difficulty, this.numCorrect, this.numMatchAttempt, timeToCompleteMs);
      this.presenter.endGame(newResult);
    }
  }

  // calculating the time elapsed
  private long returnElapsedTime() {
    return this.presenter.getTimeElapsed();
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
    this.presenter.updateScore(this.numCorrect, this.numMatchAttempt);
  }

  // set numMatches based on the difficulty of the game selected buy the user
  private void setNumMatches(String difficulty) {
    if (difficulty.equals("easy")) this.numMatches = 8;
    else this.numMatches = 16;
  }

  String getInstructions() {
    return "Match the cards!(Timer goes off when you click on one of them)";
  }
}
