package com.example.game.FlipCardGame;

import com.example.game.CurrUser;

import java.io.Serializable;

// a class to keep track of all the results in the flip card game
class FlipCardResult implements Serializable {
  private int numCorrect;
  private int timeToCompletion;
  private int numTotalMatches;
  private String difficulty;

  FlipCardResult(String difficulty, int numMatches, int numTotalMatches, long time) {
    this.difficulty = difficulty;
    this.numCorrect = numMatches;
    this.timeToCompletion = this.roundTime(time);
    this.numTotalMatches = numTotalMatches;
  }

  private int roundTime(long time) {
    return Math.round(time / 1000);
  }

  int getNumCorrect() {
    return this.numCorrect;
  }

  int getnumIncorrect() {
    return this.numTotalMatches - this.numCorrect;
  }

  int getTimeToCompletion() {
    return this.timeToCompletion;
  }

  String getStrTimeToCompletion() {
    return (this.timeToCompletion) + " seconds";
  }

  String getStrNumCorrect() {
    return Integer.toString(this.numCorrect);
  }

  String getStrNumIncorrect() {
    return Integer.toString(this.numTotalMatches - this.numCorrect);
  }

  String getStrDifficulty() {
    return this.difficulty;
  }

  void setFlipCardResult(CurrUser user) {
    user.setCurrLevel(0);
    user.setL3RecentScore(this.timeToCompletion);
    user.updateL3BestScore();
  }
}
