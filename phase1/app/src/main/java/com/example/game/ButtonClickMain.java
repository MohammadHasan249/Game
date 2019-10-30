package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class ButtonClickMain extends AppCompatActivity {

    private ArrayList<Button> buttons;
    Button button_main;
    Random r = new Random();
    CountDownTimer timer;


    public void setButtons(int n) {
        for (int i = 0; i < n; i++) {
            Random y = new Random();
            Button b = new Button(this);
            //b.setX(y.nextInt(getDisplaySize(this).x));
            //b.setY(y.nextInt(getDisplaySize(this).y));
            buttons.add(b);
        }
    }

    void goButtonClickResult(View view) {
        Intent goResult = new Intent(getApplicationContext(), ButtonClickResult.class);
        startActivity(goResult);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_main);
        Bundle bundle = getIntent().getExtras();
        //start_time = (int) bundle.get("Time");
        //startRandomButton(mainButton);

        timer = new CountDownTimer(60000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                //setButtonRandomPosition();
            }
            public void onFinish() {
                // So far have made intent to go to result screen
                //goMathGameResult();
                // Should also save data to SQL from here
            }
        };
    }
}
