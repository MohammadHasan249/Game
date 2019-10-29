package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class ButtonClickMain extends AppCompatActivity {

    private Random r = new Random();
    private int start_time;
    private Button mainButton;
    private TextView countDownText;

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static Point getDisplaySize(Context context) {
        Point point = new Point();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getSize(point);
        return point;
    }

    void goButtonClickResult(View view) {
        Intent goResult = new Intent(getApplicationContext(), ButtonClickResult.class);
        startActivity(goResult);
    }
    public void setButtonRandomPosition(Button button){
        int randomX = r.nextInt(getDisplaySize(this).x);
        int randomY = r.nextInt(getDisplaySize(this).y);
        button.setX(randomX);
        button.setY(randomY);
    }

    private void startRandomButton(Button button) {
       Timer t = new Timer(start_time * 100, mainButton, countDownText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_main);
        Bundle bundle = getIntent().getExtras();
        start_time = (int) bundle.get("Time");
    }
}
