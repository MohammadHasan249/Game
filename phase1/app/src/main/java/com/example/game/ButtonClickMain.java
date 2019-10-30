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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class ButtonClickMain extends AppCompatActivity {

    private Button[][] buttons = new Button[4][4];
    //LinearLayout linearLayout = findViewById(R.id.linearLayoutbtns);
    //TableLayout tableLayout;
    //TableRow currentRow;
    Button button;
    Random r = new Random();
    CountDownTimer timer;

/**
    public void setButtons() {
        tableLayout = new TableLayout(this);
        for (int row = 0; row < 4; row++) {
            currentRow = new TableRow(this);
            for (int col = 0; col < 4; col++) {
                button = new Button(this);
                buttons[row][col] = button;
                currentRow.addView(button);
            }
            this.tableLayout.addView(currentRow);
        }
        linearLayout.addView(tableLayout);
    }
*/
    void goButtonClickResult(View view) {
        Intent goResult = new Intent(getApplicationContext(), ButtonClickResult.class);
        startActivity(goResult);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_main);
        /**
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
         */
    }
}
