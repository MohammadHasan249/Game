package com.example.game.FlipCardGame.FlipCardSymbolFactory;

import java.util.ArrayList;

public class FlipCardSymbolFactory {
    private ArrayList<String> supportedSymbols;
    public FlipCardSymbolFactory() {
        this.supportedSymbols = this.getSymbolNames();
    }

    private ArrayList<String> getSymbolNames()
    {
        ArrayList<String> strSupportedSymbolList = new ArrayList<>();
        strSupportedSymbolList.add("Character");
        strSupportedSymbolList.add("Number");
        strSupportedSymbolList.add("Ascii");
        return strSupportedSymbolList;
    }
    public FlipCardSymbol getSymbol(int numMatches, String symbolType) {
        if (1 <= numMatches && numMatches <= 16) {
            switch (symbolType) {
                case "Character":
                    return new CharacterSymbol(numMatches);
                case "Number":
                    return new NumberSymbol(numMatches);
                case "Ascii":
                    return new AsciiSymbol(numMatches);
            }
        } else {
            throw new UnsupportedOperationException("This Number of Buttons is not supported");
        }
        return null;
    }
    public ArrayList<String> getSupportedSymbols(){
             return this.supportedSymbols;
    }
}
