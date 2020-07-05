package com.schneuwly.victor.chibraxamax.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.model.Duo;
import com.schneuwly.victor.chibraxamax.model.Game;
import com.schneuwly.victor.chibraxamax.model.Player;

public class GameActivity extends AppCompatActivity {
    private TextView scoreView,
            team0_name, team0_score, team0_20, team0_50, team0_100, team0_rest, team0_V,
            team1_name, team1_score, team1_20, team1_50, team1_100, team1_rest, team1_V;
    private Button[] addButtons = new Button[2];

    //Model
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
                new Duo(players[0], players[1], getResources().getString(R.string.default_name_team0)),
                new Duo(players[2], players[3], getResources().getString(R.string.default_name_team1))
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

        team0_name = findViewById(R.id.team0_name);
        team0_name.setOnClickListener(l -> {
            EditText input = new EditText(this);

            showPopUp(duos[0].getName(), "Changer le nom de " + duos[0].getName() + ":", input,
                    (d, w) -> {
                        duos[0].setName(input.getText().toString());
                        team0_name.setText(input.getText().toString());
                    }
            );
        });

        team1_name = findViewById(R.id.team1_name);
        team1_name.setOnClickListener(l -> {
            EditText input = new EditText(this);

            showPopUp(duos[1].getName(), "Changer le nom de " + duos[1].getName() + ":", input,
                    (d, w) -> {
                        duos[1].setName(input.getText().toString());
                        team1_name.setText(input.getText().toString());
                    }
            );
        });

        addButtons[0] = findViewById(R.id.add_button_1);
        /*
        addButtons[0].setOnClickListener(l -> {
            View multiplyer = new View(this);

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

         */

        addButtons[1] = findViewById(R.id.add_button_2);

        team0_score = findViewById(R.id.team0_score);
        team1_score = findViewById(R.id.team1_score);

        team0_20 = findViewById(R.id.team0_20);
        team0_20.setOnClickListener(l -> {
            game.add20Points(duos[0]);
            updateDisplay();
        });


        team0_50 = findViewById(R.id.team0_50);
        team0_50.setOnClickListener(l -> {
            game.add50Points(duos[0]);
            updateDisplay();
        });

        team0_100 = findViewById(R.id.team0_100);
        team0_100.setOnClickListener(l -> {
            game.add100Points(duos[0]);
            updateDisplay();
        });

        team0_rest = findViewById(R.id.team0_rest);
        team0_rest.setOnClickListener(l -> {
            game.add1Point(duos[0]);
            updateDisplay();
        });

        team0_V = findViewById(R.id.team0_V);

        team1_20 = findViewById(R.id.team1_20);
        team1_20.setOnClickListener(l -> {
            game.add20Points(duos[1]);
            updateDisplay();
        });

        team1_50 = findViewById(R.id.team1_50);
        team1_50.setOnClickListener(l -> {
            game.add50Points(duos[1]);
            updateDisplay();
        });

        team1_100 = findViewById(R.id.team1_100);
        team1_100.setOnClickListener(l -> {
            game.add100Points(duos[1]);
            updateDisplay();
        });

        team1_rest = findViewById(R.id.team1_rest);
        team1_rest.setOnClickListener(l -> {
            game.add1Point(duos[1]);
            updateDisplay();
        });

        team1_V = findViewById(R.id.team1_V);

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

    private void updateDisplay() {
        team0_score.setText(String.valueOf(duos[0].getTotalPoints()));
        team0_20.setText(tallyMarks(duos[0].getPointsDisplay().getNb20()));
        team0_50.setText(xMarks(duos[0].getPointsDisplay().getNb50()));
        team0_100.setText(tallyMarks(duos[0].getPointsDisplay().getNb100()));
        team0_rest.setText(String.valueOf(duos[0].getPointsDisplay().getRest()));
        team0_V.setText(vMarks(duos[0].getPointsDisplay().getNbV()));

        team1_score.setText(String.valueOf(duos[1].getTotalPoints()));
        team1_20.setText(tallyMarks(duos[1].getPointsDisplay().getNb20()));
        team1_50.setText(xMarks(duos[1].getPointsDisplay().getNb50()));
        team1_100.setText(tallyMarks(duos[1].getPointsDisplay().getNb100()));
        team1_rest.setText(String.valueOf(duos[1].getPointsDisplay().getRest()));
        team1_V.setText(vMarks(duos[1].getPointsDisplay().getNbV()));
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
                return sb.toString();

            case 2:
                sb.append("b");
                return sb.toString();

            case 3:
                sb.append("c");
                return sb.toString();

            case 4:
                sb.append("d");
                return sb.toString();

            default:
                return sb.toString();
        }
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

        for (int i = 0; i < nb; i++) {
            sb.append("V");
        }
        return sb.toString();
    }
}
