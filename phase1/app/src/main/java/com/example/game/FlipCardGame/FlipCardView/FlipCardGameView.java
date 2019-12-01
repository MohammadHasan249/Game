package com.example.game.FlipCardGame.FlipCardView;

import android.content.Context;
import android.widget.TableLayout;

import com.example.game.FlipCardGame.FlipCardModels.FlipCardMainGameModel;
import com.example.game.FlipCardGame.FlipCardResult.FlipCardResult;

public interface FlipCardGameView {
    void gameEnded(FlipCardResult results);

    void updateScore(String toShow);

    void startTime();

    void stopTime();

    long timeElapsed();

    Context getContext();

    String getDifficulty();

    int getColor();

    String getSymbolChoice();

    TableLayout getTableLayout();

    void displayInstructions(String instructions);

    FlipCardResult getResults();

    FlipCardMainGameModel getCurrGame();
}