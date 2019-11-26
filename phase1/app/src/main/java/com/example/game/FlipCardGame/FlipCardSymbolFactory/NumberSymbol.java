package com.example.game.FlipCardGame.FlipCardSymbolFactory;

import java.util.ArrayList;
import java.util.Random;

class NumberSymbol extends FlipCardSymbol {
    private ArrayList<String> numberList;

    NumberSymbol(int numMatches)
    {
        this.numberList = generateSymbol(numMatches);
    }
    @Override
    ArrayList<String> generateSymbol(int numMatches)
    {
        Random rand = new Random();
        ArrayList<String> listOfNumbers = new ArrayList<>();
        for (int i =0; i < numMatches; i ++)
        {
            int randomInt = rand.nextInt(numMatches);
            String randomIntStr = Integer.toString(randomInt);
            int counter = 0;
            while (listOfNumbers.contains(randomIntStr) && counter < numMatches)
            {
                randomInt = rand.nextInt(numMatches);
                randomIntStr = Integer.toString(randomInt);
                counter++;
            }
            listOfNumbers.add(randomIntStr);
            listOfNumbers.add(randomIntStr);
        }
        this.shuffleArray(listOfNumbers);
        return listOfNumbers;
    }

    @Override
    public ArrayList<String> getSymbols() {
        return this.numberList;
    }
}
