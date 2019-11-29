package com.example.game.FlipCardGame;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

class FlipCards {
  private Button btnInstance;
  private String symbol;
  private boolean flipped;
  private Drawable fullColor;
  private boolean enabled;
  private FlipCardGameModel manager;
  static boolean disableCards = false;

  FlipCards(
          Context packageContext,
          int cardBackColor,
          String symbol,
          TableRow row,
          int btnHeight,
          int btnWidth,
          FlipCardGameModel manager) {
      this.build(packageContext, cardBackColor, symbol, row, btnHeight, btnWidth, manager);
  }

    private void build(
            Context packageContext,
            int cardBackColor,
            String symbol,
            TableRow row,
            int btnHeight,
            int btnWidth,
            FlipCardGameModel manager)
  {
    this.flipped = false;
    btnInstance = new Button(packageContext);
    this.symbol = symbol;
    row.addView(btnInstance);
    this.btnInstance.setOnClickListener(handleOnClick(this.btnInstance));
    this.btnInstance.setWidth(btnWidth);
    this.btnInstance.setHeight(btnHeight);
    this.fullColor = this.initBoarderColor(this.btnInstance, cardBackColor, btnWidth);
    this.enabled = true;
    this.manager = manager;
  }
  // setting the color with the boarder of the card
  private Drawable initBoarderColor(Button btnInstance, int cardBackColor, int btnWidth) {
    GradientDrawable drawable = new GradientDrawable();
    drawable.setShape(GradientDrawable.RECTANGLE);
    drawable.setStroke(btnWidth, Color.BLACK);
    drawable.setColor(cardBackColor);
    btnInstance.setBackground(drawable);
    return drawable;
  }

  boolean isFlipped() {
    return this.flipped;
  }

  // flip the card
  void flipCard() {
    if (enabled) {
      if (!flipped) {
        this.btnInstance.setText(symbol);
        btnInstance.setBackgroundColor(Color.WHITE);
        flipped = !flipped;
      } else {
          this.turnCardToBack();
        flipped = !flipped;
      }
    }
  }

  String getSymbol() {
    return this.symbol;
  }

  // disables the card and prevents it from being clicked again
  void lockCard() {
    this.btnInstance.setEnabled(false);
    this.enabled = false;
    this.flipped = false;
  }

    void resetCard() {
        this.btnInstance.setEnabled(true);
        this.enabled = true;
        this.flipped = true;
        this.turnCardToBack();
    }

    private void turnCardToBack() {
        this.btnInstance.setText("");
        btnInstance.setBackground(fullColor);
    }
  private void callManagerUpdate() {
    manager.update(this);
  }

  void removeBtnInstance() {
    this.btnInstance = null;
  }

  // when the card is clicked, it flips then call the update on the observer(FlipCardMainGameModel)
  private View.OnClickListener handleOnClick(final Button button) {
    return new View.OnClickListener() {
      public void onClick(View v) {
        if (!disableCards) {
          callManagerUpdate();
        }
      }
    };
  }
}
