package com.example.game.FlipCardGame;

import android.os.CountDownTimer;

import java.util.ArrayList;

abstract class FlipCardGameModel {
    int numMatches;
    int numCorrect;
    int numMatchAttempt;
    ArrayList<FlipCards> allCards;
    FlipCardMainPresenter presenter;

    abstract void update(FlipCards flipCards);

    int getNumMatches() {
        return this.numMatches;
    }

    // calculating the time elapsed
    long returnElapsedTime() {
        return this.presenter.getTimeElapsed();
    }

    // goes over the list of all cards, if 2 of them are flipped, check if they are a match
    // if they are then update cards and lock them
    // if they aren't then we put a delay then flip them back
    void updateCards(FlipCards cardCalled) {
        if (!cardCalled.isFlipped())
            cardCalled.flipCard();
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

    // putting delay then flipping the 2 in correctly matched cards back
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

    // update score board
    private void updateScore() {
        this.presenter.updateScore(this.numCorrect, this.numMatchAttempt);
    }
}