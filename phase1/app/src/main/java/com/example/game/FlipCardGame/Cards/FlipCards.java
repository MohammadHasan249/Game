package com.example.game.FlipCardGame.Cards;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

import com.example.game.FlipCardGame.FlipCardModels.FlipCardGameModel;

public class FlipCards {
  private Button btnInstance;
  private String symbol;
  private boolean flipped;
  private Drawable fullColor;
  private boolean enabled;
  private FlipCardGameModel manager;
  public static boolean disableCards = false;

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

  public boolean isFlipped() {
    return this.flipped;
  }

  // flip the card
  public void flipCard() {
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

  public String getSymbol() {
    return this.symbol;
  }
  // disables the card and prevents it from being clicked again
  public void lockCard() {
    this.btnInstance.setEnabled(false);
    this.enabled = false;
    this.flipped = false;
  }

  public void resetState() {
    this.enabled = true;
    this.flipped = false;
    this.turnCardToBack();
  }

  public void setManager(FlipCardGameModel manager) {
    this.manager = manager;
  }

  public void disableBtnCall() {
    this.btnInstance.setEnabled(true);
    this.btnInstance.setOnClickListener(null);
  }
  private void turnCardToBack() {
        this.btnInstance.setText("");
        btnInstance.setBackground(fullColor);
    }

  public void callManagerUpdate() {
    manager.update(this);
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
