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
                open2Player();
            }
        });
    }

    public void open2Player(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
