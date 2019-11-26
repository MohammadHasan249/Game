package com.example.game.FlipCardGame;

import android.content.Context;

import com.example.game.CurrUser;

public class UserInfoFascade {
    CurrUser user;
    UserInfoFascade(Context currContext)
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
}
