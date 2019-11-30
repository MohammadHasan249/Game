package com.example.game.FlipCardGame;

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
}
