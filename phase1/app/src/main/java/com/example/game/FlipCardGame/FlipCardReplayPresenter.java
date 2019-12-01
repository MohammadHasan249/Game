package com.example.game.FlipCardGame;

class FlipCardReplayPresenter extends FlipCardMainPresenter {

    FlipCardReplayPresenter(FlipCardGameView view) {
        this.view = view;
    }

    @Override
    void startDisplay() {
        FlipCardMainGameModel model = this.view.getCurrGame();
        FlipCardReplayModel replayModel = new FlipCardReplayModel(model.getCardActionList(), model.getTimeActionList(),
                model.getLastStateList(), this);
        replayModel.startDisplay();
    }
}
