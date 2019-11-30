package com.example.game.FlipCardGame;

import java.io.Serializable;
import java.util.ArrayList;

abstract class FlipCardMainPresenter implements Serializable {
    FlipCardGameView view;

    void setView(FlipCardGameView view) {
        this.view = view;
    }

    abstract void startDisplay();

    ArrayList<FlipCards> buildFlipCards(ArrayList<String> symbolList, FlipCardGameModel model) {
        FlipCardsBuilder cardBuilder = new FlipCardsBuilder(symbolList,
                this.view.getContext(), this.view.getTableLayout(), model, this.view.getColor());
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

    void endGame(FlipCardResult results) {
        this.view.gameEnded(results);
    }
}
