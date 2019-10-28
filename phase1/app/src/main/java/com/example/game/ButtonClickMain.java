package com.example.game;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class ButtonClickMain extends AppCompatActivity {

    private ArrayList<Button> buttons;
    private Random r = new Random();

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public void setButtons(int n) {
        for (int i = 0; i < n; i++) {
            Button b = new Button(this);
            b.setX(r.nextInt(getScreenWidth()));
            b.setY(r.nextInt(getScreenHeight()));
            buttons.add(b);
        }
    }

    void goButtonClickResult(View view) {
        Intent goResult = new Intent(getApplicationContext(), ButtonClickResult.class);
        startActivity(goResult);
    }
    public void setButtonRandomPosition(View view){
        int randomX = new Random().nextInt(getScreenWidth());
        int randomY = new Random().nextInt(getScreenHeight());
        view.setX(randomX);
        view.setY(randomY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_main);
    }
}
