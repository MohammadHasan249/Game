package com.example.game.FlipCardGame;

import android.content.Context;

import com.example.game.CurrUser;

public class UserInfoFacade {
    CurrUser user;
    UserInfoFacade(Context currContext)
    {
        this.user = new CurrUser(currContext);
    }
    void setLevel(int level){
        this.user.setCurrLevel(level);
    }
    void startMusic()
    {
        this.user.playMusic();
    }
    void stopMusic()
    {
        this.user.stopMusic();
    }
    String getSelectedDifficulty()
    {
        return this.user.getDifficultySelected();
    }
    int getSelectedColor()
    {
        return this.user.getColorSelected();
    }
    CurrUser getUser()
    {
        return this.user;
    }
}
