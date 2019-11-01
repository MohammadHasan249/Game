package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ButtonClickMain extends AppCompatActivity {

    //-------Variable Declarations------
    private Button[][] buttons = new Button[5][4];
    Random r = new Random();
    CountDownTimer timer;
    CurrUser user;
    int diff_time;
    static int numClicks = 0;
    static int score = 0;
    TextView scoreTxt;
    //----------------------------------

    public void goButtonClickResult() {
        user.stopMusic();
        Intent goResult = new Intent(this, ButtonClickResult.class);
        goResult.putExtra("Total Clicks", numClicks);
        goResult.putExtra("Score", score);
        startActivity(goResult);
        finish();
    }

    private void buttonVisVisible(Button[][] buttons){
        //Selects a random button to make visible.
        int testR = r.nextInt(5);
        int testC = r.nextInt(4);
        buttons[testR][testC].setVisibility(View.VISIBLE);
    }

    private View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                if (button.getVisibility() == View.VISIBLE) {
                    numClicks += 1;
                    score += 1;
                }
                scoreTxt.setText(score + " | " + numClicks);
            }
        };
    }

    private View.OnClickListener handleIncorrect() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClicks += 1;
                scoreTxt.setText(score + " | " + numClicks);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_main);
        scoreTxt = findViewById(R.id.scoreBtnClick);
        final TextView countDownTxt = findViewById(R.id.countDownText);
        TableLayout tableLayout = findViewById(R.id.tableLayoutBtns);
        tableLayout.setOnClickListener(handleIncorrect());

        //--------User Creation and Parsing---------
        user = new CurrUser(this);
        user.setCurrLevel(1);
        user.playMusic();

        if(user.getDifficultySelected().equals("hard")){
            diff_time = 333;
        }
        else{
            diff_time = 1000;
        }

        //----Creation of Button Grid---------
        for (int r = 0; r < 5; r++) {
            TableRow currentRow = new TableRow(this);
            for (int c = 0; c < 4; c++) {
                Button currentButton = new Button(this);
                currentButton.setOnClickListener(handleOnClick(currentButton));
                currentButton.setBackgroundColor(user.getColorSelected()); //Colour Customization
                buttons[r][c] = currentButton;
                currentRow.addView(currentButton);
            }
            tableLayout.addView(currentRow);
        }


        //---- Creation of CountDownTimer/Difficulty Customization-----
        diff_time = 1000;
    timer =
        new CountDownTimer(60000, diff_time) {
          @Override
          public void onTick(long millisUntilFinished) {
            // Set every button to be invisible
            for (int r = 0; r < 5; r++) {
              for (int c = 0; c < 4; c++) {
                buttons[r][c].setVisibility(View.INVISIBLE);
              }
            }
            // Set a random button to visible
            buttonVisVisible(buttons);
            countDownTxt.setText((int) millisUntilFinished / 1000 + "s");
          }

          public void onFinish() {
            // So far have made intent to go to result screen
            goButtonClickResult();
          }
        }.start();
    }
}
