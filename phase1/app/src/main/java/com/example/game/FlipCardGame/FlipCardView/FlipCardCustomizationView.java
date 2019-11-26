package com.example.game.FlipCardGame.FlipCardView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.example.game.FlipCardGame.FlipCardMainView;
import com.example.game.FlipCardGame.FlipCardPresenter.FlipCardCustomizationPresenter;
import com.example.game.R;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.content.Intent;

public class FlipCardCustomizationView extends AppCompatActivity implements FlipCardCustomizationPresenter.View {
    private Spinner customizationSpn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card_customization);
        this.customizationSpn = findViewById(R.id.spnPickSymbol);
        this.initializeScreenViaPresenter();
    }

    private void initializeScreenViaPresenter()
    {
        FlipCardCustomizationPresenter presenter = new FlipCardCustomizationPresenter(this);
        presenter.initializeScreen();
    }
    @Override
    public void addToSpinner(ArrayList<String> supportedSymbols) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, supportedSymbols);
        this.customizationSpn.setAdapter(adapter);
    }
    private String spinnerValueGetter()
    {
       return this.customizationSpn.getSelectedItem().toString();
    }
    public void btnEndCustomizations(View view) {
        Intent mainGameIntent = new Intent(this , FlipCardMainView.class);
        mainGameIntent.putExtra("symbolChoice", this.spinnerValueGetter());
        startActivity(mainGameIntent);
        finish();
    }
}
