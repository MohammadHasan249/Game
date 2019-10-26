package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class FlipCardInit extends AppCompatActivity {
    public void startGame(View view) {
        Integer level = 2;
//        boolean checked = ((RadioButton) view).isChecked();
//        switch (view.getId())
//        {
//            case R.id.rdEasy:
//                if (checked)
//                    level = 0;
//                break;
//            case R.id.rdHard:
//                if (checked)
//                    level = 1;
//                break;
//            case R.id.rdInsane:
//                if (checked)
//                    level = 2;
//                break;
//        }
        Intent startGame = new Intent(getApplicationContext(), FlipCardMain.class);
        startGame.putExtra("Level Selection", level);
        startActivity(startGame);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card_init);
    }

}
