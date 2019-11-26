package com.example.game.FlipCardGame.FlipCardSymbolFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AsciiSymbol extends FlipCardSymbol {
    //factory will check if the number of matches is greater than 16
    private ArrayList<String> asciiList;

    AsciiSymbol(int numMatches) {
        this.asciiList = this.generateSymbol(numMatches);
    }
    @Override
    ArrayList<String> generateSymbol(int numMatches) {
        //getting 16 cool ascii art
        String[] asciiArray = {"C|_|", "{-_-}", "^O^", "O='`o", ".-=-.", "`o._.o'", "(ㆆ _ ㆆ)", "•͡˘㇁•͡˘",
                "ʕっ•ᴥ•ʔっ", "( 0 _ 0 )", "(-_-)", "( •͡˘ _•͡˘)ノð", "( ͡° ᴥ ͡°)", "ヽ( •_)ᕗ", "(⌐■_■)"
                , "(︶︹︶)"};
        List<String> asciiShuffledList = Arrays.asList(asciiArray);
        ArrayList<String> asciiList = new ArrayList<>();
        for (int i = 0; i < numMatches; i++) {
            asciiList.add(asciiShuffledList.get(i));
            asciiList.add(asciiShuffledList.get(i));
        }
        asciiList = this.shuffleArray(asciiList);
        return asciiList;
    }
    @Override
    public ArrayList<String> getSymbols() {
        return this.asciiList;
    }
}
