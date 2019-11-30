package com.example.game.FlipCardGame;

import java.io.Serializable;
import java.util.ArrayList;

public class FlipCardReplay implements Serializable {
    private ArrayList<String> startingStateArray;
    private ArrayList<Long> timeOfAction;
    private ArrayList<Integer> indexOfCardClicked;

    FlipCardReplay(ArrayList<FlipCards> startingState) {
        this.timeOfAction = new ArrayList<>();
        this.indexOfCardClicked = new ArrayList<>();
        this.startingStateArray = this.setStartingStateSymbol(startingState);
    }

    private ArrayList<String> setStartingStateSymbol(ArrayList<FlipCards> startingState) {
        ArrayList<String> symbolList = new ArrayList<>();
        for (FlipCards s : startingState) {
            symbolList.add(s.getSymbol());
        }
        return symbolList;
    }

    ArrayList<String> getSymbols() {
        return this.startingStateArray;
    }

    void addAction(int indexOfCard, long timeAtClick) {
        this.timeOfAction.add(timeAtClick);
        this.indexOfCardClicked.add(indexOfCard);
    }
}
