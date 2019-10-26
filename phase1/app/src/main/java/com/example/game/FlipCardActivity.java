package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FlipCardActivity extends AppCompatActivity {
    public void mainBack(View view) {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
    public void test(View view)
    {

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
