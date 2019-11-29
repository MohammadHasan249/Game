package com.example.game.FlipCardGame;

import android.content.Context;
import android.widget.TableLayout;

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
}