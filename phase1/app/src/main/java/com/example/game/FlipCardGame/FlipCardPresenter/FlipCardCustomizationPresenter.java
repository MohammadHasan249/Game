package com.example.game.FlipCardGame.FlipCardPresenter;

import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbolFactory;

import java.util.ArrayList;

public class FlipCardCustomizationPresenter {
    private FlipCardCustomizationPresenter.View view;
    private FlipCardSymbolFactory symbolFactory;
    public FlipCardCustomizationPresenter(FlipCardCustomizationPresenter.View view)
    {
        this.symbolFactory = new FlipCardSymbolFactory();
        this.view = view;
    }
    public void initializeScreen()
    {
        this.view.addToSpinner(this.symbolFactory.getSupportedSymbols());
    }
    public interface View
    {
        void addToSpinner(ArrayList<String> listOfSupportedSymbols);
    }
}
