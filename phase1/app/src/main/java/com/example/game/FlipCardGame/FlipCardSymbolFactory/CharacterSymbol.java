package com.example.game.FlipCardGame.FlipCardSymbolFactory;

import java.util.ArrayList;
import java.util.Random;

class CharacterSymbol extends FlipCardSymbol {
    private ArrayList<String> charList;

    public CharacterSymbol(int numMatches) {
        this.charList = this.generateSymbol(numMatches);
    }

    @Override
    ArrayList<String> generateSymbol(int numMatches) {
        Random rand = new Random();
        ArrayList<String> charList = new ArrayList<>();
        for (int i = 0; i < numMatches; i++) {
            // want to generate a random character to use, since my max number of matches is 20
            // I have no problem going through the entire alphabet
            char c = (char) (rand.nextInt(26) + 'a');
            String characterStr = String.valueOf(c);
            int counter = 0;
            while (charList.contains(characterStr) && counter < numMatches) {
                c = (char) (rand.nextInt(26) + 'a');
                characterStr = String.valueOf(c);
                counter++;
            }
            //want to add 2 since we need to match them
            charList.add(characterStr);
            charList.add(characterStr);
        }
        // need to randomly shuffle them for extra fun
        charList = this.shuffleArray(charList);
        return charList;
    }

    @Override
    public ArrayList<String> getSymbols() {
        return this.charList;
    }
}
