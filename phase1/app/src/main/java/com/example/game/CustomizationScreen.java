package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CustomizationScreen extends AppCompatActivity {

    RadioGroup radioGroupColor, radioGroupDifficulty, radioGroupSoundtrack;
    RadioButton radioCheckedColor, radioCheckedDifficulty, radioCheckedSoundtrack;
    RadioButton radioNoSoundtrack, radioSoundtrack1, radioSoundtrack2;
    CurrUser user;
    MediaPlayer currMedia;

    public void btnStartFunc(View view){

        radioCheckedColor = findViewById(radioGroupColor.getCheckedRadioButtonId());
        radioCheckedDifficulty = findViewById(radioGroupDifficulty.getCheckedRadioButtonId());
        radioCheckedSoundtrack = findViewById(radioGroupSoundtrack.getCheckedRadioButtonId());

        user.setColorSelected(Integer.parseInt(radioCheckedColor.getTag().toString()));
        user.setDifficultySelected(radioCheckedDifficulty.getTag().toString());
        user.setMusicSelected(Integer.parseInt(radioCheckedSoundtrack.getTag().toString()));
        user.setCurrLevel(1);

        System.out.println("MARKER 0");
        if (currMedia != null){
            System.out.println("MARKER 1");
            currMedia.stop();

        }
        if (user.getMusicSelected() != 0){
            System.out.println("MARKER 2");
            currMedia = MediaPlayer.create(this, user.getMusicSelected());
            currMedia.start();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization_screen);

        radioGroupColor = findViewById(R.id.radioGroupColor);
        radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty);
        radioGroupSoundtrack = findViewById(R.id.radioGroupSoundtrack);

        radioSoundtrack1 = findViewById(R.id.radioSoundtrack1);
        radioSoundtrack2 = findViewById(R.id.radioSoundtrack2);
        radioNoSoundtrack = findViewById(R.id.radioNoSountrack);

        radioNoSoundtrack.setTag(0);
        radioSoundtrack1.setTag(R.raw.soundtrack1);
        radioSoundtrack2.setTag(R.raw.soundtrack2);

        user = new CurrUser(this);








    }
}
