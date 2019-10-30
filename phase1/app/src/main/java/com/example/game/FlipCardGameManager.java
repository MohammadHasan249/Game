package com.example.game;

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

public class FlipCardGameManager {
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
    private boolean firstclick;
    Chronometer timer;

    FlipCardGameManager(String difficulty, int colorInt, TextView flipCardScore, Context packageContext, TableLayout stk, FlipCardMain observer, Chronometer timer) {
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
        this.firstclick = false;
        this.timer = timer;
    }

    void update() {
        if (!this.firstclick) {
            this.timer.setBase(SystemClock.elapsedRealtime());
            this.timer.start();
            this.firstclick = true;
        }
        this.updateCards();
        if (this.numCorrect == this.numMatches) {
            this.timer.stop();
            this.returnElapsedTime();
            this.observer.endGame();
        }
    }

    private int returnElapsedTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - this.timer.getBase();
        return Math.round(elapsedMillis / 1000);
    }
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

    private void nonMatchCardDelay(final FlipCards f1, final FlipCards f2) {
        new CountDownTimer(450, 450) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                f1.flipCard();
                f2.flipCard();
                FlipCards.disableCards = false;
            }
        }.start();
    }

    private void updateScore() {
        String toShow = (this.numCorrect) + " | " + (this.numMatchAttempt);
        this.flipCardScore.setText(toShow);
    }

    private ArrayList<Character> symbolGenerator(int numMatches) {
        Random rand = new Random();
        ArrayList<Character> charList = new ArrayList<>();
        for (int i = 0; i < numMatches; i++) {
            //want to generate a random character to use, since my max number of matches is 20
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

    private void setNumMatches(String difficulty) {
        if (difficulty.equals("easy"))
            this.numMatches = 10;
        else
            this.numMatches = 20;
    }

    private TableRow generateNewRow() {
        TableRow tbrow = new TableRow(this.packageContext);
        tbrow.setGravity(Gravity.CENTER_HORIZONTAL);
        tbrow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        return tbrow;
    }

    private ArrayList<FlipCards> generateCards(int numMatches) {
        ArrayList<Character> charList = this.symbolGenerator(numMatches);
        ArrayList<FlipCards> allCardCreation = new ArrayList<>();
        this.stk.setGravity(Gravity.CENTER_VERTICAL);
        int i = 0;
        TableRow tbrow = this.generateNewRow();
        for (Character s : charList) {
            if (i == 5) {
                this.stk.addView(tbrow);
                tbrow = this.generateNewRow();
                i = 0;
            }
            FlipCards f1 = new FlipCards(this.packageContext, cardBackColor, s.toString(), tbrow, 3, 3, this);
            allCardCreation.add(f1);
            i++;
        }
        stk.addView(tbrow);
        return allCardCreation;
    }
}
