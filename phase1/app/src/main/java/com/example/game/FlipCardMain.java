package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class FlipCardMain extends AppCompatActivity {

    public void mainBack(View view) {
        Intent mainActivityIntent = new Intent(this, FlipCardInit.class);
        startActivity(mainActivityIntent);
    }

    public void toResult(View view) {
        Intent mainActivityIntent = new Intent(this, FlipCardResult.class);
        startActivity(mainActivityIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card);
        //
//        Button myButton = new Button(this);
//        myButton.setText("Push Me");
//        LinearLayout ll = (LinearLayout)findViewById(R.id.rootlinearlayout);
//        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//        ll.addView(myButton, lp);
    }
}
