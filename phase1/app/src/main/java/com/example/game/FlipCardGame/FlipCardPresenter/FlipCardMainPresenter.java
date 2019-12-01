package com.example.game.FlipCardGame.FlipCardPresenter;

import com.example.game.FlipCardGame.FlipCardResult.FlipCardResult;
import com.example.game.FlipCardGame.FlipCardView.FlipCardGameView;

public abstract class FlipCardMainPresenter {
    FlipCardGameView view;

    public abstract void startDisplay();

    public long getTimeElapsed() {
        return this.view.timeElapsed();
    }

    public void startTimer() {
        this.view.startTime();
    }

    public void stopTimer() {
        this.view.stopTime();
    }

    public void updateScore(int numCorrect, int numMatchAttempt) {
        String toShow = (numCorrect) + " | " + (numMatchAttempt);
        this.view.updateScore(toShow);
    }

    public void endGame(FlipCardResult results) {
        this.view.gameEnded(results);
    }
}
