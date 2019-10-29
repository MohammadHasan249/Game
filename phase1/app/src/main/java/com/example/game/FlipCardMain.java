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
import android.view.Gravity;
public class FlipCardMain extends AppCompatActivity {
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
            numMatches = 15;
        else if (difficulty.equals("hard"))
            numMatches = 20;
        else
            numMatches = 25;
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


    //this is broken
    private void generateCards(int numMatches)
    {
        ArrayList<Character> charList = this.symbolGenerater(numMatches);
        TableLayout stk = findViewById(R.id.tableLayoutFlipCard);
        int i = 0;
        TableRow tbrow = new TableRow(this);
        tbrow.setGravity(Gravity.CENTER_HORIZONTAL);
        tbrow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        for (Character s : charList)
        {
            if (i == 5)
            {
                stk.addView(tbrow);
                tbrow = new TableRow(this);
                tbrow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                tbrow.setGravity(Gravity.CENTER_HORIZONTAL);
                i = 0;
            }
            FlipCards f1 = new FlipCards(this, cardBackColor, s.toString(), tbrow, 5, 3);
            FlipCards.allcards.add(f1);
            i++;
        }
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

