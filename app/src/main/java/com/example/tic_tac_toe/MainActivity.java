package com.example.tic_tac_toe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Global variables
    boolean hasWon = false;
    int playerXWins = 0;
    int playerOWins = 0;
    boolean isX = true;
    String turnTracker = "X";
    private static final int[][] idArray = {{R.id.button00, R.id.button01, R.id.button02},
            {R.id.button10, R.id.button11, R.id.button12},
            {R.id.button20, R.id.button21, R.id.button22}};
    Button tiles[][] = new Button[3][3];
    private TextView tracker;
    private TextView XTracker;
    private TextView OTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonRestart = (Button) findViewById(R.id.buttonRestart);
        tracker = (TextView) findViewById(R.id.Tracker);
        XTracker = (TextView) findViewById(R.id.XwinCounter);
        OTracker = (TextView) findViewById(R.id.OwinCounter);
        tracker.setText(turnTracker);

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
                b.setText(turnTracker);
                isX = false;
                b.setEnabled(false);
                turnTracker = "O";
                tracker.setText(turnTracker);
            } else {
                b.setText(turnTracker);
                isX = true;
                b.setEnabled(false);
                turnTracker = "X";
                tracker.setText(turnTracker);
            }
            checkForWin();
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
            turnTracker = "X";
        }
    }; // can make this its own function if need to reset game in other ways

    @SuppressLint("DefaultLocale")
    private void checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (tiles[i][0].getText().toString().equals(tiles[i][1].getText().toString())
                    && (tiles[i][0].getText().toString().equals(tiles[i][2].getText().toString()))
                    && !tiles[i][0].getText().toString().equals("")) {
                if (tiles[i][0].getText().toString().equals("X")) {
                    playerXWins++;
                    hasWon = true;
                    XTracker.setText(String.format("%d", playerXWins));
                }
                if (tiles[i][0].getText().toString().equals("O")) {
                    playerOWins++;
                    hasWon = true;
                    OTracker.setText(String.format("%d", playerOWins));
                }
            }
        } // check horizontal victory
        for (int i = 0; i < 3; i++) {
            if (tiles[0][i].getText().toString().equals(tiles[1][i].getText().toString())
                    && (tiles[0][i].getText().toString().equals(tiles[2][i].getText().toString()))
                    && !tiles[0][i].getText().toString().equals("")) {
                if (tiles[0][i].getText().toString().equals("X")) {
                    playerXWins++;
                    hasWon = true;
                    XTracker.setText(String.format("%d", playerXWins));
                }
                if (tiles[0][i].getText().toString().equals("O")) {
                    playerOWins++;
                    hasWon = true;
                    OTracker.setText(String.format("%d", playerOWins));
                }

            }
        } // check vertical victory
        if (tiles[0][0].getText().toString().equals(tiles[1][1].getText().toString())
                && (tiles[0][0].getText().toString().equals(tiles[2][2].getText().toString()))  // Check top left to bottom right win
                && !tiles[0][0].getText().toString().equals("")) {                               //
            if (tiles[0][0].getText().toString().equals("X")) {                                   /////////////////////////////////////
                playerXWins++;
                hasWon = true;
                XTracker.setText(String.format("%d", playerXWins));
            }
            if (tiles[0][0].getText().toString().equals("O")) {
                playerOWins++;
                hasWon = true;
                OTracker.setText(String.format("%d", playerOWins));
            }
        }
        if (tiles[0][2].getText().toString().equals(tiles[1][1].getText().toString())
                && (tiles[0][2].getText().toString().equals(tiles[2][0].getText().toString()))  // Check top right to bottom left win
                && !tiles[0][2].getText().toString().equals("")) {                               //
            if (tiles[0][2].getText().toString().equals("X")) {                                   //////////////////////////////////////
                playerXWins++;
                hasWon = true;
                XTracker.setText(String.format("%d", playerXWins));
            }
            if (tiles[0][2].getText().toString().equals("O")) {
                playerOWins++;
                hasWon = true;
                OTracker.setText(String.format("%d", playerOWins));
            }
        }
        if(hasWon){
            Toast.makeText(MainActivity.this, "winner!", Toast.LENGTH_LONG).show();
        }
    }

}
