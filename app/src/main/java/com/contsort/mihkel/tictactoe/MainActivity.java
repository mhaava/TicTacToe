package com.contsort.mihkel.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button[][] buttons = new Button[3][3];

    protected boolean player1Turn = true;

    protected int roundCount;

    protected int p1Points=0;
    protected int p2Points=0;

    protected TextView textViewP1;
    protected TextView textViewP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewP1 = findViewById(R.id.text_view_p1);
        textViewP2 = findViewById(R.id.text_view_p2);

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                String buttonID = "button_" + y + x;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[y][x] = findViewById(resID);
                buttons[y][x].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        buttons[y][x].setText("");
                    }
                }
                textViewP1.setText("P1: 0");
                textViewP2.setText("P2: 0");
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) v).setText("X");
            player1Turn=false;
        } else {
            ((Button) v).setText("O");
            player1Turn=true;
        }
        if (checkForWin()) {
            if (player1Turn) {
                p1Points++;
            } else {
                p2Points++;
            }
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    buttons[y][x].setText("");
                }
            }
        }
        roundCount++;
        textViewP1.setText("P1: "+p1Points);
        textViewP2.setText("P1: "+p2Points);
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                field[y][x] = buttons[y][x].getText().toString();
            }
        }

        for (int y = 0; y < 3; y++) {
            if (field[y][0].equals(field[y][1])
                    && field[y][0].equals(field[y][2])
                    && !field[y][0].equals("")) {
                return true;
            }
        }

        for (int x = 0; x < 3; x++) {
            if (field[0][x].equals(field[1][x])
                    && field[0][x].equals(field[2][x])
                    && !field[0][x].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }
}
