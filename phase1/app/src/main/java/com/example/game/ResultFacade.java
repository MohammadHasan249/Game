package com.example.game;

public class ResultFacade {

    CurrUser user;
    int currLevel;
    ResultFacade (CurrUser currUser){
        this.user = currUser;
        this.currLevel = currUser.getCurrLevel();
    }

    public void dataSave (int score){
        // Saves to user best / recent score

        if (currLevel == 1){
            user.setL1RecentScore(score);
            user.updateL1BestScore();
        }else if(currLevel == 2){
            user.setL2RecentScore(score);
            user.updateL2BestScore();
        }else{
            user.setL3RecentScore(score);
            user.updateL3BestScore();
        }





        // begins a popup asking to save to score board
        // updates currLevel back to 0
        user.setCurrLevel(0);



    }

}
