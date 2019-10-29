package com.example.game;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import java.util.ArrayList;
import android.graphics.drawable.GradientDrawable;

public class FlipCards {
    private Button btnInstance;
    private String symbol;
    private boolean flipped;
    private Drawable fullcolor;
    private boolean enabled;
    static int numCorrect;
    static int numclicked;
    static ArrayList<FlipCards> allcards = new ArrayList<>();
    FlipCards(FlipCardMain mainintent, int cardBackColor, String symbol, TableRow row, int btnHeight, int btnWidth)
    {
        this.flipped = false;
        btnInstance = new Button(mainintent);
        this.symbol = symbol;
        row.addView(btnInstance);
        this.btnInstance.setOnClickListener(handleOnClick(this.btnInstance));
        this.btnInstance.setWidth(btnWidth);
        this.btnInstance.setHeight(btnHeight);
        this.fullcolor = this.setBoarderColor(this.btnInstance, cardBackColor, btnWidth);
        this.enabled = true;
    }
    private Drawable setBoarderColor(Button btnInstance, int cardBackColor, int btnWidth)
    {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(btnWidth, Color.BLACK);
        drawable.setColor(cardBackColor);
        btnInstance.setBackground(drawable);
        return drawable;
    }
    public Button getBtnInstance()
    {
        return this.btnInstance;
    }
    public boolean isFlipped()
    {
        return this.flipped;
    }
    private void flipCard()
    {
        if (enabled)
        {
            if (!flipped)
            {
                this.btnInstance.setText(symbol);
                btnInstance.setBackgroundColor(Color.WHITE);
                flipped = !flipped;
            }
            else
            {
                this.btnInstance.setText("");
                btnInstance.setBackground(fullcolor);
                flipped = !flipped;
            }
        }
    }
    public String getSymbol()
    {
        return this.symbol;
    }
    public void lockCard()
    {
        this.btnInstance.setEnabled(false);
        this.enabled = false;
        this.flipped= false;
    }
    public static void updateCards()
    {
        ArrayList<FlipCards> flippedCardsList = new ArrayList<>();
        int i = 0;
        for (FlipCards f: FlipCards.allcards)
        {
            if (f.isFlipped())
            {
                flippedCardsList.add(f);
                i++;
            }
            if (i == 2)
            {
                FlipCards flippedCard1 = flippedCardsList.get(0);
                FlipCards flippedCard2 = flippedCardsList.get(1);
                if(flippedCard1.getSymbol().equals(flippedCard2.getSymbol()))
                {
                    flippedCard1.lockCard();
                    flippedCard2.lockCard();
                    FlipCards.numCorrect++;
                    FlipCards.numclicked++;
                }
                else {
                    flippedCard1.flipCard();
                    flippedCard2.flipCard();
                    FlipCards.numclicked++;
                }
                break;
            }
        }
    }
    //setting on click
    private View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                FlipCards.updateCards();
                flipCard();
            }
        };
    }
    // to disable the button once something is completed
    // FlipCards.allcards.get(0).btnInstance.setEnabled(false);

}
