package com.example.game.FlipCardGame.FlipCardResult;

import java.io.Serializable;

// a class to keep track of all the results in the flip card game
public class FlipCardResult implements Serializable {
  private int numCorrect;
  private int timeToCompletion;
  private int numTotalMatches;
  private String difficulty;

  public FlipCardResult(String difficulty, int numMatches, int numTotalMatches, long time) {
    this.difficulty = difficulty;
    this.numCorrect = numMatches;
    this.timeToCompletion = this.roundTime(time);
    this.numTotalMatches = numTotalMatches;
  }

  private int roundTime(long time) {
    return Math.round(time / 1000);
  }

  public int getTimeToCompletion() {
    return this.timeToCompletion;
  }

  public String getStrTimeToCompletion() {
    return (this.timeToCompletion) + " seconds";
  }

  public String getStrNumCorrect() {
    return Integer.toString(this.numCorrect);
  }

  public String getStrNumIncorrect() {
    return Integer.toString(this.numTotalMatches - this.numCorrect);
  }

  public String getStrDifficulty() {
    return this.difficulty;
  }
}
