package com.schneuwly.victor.chibraxamax.controller;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.model.Duo;
import com.schneuwly.victor.chibraxamax.model.Game;
import com.schneuwly.victor.chibraxamax.model.Player;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {
    private TextView endScoreView,
            team0_name, team0_score, team0_20, team0_50, team0_100, team0_rest, team0_V,
            team1_name, team1_score, team1_20, team1_50, team1_100, team1_rest, team1_V;
    private Button[] addButtons = new Button[2];

    private Dialog value_popup, add_popup;
    private EditText popup_input;

    //TODO: Create dialogue add points and historic (Recycle View)

    //Model
    private Player[] players;
    private Duo[] duos;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameInit();

        gameInfos();

        gameScores();

    }

    private void gameInit() {
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
    }

    private void gameInfos() {
        value_popup = new Dialog(this);
        value_popup.setContentView(R.layout.value_modifier_popup);

        popup_input = value_popup.findViewById(R.id.popup_edit);

        endScoreView = findViewById(R.id.score);
        endScoreView.setOnClickListener(l -> {

            popup_input.setInputType(InputType.TYPE_CLASS_NUMBER);

            showValuePopUp("Score", "Changer le score final:", popup_input,
                    e -> {
                        int newScore = Integer.parseInt(popup_input.getText().toString());
                        game.setEndScore(newScore);
                        endScoreView.setText(popup_input.getText().toString());
                        value_popup.dismiss();
                    }
            );
        });


        team0_name = findViewById(R.id.team0_name);
        team0_name.setOnClickListener(l -> {

            popup_input.setInputType(InputType.TYPE_CLASS_TEXT);

            showValuePopUp(duos[0].getName(), "Changer le nom de " + duos[0].getName() + ":", popup_input,
                    e -> {
                        duos[0].setName(popup_input.getText().toString());
                        team0_name.setText(popup_input.getText().toString());
                        value_popup.dismiss();
                    }
            );
        });

        team1_name = findViewById(R.id.team1_name);
        team1_name.setOnClickListener(l -> {
            popup_input.setInputType(InputType.TYPE_CLASS_TEXT);

            showValuePopUp(duos[1].getName(), "Changer le nom de " + duos[1].getName() + ":", popup_input,
                    e -> {
                        duos[1].setName(popup_input.getText().toString());
                        team1_name.setText(popup_input.getText().toString());
                        value_popup.dismiss();
                    }
            );
        });
    }

    private void gameScores() {
        add_popup = new Dialog(this);
        add_popup.setContentView(R.layout.add_popup);

        addButtons[0] = findViewById(R.id.add_button_1);
        addButtons[0].setOnClickListener(l -> {
            showAddPopUp(duos[0]);
        });

        addButtons[1] = findViewById(R.id.add_button_2);
        addButtons[1].setOnClickListener(l -> {
            showAddPopUp(duos[1]);
        });

        team0_score = findViewById(R.id.team0_score);
        team1_score = findViewById(R.id.team1_score);

        team0_20 = findViewById(R.id.team0_20);
        team0_20.setOnClickListener(l -> {
            game.add20Points(duos[0]);
            updateGame();
        });


        team0_50 = findViewById(R.id.team0_50);
        team0_50.setOnClickListener(l -> {
            game.add50Points(duos[0]);
            updateGame();
        });

        team0_100 = findViewById(R.id.team0_100);
        team0_100.setOnClickListener(l -> {
            game.add100Points(duos[0]);
            updateGame();
        });

        team0_rest = findViewById(R.id.team0_rest);
        team0_rest.setOnClickListener(l -> {
            game.add1Point(duos[0]);
            updateGame();
        });

        team0_V = findViewById(R.id.team0_V);

        team1_20 = findViewById(R.id.team1_20);
        team1_20.setOnClickListener(l -> {
            game.add20Points(duos[1]);
            updateGame();
        });

        team1_50 = findViewById(R.id.team1_50);
        team1_50.setOnClickListener(l -> {
            game.add50Points(duos[1]);
            updateGame();
        });

        team1_100 = findViewById(R.id.team1_100);
        team1_100.setOnClickListener(l -> {
            game.add100Points(duos[1]);
            updateGame();
        });

        team1_rest = findViewById(R.id.team1_rest);
        team1_rest.setOnClickListener(l -> {
            game.add1Point(duos[1]);
            updateGame();
        });

        team1_V = findViewById(R.id.team1_V);
    }

    private void showValuePopUp(String title, String message, EditText input, View.OnClickListener listener) {
        TextView title_view, message_view, ok, cancel;

        input.setText(null);
        input.setHint(title);
        input.setHintTextColor(R.color.primaryDark);
        input.setImeActionLabel("Ok", KeyEvent.KEYCODE_ENTER);
        //input.setOnEditorActionListener();

        title_view = value_popup.findViewById(R.id.popup_title);
        message_view = value_popup.findViewById(R.id.popup_message);
        ok = value_popup.findViewById(R.id.popup_ok);
        cancel = value_popup.findViewById(R.id.popup_cancel);

        title_view.setText(title);
        message_view.setText(message);


        ok.setOnClickListener(listener);
        cancel.setOnClickListener(l -> value_popup.dismiss());

        Objects.requireNonNull(value_popup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        value_popup.show();
    }


    private void showAddPopUp(Duo duo) {

        TextView title, score, total, ok, cancel, announce;
        RadioGroup radioGroup;
        RadioButton radioButton;
        Button match_button, clear_button, erase_button;
        Button[] digits = new Button[10];

        title = add_popup.findViewById(R.id.popup_title);
        title.setText(getResources().getString(R.string.add_title_without_name) + duo.getName());
        score = add_popup.findViewById(R.id.popup_score);
        score.setText("0");
        total = add_popup.findViewById(R.id.popup_total);
        total.setText("");
        ok = add_popup.findViewById(R.id.popup_ok);
        cancel = add_popup.findViewById(R.id.popup_cancel);
        announce = add_popup.findViewById(R.id.popup_annonce);

        radioGroup = add_popup.findViewById(R.id.radioGroup);

        match_button = add_popup.findViewById(R.id.popup_match);
        clear_button = add_popup.findViewById(R.id.popup_clear);
        erase_button = add_popup.findViewById(R.id.popup_erase);

        digits[0] = add_popup.findViewById(R.id.popup_0);
        digits[1] = add_popup.findViewById(R.id.popup_1);
        digits[2] = add_popup.findViewById(R.id.popup_2);
        digits[3] = add_popup.findViewById(R.id.popup_3);
        digits[4] = add_popup.findViewById(R.id.popup_4);
        digits[5] = add_popup.findViewById(R.id.popup_5);
        digits[6] = add_popup.findViewById(R.id.popup_6);
        digits[7] = add_popup.findViewById(R.id.popup_7);
        digits[8] = add_popup.findViewById(R.id.popup_8);
        digits[9] = add_popup.findViewById(R.id.popup_9);

        for (Button digit : digits) {
            digit.setOnClickListener(l -> {
                String currentInput = score.getText().toString();
                String newInput;

                if (currentInput.length() < 3) {
                    newInput = currentInput + digit.getText().toString();
                    newInput = (newInput.startsWith("0")) ? newInput.substring(1) : newInput;

                    score.setText(newInput);
                    total.setText(
                            String.format("Total: %s (Adversaire: %s)", newInput,
                                    (Integer.parseInt(newInput) >= 157) ? "0" : Game.MAX_POINTS - Integer.parseInt(newInput))
                    );
                }

            });
        }

        //TODO: multiplication

        match_button.setOnClickListener(l -> {
            score.setText(String.valueOf(Game.MATCH_POINTS));
            total.setText(
                    String.format("Total: %s (Adversaire: %s)", Game.MATCH_POINTS, "0")
            );
        });

        clear_button.setOnClickListener(l -> {
            String currentInput = score.getText().toString();
            String newInput = currentInput.substring(0, currentInput.length() - 1);

            score.setText(newInput);
            total.setText(
                    String.format("Total: %s (Adversaire: %s)", newInput,
                            (Integer.parseInt(newInput) >= 157) ? "0" : Game.MAX_POINTS - Integer.parseInt(newInput))
            );

        });

        erase_button.setOnClickListener(l -> {
            score.setText("0");
            total.setText(
                    String.format("Total: %s (Adversaire: %s)", "0", "157")
            );
        });

        ok.setOnClickListener(l -> {
            int inputScore = Integer.parseInt(score.getText().toString());
            game.addPointsForBothDuo(duo, inputScore);
            updateGame();
            add_popup.dismiss();
        });

        announce.setOnClickListener(l -> {
            int inputScore = Integer.parseInt(score.getText().toString());
            game.addPoints(duo, inputScore);
            updateGame();
            add_popup.dismiss();
        });

        cancel.setOnClickListener(l -> add_popup.dismiss());

        Objects.requireNonNull(add_popup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        add_popup.show();
    }

    private void updateGame() {
        updateDisplay();

        //TODO: verifier état du jeu
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
                break;

            case 2:
                sb.append("b");
                break;

            case 3:
                sb.append("c");
                break;

            case 4:
                sb.append("d");
                break;

            default:
                break;
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

        for (int i = 0; i < nb; i++) {
            sb.append("V");
        }
        return sb.toString();
    }
}
