package com.example.game.FlipCardGame;

import android.content.Context;
import android.widget.TableLayout;

import java.util.ArrayList;

public class FlipCardMainPresenter {
    private Context currContext;
    private FlipCardMainGame currGame;
    private FlipCardMainPresenter.View view;
    FlipCardMainPresenter(Context currContext, FlipCardMainPresenter.View view)
    {
        this.currContext = currContext;
        this.view = view;
    }
    void startGame(String difficulty, String symbolChoice , int color, TableLayout tableLayout) {
        this.currGame = new FlipCardMainGame(difficulty,this);
        ArrayList<FlipCards> listOfCards =
                this.buildFlipCards(color, this.currGame.getNumMatches(), symbolChoice, tableLayout);
        this.currGame.setCards(listOfCards);
    }
    private ArrayList<FlipCards> buildFlipCards(int color ,
                                                int numMatches, String symbolChoice,
                                                TableLayout tableLayout)
    {
        FlipCardsBuilder cardBuilder = new FlipCardsBuilder(numMatches, symbolChoice,
                this.currContext,tableLayout, this.currGame, color);
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
    public interface View{
        void gameEnded(FlipCardResult results);
        void updateScore(String toShow);
        void startTime();
        void stopTime();
        long timeElapsed();
    }
}
