package com.example.game;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
//    Timer timer;

    // End result stats variables
    int numCorrect, numFailedAttempts, timeHolder;

    void generateFourNumArray (int bound){
        fourNum = new ArrayList<Integer>();
        for (int i = 0; i < 4; i ++){

            intHolder = rand.nextInt(bound) + 3;
            while (fourNum.contains(intHolder)){
                intHolder = rand.nextInt(bound) + 3;
            }

            fourNum.add(intHolder);     //Random int from 3 to bound + 2
            // ensure won't generate 0,1,2 (these make the game too easy)
        }
    }

    void generateStartValue(int bound){
        startValue = 0;
        randomIndex = rand.nextInt(4);  // 0 - 3
        numOfAdditions = rand.nextInt(bound) + 2; // Random int from 2 to bounds + 1
        for (int k = 0; k < numOfAdditions; k++){
            startValue += fourNum.get(randomIndex);
            randomIndex = rand.nextInt(4);
        }
        startValueHolder = startValue;       //Holder will not constantly change like the regular startValue
    }

    void setUpReduceBtns(){
        for (int k=0; k < fourBtn.size(); k++){
            fourBtn.get(k).setTag(Integer.valueOf(fourNum.get(k)));
            fourBtn.get(k).setText("-" + fourNum.get(k).toString());
        }
    }

    public void btnReduceMethod(View view) {
        System.out.println("LOCATION 0 MARK");
        startValue -= (Integer) view.getTag();
        System.out.println("LOCATION 1 MARK");
        textValue.setText(Integer.toString(startValue));
        System.out.println("LOCATION 2 MARK");
        if (startValue == 0){
            numCorrect += 1;
            updateScore();
            reset();

        }else if (startValue < 0){
            numFailedAttempts += 1;
            updateScore();
            reset();
        }
    }

    public void btnReset(View view){
        reset();
    }

    void reset(){

        // Put 4 random numbers into fourNum
        fourNumBound = 8;
        generateFourNumArray(fourNumBound);

        // Generate start value
        numOfAdditionBounds = 8;
        generateStartValue(numOfAdditionBounds);



        setUpReduceBtns();

        // Setup displays
        textValue.setText(Integer.toString(startValue));
    }

    void goMathGameResult(){
        Intent goResultScreen = new Intent(getApplicationContext(), MathGameResult.class);
        goResultScreen.putExtra("numCorrect", numCorrect);
        goResultScreen.putExtra("numFailedAttempts", numFailedAttempts);
        goResultScreen.putExtra("time", timeHolder);
        startActivity(goResultScreen);
    }

    public void start_time (){
        time_ms = 45000;    // Sets timer to go for 30s
        // Only need to change time in one place for if later create classes
        timeHolder = time_ms / 1000;
        if (timer_set){
            // This so later if want to add function to restart game in the middle of a game
            // Then need this to cancel previous timer
            timer.cancel();
        }
        timer = new CountDownTimer(time_ms, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                textTimeDisplay.setText( (int) millisUntilFinished/1000 + "s");
            }
            @Override
            public void onFinish() {
                // So far have made intent to go to result screen
                goMathGameResult();

                // Should also save data to SQL from here
            }
        };
        timer_set = true;
        timer.start();
    }
    void updateScore(){
        textScore.setText(Integer.toString(numCorrect) + " | " + Integer.toString(numFailedAttempts));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        btnReduce1 = findViewById(R.id.btnReduce1);
        btnReduce2 = findViewById(R.id.btnReduce2);
        btnReduce3 = findViewById(R.id.btnReduce3);
        btnReduce4 = findViewById(R.id.btnReduce4);
        btnReset = findViewById(R.id.btnReset);

        fourBtn = new ArrayList<Button>();
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
    }
}
