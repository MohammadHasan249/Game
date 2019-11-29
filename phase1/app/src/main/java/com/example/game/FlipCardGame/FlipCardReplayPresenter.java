package com.example.game.FlipCardGame;

import java.util.ArrayList;

public class FlipCardReplayPresenter extends FlipCardMainPresenter implements FlipCardGameModel {
    FlipCardReplayPresenter() {
    }

    @Override
    public void update(FlipCards flipCard) {
    }

    @Override
    void setView(FlipCardGameView view) {
        this.view = view;
    }

    @Override
    void startDisplay() {
        FlipCardReplayModel model = new FlipCardReplayModel();
        this.view.displayInstructions("Welcome to the Replay!");
        ArrayList<String> symboltest = new ArrayList<>();
        symboltest.add("hello");
        symboltest.add("hello");
        symboltest.add("hello");
        symboltest.add("hello");
        symboltest.add("hello");
        ArrayList<FlipCards> listOfCards =
                this.buildFlipCards(symboltest, model);
    }

}
