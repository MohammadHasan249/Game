package com.example.game.FlipCardGame.FlipCardSymbolFactory;

import java.util.ArrayList;
import java.util.Collections;

public abstract class FlipCardSymbol {
    abstract ArrayList<String> generateSymbol(int numMatches);

    ArrayList<String> shuffleArray(ArrayList list) {
        ArrayList<String> newList = list;
        Collections.shuffle(newList);
        return newList;
    }

    public abstract ArrayList<String> getSymbols();
}
