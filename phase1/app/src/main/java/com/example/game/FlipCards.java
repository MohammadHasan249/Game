package com.example.game;

import android.content.Intent;
import android.widget.Button;
import android.widget.TableRow;

public class FlipCards {
    private Button btnInstance;
    private String symbol;
    private boolean clicked;
    public FlipCards(FlipCardMain mainintent, int cardBackColor, String symbol, TableRow row)
    {
        this.clicked = false;
        btnInstance = new Button(mainintent);
        this.symbol = symbol;
        btnInstance.setBackgroundColor(cardBackColor);
        row.addView(btnInstance);
    }
    public Button getBtnInstance()
    {
        return this.btnInstance;
    }

}
