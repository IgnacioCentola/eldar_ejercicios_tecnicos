package com.nacho.eldar_ejercicios_tecnicos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class TatetiActivity extends AppCompatActivity implements View.OnClickListener {


    private Button[] buttons = new Button[9];
    private Button resetButton;

    private int roundCount;
    private boolean activePlayer;

    private int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    private int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},//* horizontal
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //* vertical
            {0, 4, 8}, {2, 4, 6} //* diagonal
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tateti_acitivity);

        resetButton = findViewById(R.id.resetButton);

        // * initialize buttons
        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "button_" + i;
            int resourceId = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resourceId);
            buttons[i].setOnClickListener(this);
        }
        roundCount = 0;
        activePlayer = true;

        resetButton.setOnClickListener(view -> playAgain());
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        // * Obtain button ID
        String buttonId = view.getResources().getResourceEntryName(view.getId());
        // * Extract ID from substring
        int gameStatePointer = Integer.parseInt(buttonId.substring(buttonId.length() - 1, buttonId.length()));

        if (activePlayer) {
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.WHITE);
            gameState[gameStatePointer] = 0;
        } else {
            ((Button) view).setText("O");
            ((Button) view).setTextColor(Color.WHITE);
            gameState[gameStatePointer] = 1;
        }

        roundCount++;

        if (checkWinner()) {
            // * Somebody wins
            if (activePlayer) {
                //* Player one wins
                showDialog(true);
            } else {
                //* Player two wins
                showDialog(false);
            }
        } else if (roundCount == 9) {
            // * Tie
            playAgain();
            Snackbar.make(view, "Tie! Restarting game", Snackbar.LENGTH_LONG).show();

        } else {
            //* Next turn
            activePlayer = !activePlayer;
        }

    }


    public boolean checkWinner() {
        boolean result = false;

        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {
                result = true;
            }
        }
        return result;
    }

    private void playAgain() {
        roundCount = 0;
        activePlayer = true;
        for (int i = 0; i < buttons.length; i++) {
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }

    private void showDialog(boolean activePlayer){
        new AlertDialog.Builder(this)
                .setTitle(R.string.game_finished)
                .setMessage(activePlayer ? "Player 1 won!" : "Player 2 won!")
                .setPositiveButton(R.string.play_again, (dialog, which) -> {
                    playAgain();
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}