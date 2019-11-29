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
        FlipCardReplay replay = this.view.getReplay();
        this.view.displayInstructions("Welcome to the Replay!");
        ArrayList<FlipCards> flipCards = this.buildFlipCards(replay.getSymbols(), this);
    }
}
