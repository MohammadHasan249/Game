package com.example.game.FlipCardGame;


import android.content.Context;
import android.content.Intent;

import com.example.game.FlipCardGame.FlipCardView.FlipCardCustomizationView;

//this class acts as a front facing interface for others to initialize the FlipCardGame
public class FlipCardInit {
    public FlipCardInit(){
    }
    public void startGame(Context currContext)
    {
        Intent toCustomizationScreen = new Intent(currContext, FlipCardCustomizationView.class);
        currContext.startActivity(toCustomizationScreen);
    }
}
