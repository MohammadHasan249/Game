package com.example.game.FlipCardGame.FlipCardSymbolFactory;

import java.util.ArrayList;
import java.util.Random;

class NumberSymbol implements FlipCardSymbol {
    private ArrayList<String> numberList;

    NumberSymbol(int numMatches)
    {
        this.numberList = generateSymbol(numMatches);
    }
    @Override
    public ArrayList<String> generateSymbol(int numMatches)
    {
        Random rand = new Random();
        for (int i =0; i < numMatches; i ++)
        {
            int randomInt = rand.nextInt(numMatches);
            String randomIntStr = Integer.toString(randomInt);
            while (this.numberList.contains(randomIntStr))
            {
                randomInt = rand.nextInt(numMatches);
                randomIntStr = Integer.toString(randomInt);
            }
            this.numberList.add(randomIntStr);
        }
        return this.numberList;
    }
    ArrayList<String> getSymbols(){return this.numberList;}
}
