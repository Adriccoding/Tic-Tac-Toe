package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean isX = true;
    private static final int[][] idArray = {{R.id.button00, R.id.button01, R.id.button02},
            {R.id.button10, R.id.button11, R.id.button12},
            {R.id.button20, R.id.button21, R.id.button22}};
    Button tiles[][] = new Button[3][3];
    private TextView tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonRestart = (Button) findViewById(R.id.buttonRestart);
        tracker = (TextView) findViewById(R.id.Tracker);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j] = (Button) findViewById(idArray[i][j]);
                tiles[i][j].setOnClickListener(write);
            }
        }
        buttonRestart.setOnClickListener(restart);
    }

    View.OnClickListener write = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            if (isX == true) {
                b.setText("X");
                isX = false;
                b.setEnabled(false);
                tracker.setText("O");
            } else {
                b.setText("O");
                isX = true;
                b.setEnabled(false);
                tracker.setText("X");
            }
        }
    };

    View.OnClickListener restart = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tiles[i][j].setText("");
                    tiles[i][j].setEnabled(true);
                }
            }
            isX = true;
            tracker.setText("X");
        }
    };
}
