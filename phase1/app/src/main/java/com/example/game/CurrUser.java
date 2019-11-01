package com.example.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;

import static android.content.Context.MODE_PRIVATE;

public class CurrUser {

  private MediaPlayer currMedia;
  private Context context;

  private SharedPreferences sharedPreferences;
  private SQLiteDatabase gameDB;
  private Cursor c;

  private String username;

  private int colorSelected, musicSelected;
  private String difficultySelected;

  private int currLevel;

  // Level 1 (Button Click)
  private int l1EasyBestScore, l1HardBestScore, l1RecentScore;

  // Level 2 (Math Game)
  private int l2EasyBestScore, l2HardBestScore, l2RecentScore;

  // Level 3 (Flip Card)
  private int l3EasyBestScore, l3HardBestScore, l3RecentScore;

  public CurrUser(Context context) {
    this.context = context;

    sharedPreferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
    username = sharedPreferences.getString("loggedInUsername", "NA");
    gameDB = context.openOrCreateDatabase("gameDB", MODE_PRIVATE, null);
    c = gameDB.rawQuery("SELECT * FROM users WHERE username='" + username + "'", null);
    c.moveToFirst();

    // Fill customizations
    colorSelected = c.getInt(c.getColumnIndex("colorSelected"));
    difficultySelected = c.getString(c.getColumnIndex("difficultySelected"));
    musicSelected = c.getInt(c.getColumnIndex("musicSelected"));

    // Fill level left off at
    currLevel = c.getInt(c.getColumnIndex("currLevel"));

    // Fill Level 1 Data
    l1EasyBestScore = c.getInt(c.getColumnIndex("l1EasyBestScore"));
    l1HardBestScore = c.getInt(c.getColumnIndex("l1HardBestScore"));
    l1RecentScore = c.getInt(c.getColumnIndex("l1RecentScore"));

    // Fill Level 2 Data
    l2EasyBestScore = c.getInt(c.getColumnIndex("l2EasyBestScore"));
    l2HardBestScore = c.getInt(c.getColumnIndex("l2HardBestScore"));
    l2RecentScore = c.getInt(c.getColumnIndex("l2RecentScore"));

    // Fill Level 3 Data
    l3EasyBestScore = c.getInt(c.getColumnIndex("l3EasyBestScore"));
    l3HardBestScore = c.getInt(c.getColumnIndex("l3HardBestScore"));
    l3RecentScore = c.getInt(c.getColumnIndex("l3RecentScore"));
  }

  // Methods

  public void playMusic() {
    if (currMedia != null) {
      currMedia.stop();
    }
    if (this.getMusicSelected() != 0) {
      currMedia = MediaPlayer.create(this.context, this.getMusicSelected());
      currMedia.start();
    }
  }

  public void stopMusic() {
    if (currMedia != null) {
      currMedia.stop();
    }
  }

  void updateL1BestScore() {
    if (this.getDifficultySelected().equals("easy")) {
      if (this.getL1RecentScore() > this.getL1EasyBestScore()) {

        this.setL1EasyBestScore(this.getL1RecentScore());
      }
    } else {
      if (this.getL1RecentScore() > this.getL1HardBestScore()) {
        this.setL1HardBestScore(this.getL1RecentScore());
      }
    }
  }

  void updateL2BestScore() {
    if (this.getDifficultySelected().equals("easy")) {
      if (this.getL2RecentScore() > this.getL2EasyBestScore()) {
        this.setL2EasyBestScore(this.getL2RecentScore());
      }
    } else {
      if (this.getL2RecentScore() > this.getL2HardBestScore()) {
        this.setL2HardBestScore(this.getL2RecentScore());
      }
    }
  }

  public void updateL3BestScore() {
    if (this.getDifficultySelected().equals("easy")) {
      if (this.getL3RecentScore() < this.getL3EasyBestScore()) {
        this.setL1EasyBestScore(this.getL1RecentScore());
      }
    } else {
      if (this.getL3RecentScore() < this.getL3HardBestScore()) {
        this.setL3HardBestScore(this.getL3RecentScore());
      }
    }
  }

