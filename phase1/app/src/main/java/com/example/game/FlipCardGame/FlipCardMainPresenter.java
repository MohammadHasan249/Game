package com.example.game.FlipCardGame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;

import java.util.ArrayList;

public class FlipCardMainPresenter {
    private UserInfoFascade currUserInfo;
    private Context currContext;
    FlipCardMainPresenter(Context currContext)
    {
        this.currContext = currContext;
        this.currUserInfo = new UserInfoFascade(this.currContext);
    }
    void startGame(){

    }
    public interface View{
        ArrayList<Button> createButtonInstances(Drawable colour);
        void disableButtons();
        void btnClicked();
        void onEnd();
    }
}
