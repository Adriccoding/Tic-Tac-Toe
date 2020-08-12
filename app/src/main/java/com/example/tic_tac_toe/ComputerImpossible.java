package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class ComputerImpossible extends AppCompatActivity {

    // Global variables
    String TAG = "debug";
    int turnCounter = 0;
    boolean twoInARow = false;
    boolean hasWon = false;
    boolean player1X = true;
    int player1Wins = 0;
    int player2Wins = 0;
    String turnTracker = "X";
    private static final int[][] idArray = {{R.id.button00, R.id.button01, R.id.button02},
            {R.id.button10, R.id.button11, R.id.button12},
            {R.id.button20, R.id.button21, R.id.button22}};
    Button tiles[][] = new Button[3][3];
    private TextView tracker;
    private TextView XTracker;
    private TextView OTracker;
    private TextView whosX;
    String name1;
    String name2 = "Computer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1 = getIntent().getStringExtra("name_key");

        TextView player1Name = (TextView) findViewById(R.id.player1Name);
        TextView player2Name = (TextView) findViewById(R.id.player2Name);
        Button buttonMenu = (Button) findViewById(R.id.btnMenu);
        Button buttonRestart = (Button) findViewById(R.id.buttonRestart);
        Button buttonResetScore = (Button) findViewById(R.id.buttonResetScore);
        Button buttonSwap = (Button) findViewById(R.id.btnSwap);
        whosX = (TextView) findViewById(R.id.whosX);
        tracker = (TextView) findViewById(R.id.Tracker);
        XTracker = (TextView) findViewById(R.id.XwinCounter);
        OTracker = (TextView) findViewById(R.id.OwinCounter);

        tracker.setText(turnTracker);
        player1Name.setText(name1);
        player2Name.setText(name2);
        whosX.setText(name1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j] = (Button) findViewById(idArray[i][j]);
                tiles[i][j].setOnClickListener(write);
            }
        }
        buttonRestart.setOnClickListener(restart);
        buttonResetScore.setOnClickListener(resetScore);
        buttonSwap.setOnClickListener(swap);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });
    }

    View.OnClickListener write = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            if (player1X == true) {
                b.setText("X");
                b.setEnabled(false);
                turnCounter++;
                Log.d(TAG, "onClick: turn coutner is" + turnCounter);
                twoInARow();
                checkForWin();
                if(!hasWon) {
                    if(turnCounter != 9) {
                        computerTurn("O");
                    }
                    turnCounter++;
                    checkForWin();
                }
            } else{
                b.setText("O");
                b.setEnabled(false);
                turnCounter++;
                twoInARow();
                checkForWin();
                if(!hasWon || turnCounter == 9){
                    computerTurn("X");
                    turnCounter++;
                    checkForWin();
                }
            }
        }
    };

    View.OnClickListener restart = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            restartGame();
        }
    }; // can make this its own function if need to reset game in other ways

    View.OnClickListener resetScore = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            player1Wins = 0;
            player2Wins = 0;
            XTracker.setText(String.format("%d", player1Wins));
            OTracker.setText(String.format("%d", player2Wins));
            restartGame();
        }
    };

    View.OnClickListener swap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(player1X){
                player1X = false;
                whosX.setText(name2);
                computerTurn("X");
            } else {
                player1X = true;
                whosX.setText(name1);
            }
            restartGame();
        }
    };

    @SuppressLint("DefaultLocale")
    private void checkForWin() {

        for (int i = 0; i < 3; i++) {
            if (tiles[i][0].getText().toString().equals(tiles[i][1].getText().toString())
                    && (tiles[i][0].getText().toString().equals(tiles[i][2].getText().toString()))
                    && !tiles[i][0].getText().toString().equals("")) {
                if (tiles[i][0].getText().toString().equals("X") && player1X) {
                    player1Wins++;
                    hasWon = true;
                    XTracker.setText(String.format("%d", player1Wins));
                }
                if (tiles[i][0].getText().toString().equals("X") && !player1X) {
                    player2Wins++;
                    hasWon = true;
                    OTracker.setText(String.format("%d", player2Wins));
                }
                if (tiles[i][0].getText().toString().equals("O") && player1X) {
                    player2Wins++;
                    hasWon = true;
                    OTracker.setText(String.format("%d", player2Wins));
                }
                if (tiles[i][0].getText().toString().equals("O") && !player1X) {
                    player1Wins++;
                    hasWon = true;
                    XTracker.setText(String.format("%d", player1Wins));
                }
            }
        } // check horizontal victory

        for (int i = 0; i < 3; i++) {
            if (tiles[0][i].getText().toString().equals(tiles[1][i].getText().toString())
                    && (tiles[0][i].getText().toString().equals(tiles[2][i].getText().toString()))
                    && !tiles[0][i].getText().toString().equals("")) {
                if (tiles[0][i].getText().toString().equals("X") && player1X) {
                    player1Wins++;
                    hasWon = true;
                    XTracker.setText(String.format("%d", player1Wins));
                }
                if (tiles[0][i].getText().toString().equals("X") && !player1X) {
                    player2Wins++;
                    hasWon = true;
                    OTracker.setText(String.format("%d", player2Wins));
                }
                if (tiles[0][i].getText().toString().equals("O") && player1X) {
                    player2Wins++;
                    hasWon = true;
                    OTracker.setText(String.format("%d", player2Wins));
                }
                if (tiles[0][i].getText().toString().equals("O") && !player1X) {
                    player1Wins++;
                    hasWon = true;
                    XTracker.setText(String.format("%d", player1Wins));
                }
            }
        } // check vertical victory

        if (tiles[0][0].getText().toString().equals(tiles[1][1].getText().toString())
                && (tiles[0][0].getText().toString().equals(tiles[2][2].getText().toString()))  // Check top left to bottom right win
                && !tiles[0][0].getText().toString().equals("")) {                               //
            if (tiles[0][0].getText().toString().equals("X") && player1X) {
                player1Wins++;
                hasWon = true;
                XTracker.setText(String.format("%d", player1Wins));
            }
            if (tiles[0][0].getText().toString().equals("X") && !player1X) {
                player2Wins++;
                hasWon = true;
                OTracker.setText(String.format("%d", player2Wins));
            }
            if (tiles[0][0].getText().toString().equals("O") && player1X) {
                player2Wins++;
                hasWon = true;
                OTracker.setText(String.format("%d", player2Wins));
            }
            if (tiles[0][0].getText().toString().equals("O") && !player1X) {
                player1Wins++;
                hasWon = true;
                XTracker.setText(String.format("%d", player1Wins));
            }
        }

        if (tiles[0][2].getText().toString().equals(tiles[1][1].getText().toString())
                && (tiles[0][2].getText().toString().equals(tiles[2][0].getText().toString()))  // Check top right to bottom left win
                && !tiles[0][2].getText().toString().equals("")) {                               //
            if (tiles[0][2].getText().toString().equals("X") && player1X) {
                player1Wins++;
                hasWon = true;
                XTracker.setText(String.format("%d", player1Wins));
            }
            if (tiles[0][2].getText().toString().equals("X") && !player1X) {
                player2Wins++;
                hasWon = true;
                OTracker.setText(String.format("%d", player2Wins));
            }
            if (tiles[0][2].getText().toString().equals("O") && player1X) {
                player2Wins++;
                hasWon = true;
                OTracker.setText(String.format("%d", player2Wins));
            }
            if (tiles[0][2].getText().toString().equals("O") && !player1X) {
                player1Wins++;
                hasWon = true;
                XTracker.setText(String.format("%d", player1Wins));
            }
        }

        if(hasWon){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tiles[i][j].setEnabled(false);
                }
            }

        }
    }

    private void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    private void restartGame(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j].setText("");
                tiles[i][j].setEnabled(true);
            }
        }
        if(!player1X){
            computerTurn("X");
        }
        turnTracker = "X";
        hasWon = false;
        twoInARow = false;
        turnCounter = 0;
    }

    private void computerTurn(String item){

        boolean isEmpty = false;
        int[] arr = twoInARow();
        int one = arr[0];
        int two = arr[1];

        if(twoInARow){
            if (tiles[one][two].getText().toString().equals("")) {
                tiles[one][two].setText(item);
                tiles[one][two].setEnabled(false);
                twoInARow = false;
            }
        }else {
            while (isEmpty == false) {
                int rand1 = new Random().nextInt(3);
                int rand2 = new Random().nextInt(3);
                if (tiles[rand1][rand2].getText().toString().equals("")) {
                    tiles[rand1][rand2].setText(item);
                    tiles[rand1][rand2].setEnabled(false);
                    isEmpty = true;
                }
            }
        }
    }

    private int[] twoInARow(){


        if(tiles[1][1].getText().toString().equals(tiles[0][0].getText().toString()) && !tiles[1][1].getText().toString().equals("") && tiles[2][2].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,2};
            return arr;
        }
        if(tiles[1][1].getText().toString().equals(tiles[0][1].getText().toString()) && !tiles[1][1].getText().toString().equals("") && tiles[2][1].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,1};
            return arr;
        }
        if(tiles[1][1].getText().toString().equals(tiles[0][2].getText().toString()) && !tiles[1][1].getText().toString().equals("") && tiles[2][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,0};
            return arr;
        }
        if(tiles[1][1].getText().toString().equals(tiles[1][0].getText().toString()) && !tiles[1][1].getText().toString().equals("") && tiles[1][2].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={1,2};
            return arr;
        }
        if(tiles[1][1].getText().toString().equals(tiles[1][2].getText().toString()) && !tiles[1][1].getText().toString().equals("") && tiles[1][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={1,0};
            return arr;
        }
        if(tiles[1][1].getText().toString().equals(tiles[2][0].getText().toString()) && !tiles[1][1].getText().toString().equals("") && tiles[0][2].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={0,2};
            return arr;
        }
        if(tiles[1][1].getText().toString().equals(tiles[2][1].getText().toString()) && !tiles[1][1].getText().toString().equals("") && tiles[0][1].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={0,1};
            return arr;
        }
        if(tiles[1][1].getText().toString().equals(tiles[2][2].getText().toString()) && !tiles[1][1].getText().toString().equals("") && tiles[0][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={0,0};
            return arr;
        }
        if(tiles[0][0].getText().toString().equals(tiles[1][0].getText().toString()) && !tiles[0][0].getText().toString().equals("") && tiles[2][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,0};
            return arr;
        }
        if(tiles[0][0].getText().toString().equals(tiles[2][0].getText().toString()) && !tiles[0][0].getText().toString().equals("") && tiles[1][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={1,0};
            return arr;
        }
        if(tiles[0][0].getText().toString().equals(tiles[0][1].getText().toString()) && !tiles[0][0].getText().toString().equals("") && tiles[0][2].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={0,2};
            return arr;
        }
        if(tiles[0][0].getText().toString().equals(tiles[0][2].getText().toString()) && !tiles[0][0].getText().toString().equals("") && tiles[0][1].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={0,1};
            return arr;
        }
        if(tiles[2][2].getText().toString().equals(tiles[2][1].getText().toString()) && !tiles[2][2].getText().toString().equals("") && tiles[2][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,0};
            return arr;
        }
        if(tiles[2][2].getText().toString().equals(tiles[2][0].getText().toString()) && !tiles[2][2].getText().toString().equals("") && tiles[2][1].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,1};
            return arr;
        }
        if(tiles[2][2].getText().toString().equals(tiles[2][1].getText().toString()) && !tiles[2][2].getText().toString().equals("") && tiles[2][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,0};
            return arr;
        }
        if(tiles[2][2].getText().toString().equals(tiles[2][1].getText().toString()) && !tiles[2][2].getText().toString().equals("") && tiles[2][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,0};
            return arr;
        }
        if(tiles[2][2].getText().toString().equals(tiles[2][1].getText().toString()) && !tiles[2][2].getText().toString().equals("") && tiles[2][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,0};
            return arr;
        }
        if(tiles[2][2].getText().toString().equals(tiles[1][2].getText().toString()) && !tiles[2][2].getText().toString().equals("") && tiles[0][2].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={0,2};
            return arr;
        }
        if(tiles[2][2].getText().toString().equals(tiles[0][2].getText().toString()) && !tiles[2][2].getText().toString().equals("") && tiles[1][2].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={1,2};
            return arr;
        }
        if(tiles[2][0].getText().toString().equals(tiles[2][1].getText().toString()) && !tiles[2][0].getText().toString().equals("") && tiles[2][2].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,2};
            return arr;
        }
        if(tiles[2][0].getText().toString().equals(tiles[1][0].getText().toString()) && !tiles[2][0].getText().toString().equals("") && tiles[0][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={0,0};
            return arr;
        }
        if(tiles[0][2].getText().toString().equals(tiles[0][1].getText().toString()) && !tiles[0][2].getText().toString().equals("") && tiles[0][0].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={0,0};
            return arr;
        }
        if(tiles[0][2].getText().toString().equals(tiles[1][2].getText().toString()) && !tiles[0][2].getText().toString().equals("") && tiles[2][2].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={2,2};
            return arr;
        }
        if(tiles[0][0].getText().toString().equals(tiles[2][2].getText().toString()) && !tiles[0][0].getText().toString().equals("") && tiles[1][1].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={1,1};
            return arr;
        }
        if(tiles[0][2].getText().toString().equals(tiles[2][0].getText().toString()) && !tiles[0][2].getText().toString().equals("") && tiles[1][1].getText().toString().equals("")){
            twoInARow = true;
            int[] arr ={1,1};
            return arr;
        }
        int[] filler = {4,4};
        return filler;
    }
}
