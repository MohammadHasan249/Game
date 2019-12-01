package com.example.game.FlipCardGame.FlipCardPresenter;

import com.example.game.FlipCardGame.FlipCardModels.FlipCardMainGameModel;
import com.example.game.FlipCardGame.FlipCardModels.FlipCardReplayModel;
import com.example.game.FlipCardGame.FlipCardView.FlipCardGameView;

public class FlipCardReplayPresenter extends FlipCardMainPresenter {

    public FlipCardReplayPresenter(FlipCardGameView view) {
        this.view = view;
    }

    @Override
    public void startDisplay() {
        FlipCardMainGameModel model = this.view.getCurrGame();
        FlipCardReplayModel replayModel = new FlipCardReplayModel(model.getCardActionList(), model.getTimeActionList(),
                model.getLastStateList(), this);
        replayModel.startDisplay();
    }
}
