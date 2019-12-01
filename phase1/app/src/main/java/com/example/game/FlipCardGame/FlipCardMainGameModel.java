package com.example.game.FlipCardGame;

import java.util.ArrayList;

class FlipCardMainGameModel extends FlipCardGameModel {
  private String difficulty;
  private boolean firstClick;
  private ArrayList<Long> timeActionList;
  private ArrayList<FlipCards> cardActionList;
  FlipCardMainGameModel(
          String difficulty,
          FlipCardMainPresenter presenter) {
    this.flipCardGameManagerBuilder(difficulty,presenter);
  }

  private void flipCardGameManagerBuilder(
          String difficulty,
          FlipCardMainPresenter presenter)
  {
    this.difficulty = difficulty;
    this.setNumMatches(this.difficulty);
    this.numCorrect = 0;
    this.numMatchAttempt = 0;
    this.presenter = presenter;
    this.firstClick = false;
    this.timeActionList = new ArrayList<>();
    this.cardActionList = new ArrayList<>();
  }

  @Override
  public void update(FlipCards cardCalled) {
    if (!this.firstClick) {
      this.presenter.startTimer();
      this.firstClick = true;
    }
    if (!cardCalled.isFlipped()) {

      this.cardActionList.add(cardCalled);
      this.timeActionList.add(this.returnElapsedTime());
      cardCalled.flipCard();
    }
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


  // set numMatches based on the difficulty of the game selected buy the user
  private void setNumMatches(String difficulty) {
    if (difficulty.equals("easy")) this.numMatches = 8;
    else this.numMatches = 16;
  }

  String getInstructions() {
    return "Match the cards!(Timer goes off when you click on one of them)";
  }

  ArrayList<FlipCards> getCardActionList() {
    return this.cardActionList;
  }

  ArrayList<Long> getTimeActionList() {
    return this.timeActionList;
  }

  ArrayList<FlipCards> getLastStateList() {
    return this.allCards;
  }
}
