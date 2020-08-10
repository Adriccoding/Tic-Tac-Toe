package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterNames extends AppCompatActivity {

    EditText name1Text;
    EditText name2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_names);

        Button accept = (Button) findViewById(R.id.btnAccept);
        name1Text = (EditText) findViewById(R.id.player1Name);
        name2Text = (EditText) findViewById(R.id.player2Name);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open2player();
            }
        });
    }

    private void open2player(){
        Intent intent = new Intent(this, MainActivity.class);
        String name1 = name1Text.getText().toString();
        String name2 = name2Text.getText().toString();
        intent.putExtra("name1_key", name1);
        intent.putExtra("name2_key", name2);
        startActivity(intent);
    }
}
