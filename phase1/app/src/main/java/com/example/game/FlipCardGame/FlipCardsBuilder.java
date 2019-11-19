package com.example.game.FlipCardGame;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbol;
import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbolFactory;

import java.util.ArrayList;

class FlipCardsBuilder {
    private Context currContext;
    private int numMatches;
    private ArrayList<FlipCards> flipCards;
    private TableLayout stk;
    private FlipCardSymbol flipCardSymbol;
    private String symbolType;
    private FlipCardManager desiredManager;
    private int cardBackColor;

    FlipCardsBuilder(int numMatches, String symbolType, Context packageContext, TableLayout stk, FlipCardManager desiredManager, int cardBackColor) {
        this.currContext = packageContext;
        this.numMatches = numMatches;
        this.stk = stk;
        this.symbolType = symbolType;
        this.flipCardSymbol = this.getFlipCardSymbol(this.numMatches, this.symbolType);
        this.desiredManager = desiredManager;
        this.cardBackColor = cardBackColor;
    }

    private FlipCardSymbol getFlipCardSymbol(int numMatches, String symbolType) {
        FlipCardSymbolFactory localFactory = new FlipCardSymbolFactory();
        return localFactory.getSymbol(numMatches, this.symbolType);
    }

    private ArrayList<String> symbolGenerator() {
        return this.flipCardSymbol.getSymbols();
    }

    // generates a new table row in our table layout
    private TableRow generateNewRow() {
        TableRow tbRow = new TableRow(this.currContext);
        tbRow.setGravity(Gravity.CENTER_HORIZONTAL);
        tbRow.setLayoutParams(
                new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        return tbRow;
    }

    public ArrayList<FlipCards> createCards() {
        ArrayList<String> symbolList = this.symbolGenerator();
        ArrayList<FlipCards> allCardCreation = new ArrayList<>();
        this.stk.setGravity(Gravity.CENTER_VERTICAL);
        int i = 0;
        TableRow tbrow = this.generateNewRow();
        for (String s : symbolList) {
            if (i == 4) {
                this.stk.addView(tbrow);
                tbrow = this.generateNewRow();
                i = 0;
            }
            FlipCards f1 =
                    new FlipCards(this.currContext, this.cardBackColor, s, tbrow, 3, 3, this.desiredManager);
            allCardCreation.add(f1);
            i++;
        }
        stk.addView(tbrow);
        return allCardCreation;
    }
}
