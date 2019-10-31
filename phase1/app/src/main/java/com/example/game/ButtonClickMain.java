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

    //-------Variable Declarations------
    private Button[][] buttons = new Button[5][4];
    Random r = new Random();
    CountDownTimer timer;
    CurrUser user;
    int diff_time;
    //----------------------------------

    void goButtonClickResult(View view) {
        Intent goResult = new Intent(getApplicationContext(), ButtonClickResult.class);
        startActivity(goResult);
    }

    private void buttonVisVisible(Button[][] buttons){
        //Selects a random button to make visible.
        int testR = r.nextInt(5);
        int testC = r.nextInt(4);
        buttons[testR][testC].setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_main);
        TableLayout tableLayout = findViewById(R.id.tableLayoutBtns);
        //--------User Creation and Parsing---------
        user = new CurrUser(this);

        if(user.getDifficultySelected().equals("hard")){
            diff_time = 333;
        }
        else{
            diff_time = 1000;
        }
        
        //----Creation of Button Grid----------
        for (int r = 0; r < 5; r++) {
            TableRow currentRow = new TableRow(this);
            for (int c = 0; c < 4; c++) {
                Button currentButton = new Button(this);
                currentButton.setBackgroundColor(user.getColorSelected()); //Colour Customization
                buttons[r][c] = currentButton;
                currentRow.addView(currentButton);
            }
            tableLayout.addView(currentRow);
        }


        Bundle bundle = getIntent().getExtras(); //what is this


        //---- Creation of Timer/Difficulty Customization-----
        timer = new CountDownTimer(60000, diff_time){
            @Override
            public void onTick(long millisUntilFinished) {
                //Set every button to be invisible
                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 4; c++) {
                        buttons[r][c].setVisibility(View.INVISIBLE);
                    }
                }
                //Set a random button to visible
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
