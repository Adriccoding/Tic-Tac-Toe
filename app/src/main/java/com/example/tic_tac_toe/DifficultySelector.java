package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DifficultySelector extends AppCompatActivity {

    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selector);

        name = (EditText) findViewById(R.id.name);
        Button btnNormal = (Button) findViewById(R.id.btnNormal);
        Button btnHard = (Button) findViewById(R.id.btnHard);
        Button btnImpossible = (Button) findViewById(R.id.btnImpossible);

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNormal();
            }
        });
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHard();
            }
        });
        btnImpossible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchImpossible();
            }
        });
    }

    private void launchNormal(){
        Intent intent = new Intent(this, ComputerNormal.class);
        String playerName = name.getText().toString();
        intent.putExtra("name_key", playerName);
        startActivity(intent);
    }

    private void launchHard(){
        Intent intent = new Intent(this, ComputerHard.class);
        String playerName = name.getText().toString();
        intent.putExtra("name_key", playerName);
        startActivity(intent);
    }
    private void launchImpossible(){
        Intent intent = new Intent(this, ComputerHard.class);
        String playerName = name.getText().toString();
        intent.putExtra("name_key", playerName);
        startActivity(intent);
    }
}
