package com.example.game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ResultFacade {

    private CurrUser user;
    private int currLevel;
    private ScoreBoardDataInput scoreBoardDataInput;
    private Context context;


    ResultFacade (Context context){
        this.context = context;
        this.user = new CurrUser(context);

        this.scoreBoardDataInput = new ScoreBoardDataInput(context, this.user.getCurrLevel(), this.user.getDifficultySelected());
        this.currLevel = this.user.getCurrLevel();
    }

    public void dataSave (int score){
        // Saves to user best / recent score

        if (this.currLevel == 1){
            user.setL1RecentScore(score);
            user.updateL1BestScore();
        }else if(this.currLevel == 2){
            user.setL2RecentScore(score);
            user.updateL2BestScore();
        }else{
            user.setL3RecentScore(score);
            user.updateL3BestScore();
        }
        
        // begins a popup asking to save to score board
        this.scoreBoardDataInput.requestSavePermission(score);

        // updates currLevel back to 0
        this.user.setCurrLevel(0);



    }

}
