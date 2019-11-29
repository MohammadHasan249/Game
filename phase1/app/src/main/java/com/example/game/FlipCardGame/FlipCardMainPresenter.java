package com.example.game.FlipCardGame;

import android.content.Context;
import android.widget.TableLayout;

import java.io.Serializable;
import java.util.ArrayList;

abstract class FlipCardMainPresenter implements Serializable {
    FlipCardGameView view;

    void setView(FlipCardGameView view) {
        this.view = view;
    }

    abstract void startDisplay();

    ArrayList<FlipCards> buildFlipCards(Context currContext, int color,
                                        int numMatches, String symbolChoice,
                                        TableLayout tableLayout, FlipCardGameModel model) {
        FlipCardsBuilder cardBuilder = new FlipCardsBuilder(numMatches, symbolChoice,
                currContext, tableLayout, model, color);
        return cardBuilder.createCards();
    }

    long getTimeElapsed() {
        return this.view.timeElapsed();
    }

    void startTimer() {
        this.view.startTime();
    }

    void stopTimer() {
        this.view.stopTime();
    }

    void updateScore(int numCorrect, int numMatchAttempt) {
        String toShow = (numCorrect) + " | " + (numMatchAttempt);
        this.view.updateScore(toShow);
    }
}
