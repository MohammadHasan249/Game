package com.example.game.FlipCardGame.FlipCardPresenter;

import com.example.game.FlipCardGame.Cards.FlipCards;
import com.example.game.FlipCardGame.Cards.FlipCardsBuilder;
import com.example.game.FlipCardGame.FlipCardModels.FlipCardGameModel;
import com.example.game.FlipCardGame.FlipCardModels.FlipCardMainGameModel;
import com.example.game.FlipCardGame.FlipCardResult.FlipCardResult;
import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbol;
import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbolFactory;
import com.example.game.FlipCardGame.FlipCardView.FlipCardGameView;

import java.util.ArrayList;

public class FlipCardGamePresenter extends FlipCardMainPresenter {
    private FlipCardMainGameModel currGame;

    public FlipCardGamePresenter(FlipCardGameView view) {
        this.view = view;
    }

    private ArrayList<FlipCards> buildFlipCards(ArrayList<String> symbolList, FlipCardGameModel model) {
        FlipCardsBuilder cardBuilder = new FlipCardsBuilder(symbolList,
                this.view.getContext(), this.view.getTableLayout(), model, this.view.getColor());
        return cardBuilder.createCards();
    }

    @Override
    public void startDisplay() {
        this.currGame = new FlipCardMainGameModel(this.view.getDifficulty(), this);
        this.view.displayInstructions(this.currGame.getInstructions());
        FlipCardSymbolFactory factory = new FlipCardSymbolFactory();
        FlipCardSymbol symbol = factory.getSymbol(this.currGame.getNumMatches(), this.view.getSymbolChoice());
        ArrayList<FlipCards> listOfCards =
                this.buildFlipCards(symbol.getSymbols(), this.currGame);
        this.currGame.setCards(listOfCards);
    }

    @Override
    public void endGame(FlipCardResult results) {
        this.view.gameEnded(results);
    }

    public FlipCardMainGameModel getCurrGame() {
        return this.currGame;
    }
}