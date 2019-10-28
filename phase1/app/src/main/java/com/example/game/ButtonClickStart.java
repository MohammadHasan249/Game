package com.example.game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonClickStart extends AppCompatActivity {

    public void beginGame(View view) {
        String time;
        RadioButton t1 =findViewById(R.id.rb_30);
        RadioButton t2 =findViewById(R.id.rb_45);
        if (t1.isChecked())
            time = "30";
        else if (t2.isChecked())
            time = "45";
        else
            time = "60";
        String score;
        RadioButton s1 = findViewById(R.id.sm_1x);
        RadioButton s2 = findViewById(R.id.sm_1_5x);
        if (s1.isChecked())
            score = "1";
        else if (s2.isChecked())
            score = "1.5";
        else
            score = "2";
        int c;
        RadioButton c1 = findViewById(R.id.colour_red);
        RadioButton c2 = findViewById(R.id.colour_green);
        if (c1.isChecked())
            c = Color.RED;
        else if (c2.isChecked())
            c = Color.GREEN;
        else
            c = Color.BLUE;
        Intent beginGame = new Intent(this, ButtonClickMain.class);
        beginGame.putExtra("Time", time);
        beginGame.putExtra("Score", score);
        beginGame.putExtra("Color", c);
        startActivity(beginGame);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_start);
    }
}
