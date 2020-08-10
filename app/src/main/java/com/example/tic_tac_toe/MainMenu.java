package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button btn2player = (Button) findViewById(R.id.btn2Player);
        Button btn1player = (Button) findViewById(R.id.btn1Player);

        btn2player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNames();
            }
        });

        btn1player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDifficulty();
            }
        });
    }

    public void openNames(){
        Intent intent = new Intent(this, EnterNames.class);
        startActivity(intent);
    }

    public void openDifficulty(){
        Intent intent = new Intent(this, DifficultySelector.class);
        startActivity(intent);
    }
}
