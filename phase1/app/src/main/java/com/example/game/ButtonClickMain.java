package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class ButtonClickMain extends AppCompatActivity {

    private Button[][] buttons = new Button[5][4];
    Random r = new Random();
    CountDownTimer timer;


    void goButtonClickResult(View view) {
        Intent goResult = new Intent(getApplicationContext(), ButtonClickResult.class);
        startActivity(goResult);
    }

    private void buttonVisVisible(Button[][] buttons){
        int testR = r.nextInt(5);
        int testC = r.nextInt(4);
        buttons[testR][testC].setVisibility(View.VISIBLE);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_main);
        TableLayout tableLayout = findViewById(R.id.tableLayoutBtns);
        for (int r = 0; r < 5; r++) {
            TableRow currentRow = new TableRow(this);
            for (int c = 0; c < 4; c++) {
                Button currentButton = new Button(this);
                buttons[r][c] = currentButton;
                currentRow.addView(currentButton);
            }
            tableLayout.addView(currentRow);
        }

        Bundle bundle = getIntent().getExtras();

        timer = new CountDownTimer(60000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 4; c++) {
                        buttons[r][c].setVisibility(View.INVISIBLE);
                    }
                }
                buttonVisVisible(buttons);

            }
            
            public void onFinish() {

                // So far have made intent to go to result screen
                //goMathGameResult();
                // Should also save data to SQL from here
            }
        }.start();

    }
}
