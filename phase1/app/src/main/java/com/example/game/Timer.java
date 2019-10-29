package com.example.game;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Timer {
    private long totalTimeInMilliSeconds;
    private long timeLeftInMilliSeconds;
    private long timeElapsedInMilliSeconds;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning;
    private Button startButton;
    private TextView countDownTextView;


    public Timer(long durationInMilliSeconds, Button but, TextView text){
        totalTimeInMilliSeconds = durationInMilliSeconds;
        timeLeftInMilliSeconds = durationInMilliSeconds;
        timeElapsedInMilliSeconds = 0;
        startButton = but;
        countDownTextView = text;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    public long getTimeLeft(){
        return timeLeftInMilliSeconds;
    }

    public long getTimeElapsed(){
        return timeElapsedInMilliSeconds;
    }

    public void setTotalTime(long duration){
        totalTimeInMilliSeconds = duration;
    }

    public boolean getIsTimerRunning(){
        return isTimerRunning;
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliSeconds = l;
                countDownTextView.setText((int) timeLeftInMilliSeconds/1000 + "s");
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                startButton.setText("Start");
                startButton.setVisibility(View.INVISIBLE);

            }
        }.start();
        isTimerRunning = true;
        startButton.setText("Pause");
        timeElapsedInMilliSeconds ++;
    }

    public void pauseTimer(){
        countDownTimer.cancel();
        isTimerRunning = false;
        startButton.setText("Start");
    }

    /// If we want to format the time, can decide this later
    /*private void updateCountDownText(){
        int minutes = (int) (timeLeft / 1000) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;

        String formatedTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countDownTextView.setText(formatedTime);
    }*/
}
