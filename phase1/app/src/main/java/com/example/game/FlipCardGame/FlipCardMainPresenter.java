package com.example.game.FlipCardGame;

abstract class FlipCardMainPresenter {
    FlipCardGameView view;
    abstract void startDisplay();

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
