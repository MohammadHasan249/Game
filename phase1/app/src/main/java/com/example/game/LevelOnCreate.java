package com.example.game;

import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;

import androidx.appcompat.app.AlertDialog;

public class LevelOnCreate {

    public LevelOnCreate(Context context, String instructions) {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Level Instructions")
                .setMessage(instructions)
                .setPositiveButton("OK", null)
                .show();
    }

    public LevelOnCreate(Context context, String instructions, final CountDownTimer t) {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Level Instructions")
                .setMessage(instructions)
                .setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                t.start();
                            }
                        })
                .show();
    }
}
