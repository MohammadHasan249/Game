package com.example.game.FlipCardGame.FlipCardPresenter;

import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbolFactory;
import com.example.game.FlipCardGame.FlipCardView.FlipCardCustomizationView;

import java.util.ArrayList;

public class FlipCardCustomizationPresenter {
    private FlipCardCustomizationView view;
    private FlipCardSymbolFactory symbolFactory;
    public FlipCardCustomizationPresenter(FlipCardCustomizationView view)
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
