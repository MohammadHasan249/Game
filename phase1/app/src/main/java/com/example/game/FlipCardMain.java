package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Button;

public class FlipCardMain extends AppCompatActivity {
    ArrayList<FlipCards> cardsList;
    String difficulty;
    int numMatches = 0;
    //waiting for henry to give me the color instance so i will be setting it here for now
    int cardBackColor = Color.RED;
    public void mainBack(View view) {
        Intent mainActivityIntent = new Intent(this, FlipCardInit.class);
        startActivity(mainActivityIntent);
    }

    public void toResult(View view) {
        Intent mainActivityIntent = new Intent(this, FlipCardResult.class);
        startActivity(mainActivityIntent);
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
    private ArrayList<Character> symbolGenerater(int numMatches)
    {
        Random rand = new Random();
        ArrayList<Character> charList = new ArrayList<>();
        for (int i = 0; i < numMatches; i ++)
        {
            //want to generate a random character to use, since my max number of matches is 20
            // I have no problem going through the entire alphabet
            char c = (char)(rand.nextInt(26) + 'a');
            if (charList.contains(c))
            {
                for (int j = 0; j < numMatches; j ++)
                {
                    char newchar = (char)(rand.nextInt(26) + 'a');
                    if (!charList.contains(newchar))
                    {
                        charList.add(newchar);
                        charList.add(newchar);
                        break;
                    }
                }
            }
            else
            {
                charList.add(c);
                charList.add(c);
            }
        }
        Collections.shuffle(charList);
        return charList;
    }
    private void generateCards(int numMatches)
    {
        //only want 5 cards in each row
        int numrows = numMatches/5;
        TableLayout stk = (TableLayout) findViewById(R.id.tableLayoutFlipCard);
        TableRow tbrow0 = new TableRow(this);
        FlipCards f1 = new FlipCards(this, cardBackColor, "a", tbrow0);
        f1.getBtnInstance().setOnClickListener(handleOnClick(f1.getBtnInstance()));
        stk.addView(tbrow0);
    }
    //setting on click
    View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                button.setText("helllllllooooooo");
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card);
        Bundle receiver = getIntent().getExtras();
        if (receiver != null)
        {
            this.difficulty = receiver.getString("Level");
            this.setDiffculity(this.difficulty);
            this.generateCards(this.numMatches);
        }
    }
    }

