package com.schneuwly.victor.chibraxamax.controller;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.model.Duo;
import com.schneuwly.victor.chibraxamax.model.Game;
import com.schneuwly.victor.chibraxamax.model.Player;

public class GameActivity extends AppCompatActivity {
    //private TextView team1_20, team1_50, team1_100, team2_20, team2_50, team2_100;
    private EditText scoreView, team1_name, team2_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Player[] players = {
                new Player("Player 1"),
                new Player("Player 2"),
                new Player("Player 3"),
                new Player("Player 4")
        };

        Duo[] duos = {
                new Duo(players[0], players[1], getResources().getString(R.string.default_name_team1)),
                new Duo(players[2], players[3], getResources().getString(R.string.default_name_team2))
        };

        Game game = new Game(duos[0], duos[1], Integer.parseInt(getResources().getString(R.string.default_score)));

        scoreView = findViewById(R.id.score);
        scoreView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        team1_name = findViewById(R.id.team1_name);
        team2_name = findViewById(R.id.team2_name);

        //team1_20 = findViewById(R.id.team1_20);
        //team1_50 = findViewById(R.id.team1_50);
        //team1_100 = findViewById(R.id.team1_100);
        //team2_20 = findViewById(R.id.team2_20);
        //team2_50 = findViewById(R.id.team2_50);
        //team2_100 = findViewById(R.id.team2_100);
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
