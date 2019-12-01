package com.example.game.MathGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.game.CurrUser;
import com.example.game.HomePage;
import com.example.game.LevelOnCreate;
import com.example.game.R;



import java.util.ArrayList;
import java.util.Random;

public class MathGame extends AppCompatActivity {

    Button btnReduce1, btnReduce2, btnReduce3, btnReduce4, btnReset;
    TextView textTimeDisplay, textScore, textValue;
    ArrayList<Integer> fourNum;
    ArrayList<Button> fourBtn;
    Random rand;
    int fourNumBound, numOfAdditions, numOfAdditionBounds, startValue, startValueHolder, randomIndex, time_ms, intHolder;
    boolean timer_set;
    CountDownTimer timer;
    String instructions;
    LevelOnCreate levelOnCreate;

    CurrUser user;

    // End result stats variables
    int numCorrect, numFailedAttempts, timeHolder;

    void generateFourNumArray(int bound) {
        fourNum = new ArrayList<>();
        for (int i = 0; i < 4; i++) {

            intHolder = rand.nextInt(bound) + 3;
            while (fourNum.contains(intHolder)) {
                intHolder = rand.nextInt(bound) + 3;
            }

            fourNum.add(intHolder);     //Random int from 3 to bound + 2
            // ensure won't generate 0,1,2 (these make the game too easy)
        }
    }

    void generateStartValue(int bound) {
        startValue = 0;
        randomIndex = rand.nextInt(4);  // 0 - 3
        numOfAdditions = rand.nextInt(bound) + 2; // Random int from 2 to bounds + 1
        for (int k = 0; k < numOfAdditions; k++) {
            startValue += fourNum.get(randomIndex);
            randomIndex = rand.nextInt(4);
        }
        startValueHolder = startValue;       //Holder will not constantly change like the regular startValue
    }

    void setUpReduceBtns() {
        for (int k = 0; k < fourBtn.size(); k++) {
            String strFourBtn = "-" + fourNum.get(k).toString();
            fourBtn.get(k).setTag((fourNum.get(k)));
            fourBtn.get(k).setText(strFourBtn);
        }
    }

    public void btnReduceMethod(View view) {
        startValue -= (Integer) view.getTag();
        String strTextValueLocal = Integer.toString(startValue);
        textValue.setText(strTextValueLocal);
        if (startValue == 0) {
            numCorrect += 1;
            updateScore();
            reset();

        } else if (startValue < 0) {
            numFailedAttempts += 1;
            updateScore();
            reset();
        }
    }

    public void btnReset(View view) {
        reset();
    }

    void reset() {

        // Put 4 random numbers into fourNum

        if (user.getDifficultySelected().equals("easy")) {
            fourNumBound = 8;
        } else {
            fourNumBound = 16;
        }

        generateFourNumArray(fourNumBound);

        // Generate start value
        numOfAdditionBounds = 8;
        generateStartValue(numOfAdditionBounds);

        setUpReduceBtns();

        // Setup displays
        String strTextValue = Integer.toString(startValue);
        textValue.setText(strTextValue);
    }

    void goMathGameResult() {
        Intent goResultScreen = new Intent(getApplicationContext(), MathGameResult.class);
        goResultScreen.putExtra("numCorrect", numCorrect);
        goResultScreen.putExtra("numFailedAttempts", numFailedAttempts);
        goResultScreen.putExtra("time", timeHolder);
        startActivity(goResultScreen);
    }

    public void start_time() {
        time_ms = 45000;    // Sets timer to go for 30s
        // Only need to change time in one place for if later create classes
        timeHolder = time_ms / 1000;
        if (timer_set) {
            // This so later if want to add function to restart game in the middle of a game
            // Then need this to cancel previous timer
            timer.cancel();
        }
        timer = new CountDownTimer(time_ms, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String strTimeDisplay = (int) millisUntilFinished / 1000 + "s";
                textTimeDisplay.setText(strTimeDisplay);
            }

            @Override
            public void onFinish() {
                // So far have made intent to go to result screen
                user.stopMusic();
                goMathGameResult();
                finish();
                // Should also save data to SQL from here
            }
        };
        timer_set = true;
//        timer.start();
    }

    void updateScore() {
        String strScore = (numCorrect) + " | " + (numFailedAttempts);
        textScore.setText(strScore);
    }

    private void changeBtnColor() {
        int k;
        for (k = 0; k < fourBtn.size(); k++) {
            fourBtn.get(k).setBackgroundColor(user.getColorSelected());
        }

        btnReset.setBackgroundColor(user.getColorSelected());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);


        user = new CurrUser(this);
        user.playMusic();
        user.setCurrLevel(2);

        btnReduce1 = findViewById(R.id.btnReduce1);
        btnReduce2 = findViewById(R.id.btnReduce2);
        btnReduce3 = findViewById(R.id.btnReduce3);
        btnReduce4 = findViewById(R.id.btnReduce4);
        btnReset = findViewById(R.id.btnReset);

        fourBtn = new ArrayList<>();
        fourBtn.add(btnReduce1);
        fourBtn.add(btnReduce2);
        fourBtn.add(btnReduce3);
        fourBtn.add(btnReduce4);

        textScore = findViewById(R.id.textScore);
        textValue = findViewById(R.id.textValue);
        textTimeDisplay = findViewById(R.id.textTimeDisplay);

        rand = new Random();
        timer_set = false;

        reset();
        start_time();

        numCorrect = 0;
        numFailedAttempts = 0;
        updateScore();

        changeBtnColor();

        instructions = "Decrease the displayed value to exactly 0 by clicking the 4 numbered squares";
        levelOnCreate = new LevelOnCreate(this, instructions, timer);
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        user.stopMusic();
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        start.putExtra("androidBack", 1);
        startActivity(start);
        finish();
    }
}
