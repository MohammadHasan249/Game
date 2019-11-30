package com.example.game.FlipCardGame;

import java.util.ArrayList;

class FlipCardMainGameModel extends FlipCardGameModel {
  private String difficulty;
  private boolean firstClick;
  private ArrayList<Long> timeOfActionList;
  private ArrayList<FlipCards> flipCardActionList;
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
    this.timeOfActionList = new ArrayList<>();
    this.flipCardActionList = new ArrayList<>();
  }
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
    this.flipCardActionList.add(cardCalled);
    this.timeOfActionList.add(this.returnElapsedTime());
    this.updateCards(cardCalled);
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

  ArrayList<FlipCards> getFlipCardActionList() {
    return this.flipCardActionList;
  }

  ArrayList<Long> getTimeOfActionList() {
    return this.timeOfActionList;
  }

  ArrayList<FlipCards> getLastState() {
    return this.allCards;
  }
}
