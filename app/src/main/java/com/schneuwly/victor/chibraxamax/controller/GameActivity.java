package com.schneuwly.victor.chibraxamax.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.model.Duo;
import com.schneuwly.victor.chibraxamax.model.Game;
import com.schneuwly.victor.chibraxamax.model.Player;

public class GameActivity extends AppCompatActivity {
    //private TextView team1_20, team1_50, team1_100, team2_20, team2_50, team2_100;
    private TextView scoreView, team1_name, team2_name;
    private Player[] players;
    private Duo[] duos;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        players = new Player[]{
                new Player("Player 1"),
                new Player("Player 2"),
                new Player("Player 3"),
                new Player("Player 4")
        };

        duos = new Duo[]{
                new Duo(players[0], players[1], getResources().getString(R.string.default_name_team1)),
                new Duo(players[2], players[3], getResources().getString(R.string.default_name_team2))
        };

        game = new Game(duos[0], duos[1], Integer.parseInt(getResources().getString(R.string.default_score)));

        scoreView = findViewById(R.id.score);
        scoreView.setOnClickListener(l -> {
            EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);

            showPopUp("Score", "Changer le score final:", input,
                    (d, w) -> {
                        int newScore = Integer.parseInt(input.getText().toString());
                        game.setEndScore(newScore);
                        scoreView.setText(input.getText().toString());
                    }
            );
        });

        team1_name = findViewById(R.id.team1_name);
        team1_name.setOnClickListener(l -> {
            EditText input = new EditText(this);

            showPopUp(duos[0].getName(), "Changer le nom de " + duos[0].getName() + ":", input,
                    (d, w) -> {
                        duos[0].setName(input.getText().toString());
                        team1_name.setText(input.getText().toString());
                    }
            );
        });

        team2_name = findViewById(R.id.team2_name);
        team2_name.setOnClickListener(l -> {
            EditText input = new EditText(this);

            showPopUp(duos[1].getName(), "Changer le nom de " + duos[1].getName() + ":", input,
                    (d, w) -> {
                        duos[1].setName(input.getText().toString());
                        team2_name.setText(input.getText().toString());
                    }
            );
        });

        //team1_20 = findViewById(R.id.team1_20);
        //team1_50 = findViewById(R.id.team1_50);
        //team1_100 = findViewById(R.id.team1_100);
        //team2_20 = findViewById(R.id.team2_20);
        //team2_50 = findViewById(R.id.team2_50);
        //team2_100 = findViewById(R.id.team2_100);
    }

    private void showPopUp(String title, String message, EditText input, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message)
                .setView(input)
                .setPositiveButton("Ok", listener)
                .setNegativeButton("Annulé", (d, w) -> {
                })
                .create()
                .show();
    }

    /**
     * Returns the tally marks representation of a given number (using the tally marks font).
     * a: one vertical line.
     * b: two vertical lines.
     * c: three vertical lines.
     * d: four vertical lines.
     * e: diagonal line crossing five vertical lines.
     *
     * @param nb the number we want to represent in tally marks.
     * @return the tally marks representation of a given number.
     */
    public static String tallyMarks(int nb) {
        StringBuilder sb = new StringBuilder();

        int toCompute = nb;

        while (toCompute >= 5) {
            toCompute -= 5;
            sb.append("e");
        }

        //Compute the rest
        switch (toCompute) {
            case 1:
                sb.append("a");

            case 2:
                sb.append("b");

            case 3:
                sb.append("c");

            case 4:
                sb.append("d");

            default:
        }

        return sb.toString();
    }

    /**
     * Returns the "X\" representation of a given number.
     * \: 50.
     * X: 100.
     *
     * @param nb the number we want to represent in "X\".
     * @return the "X\" representation of a given number.
     */
    public String xMarks(int nb) {
        StringBuilder sb = new StringBuilder();

        int toCompute = nb;

        while (toCompute >= 2) {
            toCompute -= 2;
            sb.append("X");
        }

        if (toCompute == 1) {
            sb.append("\\");
        }

        return sb.toString();
    }

    /**
     * Returns the "V" representation of a given number.
     *
     * @param nb the number we want to represent in "V".
     * @return the "V" representation of a given number.
     */
    public String vMarks(int nb) {
        StringBuilder sb = new StringBuilder();

        int toCompute = nb;

        while (toCompute > 0) {
            --toCompute;
            sb.append("V");
        }

        return sb.toString();
    }
}
