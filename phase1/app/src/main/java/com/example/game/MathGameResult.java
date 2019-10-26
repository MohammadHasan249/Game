package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MathGameResult extends AppCompatActivity {

    int totalCorrect, totalFailedAttempts, time;
    double speed;
    TextView textTotalCorrect, textFailedAttempts, textSpeed;

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
        speed = Math.round(100*(double)totalCorrect/time)/100.0 ;
        // Math.round produces an int, so need to divide by 100.0 to get double
        // Math.round will round to nearest decimal, so if get a value of something like 0.131123 but if round this
        // to nearest decimal will be 0, so need to multiple by 100 --> 13.1123 then round to remove excess digits --> 13 divide by 100 --> 0.13

        textTotalCorrect.setText(Integer.toString(totalCorrect));
        textFailedAttempts.setText(Integer.toString(totalFailedAttempts));
        textSpeed.setText(Double.toString(speed) + "/s");
    }
}
