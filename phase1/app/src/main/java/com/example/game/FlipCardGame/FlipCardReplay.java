package com.example.game.FlipCardGame;

import java.io.Serializable;
import java.util.ArrayList;

public class FlipCardReplay implements Serializable {
    private String[] symbolStateList1;
    private ArrayList<String> actionList;
    private ArrayList<Long> timeOfActionList;
    private int endGameCorrect;
    private int endGameAttempt;

    public FlipCardReplay() {
    }

    void setEndGameStateList(ArrayList<FlipCards> endGameStateList) {
        this.symbolStateList1 = new String[endGameStateList.size()];
        for (int i = 0; i < endGameStateList.size(); i++) {
            this.symbolStateList1[i] = endGameStateList.get(i).getSymbol();
        }
    }
    // void setAction(FlipCards flipcard) {this.actionList.add(flipcard);}
    //ArrayList<FlipCards> getEndGameState(){return this.endGameStateList;}
}
