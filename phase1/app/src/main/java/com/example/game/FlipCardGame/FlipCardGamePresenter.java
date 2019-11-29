package com.example.game.FlipCardGame;

import java.util.ArrayList;

public class FlipCardGamePresenter extends FlipCardMainPresenter {
    private FlipCardMainGameModel currGame;

    public FlipCardGamePresenter() {
    }
    void startDisplay() {
        this.currGame = new FlipCardMainGameModel(this.view.getDifficulty(), this);
        this.view.displayInstructions(this.currGame.getInstructions());
        ArrayList<FlipCards> listOfCards =
                this.buildFlipCards(this.view.getContext(), this.view.getColor(),
                        this.currGame.getNumMatches(), this.view.getSymbolChoice(),
                        this.view.getTableLayout(), this.currGame);
        this.currGame.setCards(listOfCards);
    }
}