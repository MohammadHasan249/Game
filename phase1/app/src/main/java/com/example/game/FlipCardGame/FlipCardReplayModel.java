package com.example.game.FlipCardGame;

import android.os.CountDownTimer;

import java.util.ArrayList;

class FlipCardReplayModel extends FlipCardGameModel {
    private ArrayList<FlipCards> cardActionList;
    private ArrayList<Long> timeActionList;

    FlipCardReplayModel(ArrayList<FlipCards> cardActionList,
                        ArrayList<Long> timeActionList, ArrayList<FlipCards> lastStateList, FlipCardMainPresenter presenter) {
        this.cardActionList = cardActionList;
        this.timeActionList = timeActionList;
        this.allCards = lastStateList;
        this.presenter = presenter;
    }

    void startDisplay() {
        this.presenter.updateScore(0, 0);
        this.restoreStartState();
        this.presenter.startTimer();
        this.replay(this.timeActionList.get(0));
    }

    private void replay(final long delay) {
        new CountDownTimer(delay, 1) {
            public void onFinish() {
                if (cardActionList.size() > 1) {
                    cardActionList.get(0).callManagerUpdate();
                    long delayTime = timeActionList.get(1) - timeActionList.get(0);
                    cardActionList.remove(0);
                    timeActionList.remove(0);
                    replay(delayTime);
                } else if (cardActionList.size() == 1) {
                    cardActionList.get(0).callManagerUpdate();
                    cardActionList.remove(0);
                    timeActionList.remove(0);
                    presenter.stopTimer();
                }
            }

            public void onTick(long millisUntilFinished) {
            }
        }.start();
    }

    private void restoreStartState() {
        for (FlipCards f : this.allCards) {
            f.resetState();
            f.disableBtnCall();
            f.setManager(this);
        }
    }

    @Override
    void update(FlipCards cardCalled) {
        if (!cardCalled.isFlipped())
            cardCalled.flipCard();
        this.updateCards();
    }
}
