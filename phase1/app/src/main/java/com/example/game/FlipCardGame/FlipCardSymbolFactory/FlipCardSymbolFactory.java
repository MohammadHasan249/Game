package com.example.game.FlipCardGame.FlipCardSymbolFactory;

public class FlipCardSymbolFactory {
    public FlipCardSymbolFactory() {
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
}
