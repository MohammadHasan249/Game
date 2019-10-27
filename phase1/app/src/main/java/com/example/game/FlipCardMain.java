package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
public class FlipCardMain extends AppCompatActivity {
    String diffculity;
    int numMatches = 0;
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
        Bundle receiver = getIntent().getExtras();
        if (receiver != null)
        {
            diffculity = receiver.getString("Level");
            //this.setDiffculity(diffculity);
            //this.generateCards(this.numMatches);
        }

    }
    private void setDiffculity(String difficulty)
    {
        if (difficulty.equals("easy"))
            numMatches = 5;
        else if (difficulty.equals("hard"))
            numMatches = 10;
        else
            numMatches = 20;
    }
//    private Character[] symbolGenerater(int numMatches)
//    {
//        ArrayList<Character> charList = new ArrayList<Character>();
//
//    }
//    private void generateCards(int numMatches)
//    {
//        //only want 5 cards in each row
//        int numrows = numMatches/5;
//        for (int i = 0; i < numrows; i ++)
//        {
//            // want 5 per row
//            for (int j = 0; j < 5; j ++)
//            {
//
//            }
//        }
//    }
    //        Button myButton = new Button(this);
//        myButton.setText("Push Me");
//        LinearLayout ll = (LinearLayout)findViewById(R.id.rootlinearlayout);
//        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//        ll.addView(myButton, lp);

    }