  // Setters and Getters

  String getUsername() {
    return username;
  }

  public int getColorSelected() {
    return colorSelected;
  }

  void setColorSelected(int colorSelected) {
    this.colorSelected = colorSelected;
    gameDB.execSQL(
        "UPDATE users SET colorSelected= '"
            + colorSelected
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  public String getDifficultySelected() {
    return difficultySelected;
  }

  void setDifficultySelected(String difficultySelected) {
    this.difficultySelected = difficultySelected;
    gameDB.execSQL(
        "UPDATE users SET difficultySelected= '"
            + difficultySelected
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  private int getMusicSelected() {
    return musicSelected;
  }

  void setMusicSelected(int musicSelected) {
    this.musicSelected = musicSelected;

    gameDB.execSQL(
        "UPDATE users SET musicSelected= '"
            + musicSelected
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  int getCurrLevel() {
    return currLevel;
  }

  public void setCurrLevel(int currLevel) {
    this.currLevel = currLevel;
    gameDB.execSQL(
        "UPDATE users SET currLevel= '"
            + currLevel
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  private int getL1EasyBestScore() {
    return l1EasyBestScore;
  }

  private void setL1EasyBestScore(int l1EasyBestScore) {
    this.l1EasyBestScore = l1EasyBestScore;
    gameDB.execSQL(
        "UPDATE users SET l1EasyBestScore= '"
            + l1EasyBestScore
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  private int getL1HardBestScore() {
    return l1HardBestScore;
  }

  private void setL1HardBestScore(int l1HardBestScore) {
    this.l1HardBestScore = l1HardBestScore;
    gameDB.execSQL(
        "UPDATE users SET l1HardBestScore= '"
            + l1HardBestScore
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  int getL1RecentScore() {
    return l1RecentScore;
  }

  void setL1RecentScore(int l1RecentScore) {
    this.l1RecentScore = l1RecentScore;
    gameDB.execSQL(
        "UPDATE users SET l1RecentScore= '"
            + l1RecentScore
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  private int getL2EasyBestScore() {
    return l2EasyBestScore;
  }

  private void setL2EasyBestScore(int l2EasyBestScore) {
    this.l2EasyBestScore = l2EasyBestScore;
    gameDB.execSQL(
        "UPDATE users SET l2EasyBestScore= '"
            + l2EasyBestScore
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  private int getL2HardBestScore() {
    return l2HardBestScore;
  }

  private void setL2HardBestScore(int l2HardBestScore) {
    this.l2HardBestScore = l2HardBestScore;
    gameDB.execSQL(
        "UPDATE users SET l2HardBestScore= '"
            + l2HardBestScore
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  int getL2RecentScore() {
    return l2RecentScore;
  }

  void setL2RecentScore(int l2RecentScore) {
    this.l2RecentScore = l2RecentScore;
    gameDB.execSQL(
        "UPDATE users SET l2RecentScore= '"
            + l2RecentScore
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  private int getL3EasyBestScore() {
    return l3EasyBestScore;
  }

  public void setL3EasyBestScore(int l3EasyBestScore) {
    this.l3EasyBestScore = l3EasyBestScore;
    gameDB.execSQL(
        "UPDATE users SET l3EasyBestScore= '"
            + l3EasyBestScore
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  private int getL3HardBestScore() {
    return l3HardBestScore;
  }

  private void setL3HardBestScore(int l3HardBestScore) {
    this.l3HardBestScore = l3HardBestScore;
    gameDB.execSQL(
        "UPDATE users SET l3HardBestScore= '"
            + l3HardBestScore
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }

  int getL3RecentScore() {
    return l3RecentScore;
  }

  public void setL3RecentScore(int l3RecentScore) {
    this.l3RecentScore = l3RecentScore;
    gameDB.execSQL(
        "UPDATE users SET l3RecentScore= '"
            + l3RecentScore
            + "' WHERE username = '"
            + this.getUsername()
            + "'");
  }
}
