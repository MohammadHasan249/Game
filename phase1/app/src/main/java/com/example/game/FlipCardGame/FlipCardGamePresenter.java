package com.example.game.FlipCardGame;

import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbol;
import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbolFactory;

import java.util.ArrayList;

class FlipCardGamePresenter extends FlipCardMainPresenter {
    private FlipCardMainGameModel currGame;

    FlipCardGamePresenter(FlipCardGameView view) {
        this.view = view;
    }

    private ArrayList<FlipCards> buildFlipCards(ArrayList<String> symbolList, FlipCardGameModel model) {
        FlipCardsBuilder cardBuilder = new FlipCardsBuilder(symbolList,
                this.view.getContext(), this.view.getTableLayout(), model, this.view.getColor());
        return cardBuilder.createCards();
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
    void endGame(FlipCardResult results) {
        this.view.gameEnded(results);
    }

    FlipCardMainGameModel getCurrGame() {
        return this.currGame;
    }
}