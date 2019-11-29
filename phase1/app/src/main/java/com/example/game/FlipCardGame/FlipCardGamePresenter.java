package com.example.game.FlipCardGame;

import android.content.Context;
import android.widget.TableLayout;

import java.util.ArrayList;

public class FlipCardGamePresenter extends FlipCardMainPresenter {
    private FlipCardMainGameModel currGame;
    private FlipCardGameView view;

    public FlipCardGamePresenter() {
    }
    void setView(FlipCardGameView view) {
        this.view = view;
    }

    void startDisplay() {
        this.currGame = new FlipCardMainGameModel(this.view.getDifficulty(), this);
        this.view.displayInstructions(this.currGame.getInstructions());
        ArrayList<FlipCards> listOfCards =
                this.buildFlipCards(this.view.getContext(), this.view.getColor(),
                        this.currGame.getNumMatches(), this.view.getSymbolChoice(),
                        this.view.getTableLayout());
        this.currGame.setCards(listOfCards);
    }

    private ArrayList<FlipCards> buildFlipCards(Context currContext, int color,
                                                int numMatches, String symbolChoice,
                                                TableLayout tableLayout)
    {
        FlipCardsBuilder cardBuilder = new FlipCardsBuilder(numMatches, symbolChoice,
                currContext, tableLayout, this.currGame, color);
        return cardBuilder.createCards();
    }
    long getTimeElapsed()
    {
        return this.view.timeElapsed();
    }
    void startTimer()
    {
        this.view.startTime();
    }
    void stopTimer()
    {
        this.view.stopTime();
    }
    void endGame(FlipCardResult results)
    {
        this.view.gameEnded(results);
    }
    void updateScore(int numCorrect, int numMatchAttempt)
    {
        String toShow = (numCorrect) + " | " + (numMatchAttempt);
        this.view.updateScore(toShow);
    }
}