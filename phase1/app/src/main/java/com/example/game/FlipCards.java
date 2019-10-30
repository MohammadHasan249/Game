package com.example.game;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.graphics.drawable.GradientDrawable;

class FlipCards {
    private Button btnInstance;
    private String symbol;
    private boolean flipped;
    private Drawable fullColor;
    private boolean enabled;
    private FlipCardGameManager manager;
    static boolean disableCards = false;

    FlipCards(Context packageContext, int cardBackColor, String symbol, TableRow row, int btnHeight, int btnWidth, FlipCardGameManager manager)
    {
        this.flipped = false;
        btnInstance = new Button(packageContext);
        this.symbol = symbol;
        row.addView(btnInstance);
        this.btnInstance.setOnClickListener(handleOnClick(this.btnInstance));
        this.btnInstance.setWidth(btnWidth);
        this.btnInstance.setHeight(btnHeight);
        this.fullColor = this.setBoarderColor(this.btnInstance, cardBackColor, btnWidth);
        this.enabled = true;
        this.manager = manager;
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

    boolean isFlipped()
    {
        return this.flipped;
    }

    void flipCard()
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
                btnInstance.setBackground(fullColor);
                flipped = !flipped;
            }
        }
    }

    String getSymbol()
    {
        return this.symbol;
    }

    void lockCard()
    {
        this.btnInstance.setEnabled(false);
        this.enabled = false;
        this.flipped= false;
    }

    //setting on click
    private View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                if (!disableCards) {
                    flipCard();
                    manager.update();
                }
            }
        };
    }
}
