package com.example.game.FlipCardGame;

import java.io.Serializable;

abstract class FlipCardMainPresenter implements Serializable {
    abstract void setView(FlipCardGameView view);

    abstract void startDisplay();
}
