package com.example.game.FlipCardGame;

import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbol;
import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbolFactory;

import java.util.ArrayList;

public class FlipCardGamePresenter extends FlipCardMainPresenter {
    private FlipCardMainGameModel currGame;

    public FlipCardGamePresenter() {
    }
    void startDisplay() {
        this.currGame = new FlipCardMainGameModel(this.view.getDifficulty(), this);
        this.view.displayInstructions(this.currGame.getInstructions());
        FlipCardSymbolFactory factory = new FlipCardSymbolFactory();
        FlipCardSymbol symbol = factory.getSymbol(this.currGame.getNumMatches(), this.view.getSymbolChoice());
        ArrayList<FlipCards> listOfCards =
                this.buildFlipCards(symbol.getSymbols(), this.currGame);
        this.currGame.setCards(listOfCards);
    }

    void endGame(FlipCardResult results, FlipCardReplay replay) {
        this.view.gameEnded(results, replay);
    }

}