package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.FlipCardGame.FlipCardInit;

public class MathGameResult extends AppCompatActivity {

    int totalCorrect, totalFailedAttempts, time;
    double speed;
    TextView textTotalCorrect, textFailedAttempts, textSpeed;
    CurrUser user;
    ResultFacade resultFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game_result);

        textTotalCorrect = findViewById(R.id.textTotalCorrect);
        textFailedAttempts = findViewById(R.id.textFailedAttempts);
        textSpeed = findViewById(R.id.textSpeed);

        Intent intentOrigin = getIntent();
        totalCorrect = intentOrigin.getIntExtra("numCorrect", 0);
        totalFailedAttempts = intentOrigin.getIntExtra("numFailedAttempts", 0);
        time = intentOrigin.getIntExtra("time", 0);
        speed = Math.round(100 * (double) totalCorrect / time) / 100.0;
        // Math.round produces an int, so need to divide by 100.0 to get double
        // Math.round will round to nearest decimal, so if get a value of something like 0.131123 but if round this
        // to nearest decimal will be 0, so need to multiple by 100 --> 13.1123 then round to remove excess digits --> 13 divide by 100 --> 0.13
        String strTotalCorrect = Integer.toString(totalCorrect);
        String strTotalFailedAttempts = Integer.toString(totalFailedAttempts);
        String strSpeed = speed + "/s";
        textTotalCorrect.setText(strTotalCorrect);
        textFailedAttempts.setText(strTotalFailedAttempts);
        textSpeed.setText(strSpeed);


        resultFacade = new ResultFacade(this);
        resultFacade.dataSave(totalCorrect);

//        user = new CurrUser(this);
//        user.setL2RecentScore(totalCorrect);
//        user.updateL2BestScore();
//        user.setCurrLevel(3);
    }

    public void btnContinueFunc(View view) {
        FlipCardInit newFlipCardGame = new FlipCardInit();
        newFlipCardGame.startGame(this );
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        start.putExtra("androidBack", 1);
        startActivity(start);
        finish();
    }
}
