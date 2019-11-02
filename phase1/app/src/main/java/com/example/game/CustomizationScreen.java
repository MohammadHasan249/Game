package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CustomizationScreen extends AppCompatActivity {

    RadioGroup radioGroupColor, radioGroupDifficulty, radioGroupSoundtrack;
    RadioButton radioCheckedColor, radioCheckedDifficulty, radioCheckedSoundtrack;
    RadioButton radioRed, radioBlue, radioGreen;
    RadioButton radioNoSoundtrack, radioSoundtrack1, radioSoundtrack2;
    CurrUser user;

    public void btnStartFunc(View view) {

        radioCheckedColor = findViewById(radioGroupColor.getCheckedRadioButtonId());
        radioCheckedDifficulty = findViewById(radioGroupDifficulty.getCheckedRadioButtonId());
        radioCheckedSoundtrack = findViewById(radioGroupSoundtrack.getCheckedRadioButtonId());

        user.setColorSelected(Integer.parseInt(radioCheckedColor.getTag().toString()));
        user.setDifficultySelected(radioCheckedDifficulty.getTag().toString());
        user.setMusicSelected(Integer.parseInt(radioCheckedSoundtrack.getTag().toString()));

        user.setCurrLevel(1);

        Intent goLevel1 = new Intent(this, ButtonClickMain.class);
        startActivity(goLevel1);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization_screen);

        radioGroupColor = findViewById(R.id.radioGroupColor);
        radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty);
        radioGroupSoundtrack = findViewById(R.id.radioGroupSoundtrack);

        radioRed = findViewById(R.id.radioRed);
        radioBlue = findViewById(R.id.radioBlue);
        radioGreen = findViewById(R.id.radioGreen);

        radioRed.setTag(Color.RED);

        int lightBlue = Color.parseColor("#4290f5");
        radioBlue.setTag(lightBlue);

        radioGreen.setTag(Color.GREEN);

        radioSoundtrack1 = findViewById(R.id.radioSoundtrack1);
        radioSoundtrack2 = findViewById(R.id.radioSoundtrack2);
        radioNoSoundtrack = findViewById(R.id.radioNoSountrack);

        radioNoSoundtrack.setTag(0);
        radioSoundtrack1.setTag(R.raw.soundtrack1);
        radioSoundtrack2.setTag(R.raw.soundtrack2);

        user = new CurrUser(this);
    }
}
