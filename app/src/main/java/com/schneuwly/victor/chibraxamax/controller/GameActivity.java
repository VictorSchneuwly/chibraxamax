package com.schneuwly.victor.chibraxamax.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.model.Duo;
import com.schneuwly.victor.chibraxamax.model.Game;
import com.schneuwly.victor.chibraxamax.model.Player;
import com.schneuwly.victor.chibraxamax.model.abstractEntitiy.PlayingEntity;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {
    private final String firstTimeKey = "firstTime";
    private final String touchPointsKey = "touchPoints";
    private final String touchAnnounceKey = "touchAnnouncePoints";
    private final String showMarksKey = "showMarks";
    private final String showGuideKey = "showGuide";
    private final String bigScoreKey = "bigScore";

    private TextView endScoreView,
            team0_name, team0_score, team0_big_score, team0_20, team0_50, team0_100, team0_rest, team0_V,
            team1_name, team1_score, team1_big_score, team1_20, team1_50, team1_100, team1_rest, team1_V;
    private ImageView[] guides = new ImageView[2];

    private Button[] addButtons = new Button[2];

    private Button paramButton, historicButton;

    private GamePopup valuePopup, addPopup, endScorePopup, parametersPopup, statisticPopup, endPopup;
    private EditText popupInput;

    //Model
    private Player[] players;
    private Duo[] duos;
    private Game game;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        preferences = getPreferences(MODE_PRIVATE);

        gameInit();

        gameInfos();

        gameUI();

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

        if (getPreferences(MODE_PRIVATE).getBoolean(firstTimeKey, true)) {

            preferences.edit()
                    .putBoolean(firstTimeKey, false)
                    .putBoolean(touchPointsKey, true)
                    .putBoolean(touchAnnounceKey, true)
                    .putBoolean(bigScoreKey, false)
                    .putBoolean(showGuideKey, true)
                    .putBoolean(showMarksKey, true)
                    .apply();
        }
    }

    private void gameInfos() {
        valuePopup = new GamePopup(this);
        valuePopup.setContentView(R.layout.value_modifier_popup);

        endScorePopup = new GamePopup(this);
        endScorePopup.setContentView(R.layout.end_score_popup);

        endScoreView = findViewById(R.id.score);
        endScoreView.setOnClickListener(l -> showEndScorePopUp());


        popupInput = valuePopup.findViewById(R.id.popup_edit);

        team0_name = findViewById(R.id.team0_name);
        team0_name.setOnClickListener(l -> {

            popupInput.setInputType(InputType.TYPE_CLASS_TEXT);

            showValuePopUp(duos[0].getName(), "Changer le nom de " + duos[0].getName() + ":", popupInput,
                    e -> {
                        duos[0].setName(popupInput.getText().toString());
                        team0_name.setText(popupInput.getText().toString());
                        valuePopup.dismiss();
                    }
            );
        });

        team1_name = findViewById(R.id.team1_name);
        team1_name.setOnClickListener(l -> {
            popupInput.setInputType(InputType.TYPE_CLASS_TEXT);

            showValuePopUp(duos[1].getName(), "Changer le nom de " + duos[1].getName() + ":", popupInput,
                    e -> {
                        duos[1].setName(popupInput.getText().toString());
                        team1_name.setText(popupInput.getText().toString());
                        valuePopup.dismiss();
                    }
            );
        });

    }

    private void gameUI() {
        endPopup = new GamePopup(this, false);
        endPopup.setContentView(R.layout.end_popup);

        parametersPopup = new GamePopup(this);
        parametersPopup.setContentView(R.layout.parameter_popup);

        paramButton = findViewById(R.id.parameter);
        paramButton.setOnClickListener(l -> {
            showParamPopup();
        });

        historicButton = findViewById(R.id.historic);
        historicButton.setOnClickListener(l -> showStatisticPopup());

        statisticPopup = new GamePopup(this);
        statisticPopup.setContentView(R.layout.statistic_popup);

        addPopup = new GamePopup(this);
        addPopup.setContentView(R.layout.add_popup);

        addButtons[0] = findViewById(R.id.add_button_1);
        addButtons[0].setOnClickListener(l -> {
            showAddPopUp(duos[0]);
        });

        addButtons[1] = findViewById(R.id.add_button_2);
        addButtons[1].setOnClickListener(l -> {
            showAddPopUp(duos[1]);
        });

        guides[0] = findViewById(R.id.z1);
        guides[1] = findViewById(R.id.z2);

        team0_score = findViewById(R.id.team0_score);
        team1_score = findViewById(R.id.team1_score);
        team0_big_score = findViewById(R.id.team0_big_score);
        team1_big_score = findViewById(R.id.team1_big_score);

        team0_20 = findViewById(R.id.team0_20);
        team0_50 = findViewById(R.id.team0_50);
        team0_100 = findViewById(R.id.team0_100);
        team0_rest = findViewById(R.id.team0_rest);
        team0_V = findViewById(R.id.team0_V);

        team1_20 = findViewById(R.id.team1_20);
        team1_50 = findViewById(R.id.team1_50);
        team1_100 = findViewById(R.id.team1_100);
        team1_rest = findViewById(R.id.team1_rest);
        team1_V = findViewById(R.id.team1_V);

        team0_20.setOnClickListener(l -> {
            game.add20Points(duos[0], getPreferences(MODE_PRIVATE).getBoolean(touchAnnounceKey, true));
            updateGame();
        });

        team0_50.setOnClickListener(l -> {
            game.add50Points(duos[0], getPreferences(MODE_PRIVATE).getBoolean(touchAnnounceKey, true));
            updateGame();
        });


        team0_100.setOnClickListener(l -> {
            game.add100Points(duos[0], getPreferences(MODE_PRIVATE).getBoolean(touchAnnounceKey, true));
            updateGame();
        });

        team0_rest.setOnClickListener(l -> {
            game.add1Point(duos[0], getPreferences(MODE_PRIVATE).getBoolean(touchAnnounceKey, true));
            updateGame();
        });


        team1_20.setOnClickListener(l -> {
            game.add20Points(duos[1], getPreferences(MODE_PRIVATE).getBoolean(touchAnnounceKey, true));
            updateGame();
        });

        team1_50.setOnClickListener(l -> {
            game.add50Points(duos[1], getPreferences(MODE_PRIVATE).getBoolean(touchAnnounceKey, true));
            updateGame();
        });

        team1_100.setOnClickListener(l -> {
            game.add100Points(duos[1], getPreferences(MODE_PRIVATE).getBoolean(touchAnnounceKey, true));
            updateGame();
        });

        team1_rest.setOnClickListener(l -> {
            game.add1Point(duos[1], getPreferences(MODE_PRIVATE).getBoolean(touchAnnounceKey, true));
            updateGame();
        });

        updateUI();

    }

    private void showValuePopUp(String title, String message, EditText input, View.OnClickListener listener) {
        TextView title_view, message_view, ok, cancel;

        input.setText(null);
        input.setHint(title);
        input.setHintTextColor(R.color.primaryDark);

        title_view = valuePopup.findViewById(R.id.popup_title);
        message_view = valuePopup.findViewById(R.id.popup_message);
        ok = valuePopup.findViewById(R.id.popup_ok);
        cancel = valuePopup.findViewById(R.id.popup_cancel);

        title_view.setText(title);
        message_view.setText(message);

        ok.setOnClickListener(listener);
        cancel.setOnClickListener(l -> valuePopup.dismiss());

        Objects.requireNonNull(valuePopup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        valuePopup.show();
    }

    private void showEndScorePopUp() {
        TextView score, ok, cancel;
        Button clear_button, erase_button, button_1000, button_1500, button_2500;
        Button[] digits = new Button[10];

        score = endScorePopup.findViewById(R.id.popup_score);
        score.setText(endScoreView.getText().toString());
        ok = endScorePopup.findViewById(R.id.popup_ok);
        cancel = endScorePopup.findViewById(R.id.popup_cancel);

        clear_button = endScorePopup.findViewById(R.id.popup_clear);
        erase_button = endScorePopup.findViewById(R.id.popup_erase);

        button_1000 = endScorePopup.findViewById(R.id.popup_1000);
        button_1500 = endScorePopup.findViewById(R.id.popup_1500);
        button_2500 = endScorePopup.findViewById(R.id.popup_2500);

        digits[0] = endScorePopup.findViewById(R.id.popup_0);
        digits[1] = endScorePopup.findViewById(R.id.popup_1);
        digits[2] = endScorePopup.findViewById(R.id.popup_2);
        digits[3] = endScorePopup.findViewById(R.id.popup_3);
        digits[4] = endScorePopup.findViewById(R.id.popup_4);
        digits[5] = endScorePopup.findViewById(R.id.popup_5);
        digits[6] = endScorePopup.findViewById(R.id.popup_6);
        digits[7] = endScorePopup.findViewById(R.id.popup_7);
        digits[8] = endScorePopup.findViewById(R.id.popup_8);
        digits[9] = endScorePopup.findViewById(R.id.popup_9);

        for (Button digit : digits) {
            digit.setOnClickListener(l -> {
                String currentInput = score.getText().toString();
                String newInput;

                if (currentInput.length() < 3) {
                    newInput = currentInput + digit.getText().toString();
                    newInput = (newInput.startsWith("0")) ? newInput.substring(1) : newInput;

                    score.setText(newInput);
                }

            });
        }

        button_1000.setOnClickListener(l -> {
            score.setText("1000");
        });

        button_1500.setOnClickListener(l -> {
            score.setText("1500");
        });

        button_2500.setOnClickListener(l -> {
            score.setText("2500");
        });

        erase_button.setOnClickListener(l -> {
            String currentInput = score.getText().toString();
            String newInput = currentInput.substring(0, currentInput.length() - 1);

            score.setText(newInput);

        });

        clear_button.setOnClickListener(l -> {
            score.setText("0");
        });

        ok.setOnClickListener(l -> {
            game.setEndScore(Integer.parseInt(score.getText().toString()));
            updateGame();
            endScorePopup.dismiss();
        });

        cancel.setOnClickListener(l -> endScorePopup.dismiss());

        Objects.requireNonNull(endScorePopup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        endScorePopup.show();
    }

    private void showAddPopUp(Duo duo) {

        TextView title, score, total, ok, cancel, announce;
        RadioGroup radioGroup;
        final int[] multiplier = {1};
        Button match_button, clear_button, erase_button;
        Button[] digits = new Button[10];

        title = addPopup.findViewById(R.id.popup_title);
        title.setText(getResources().getString(R.string.add_title_without_name) + " " + duo.getName());
        score = addPopup.findViewById(R.id.popup_score);
        score.setText("0");
        total = addPopup.findViewById(R.id.popup_total);
        total.setText("");
        ok = addPopup.findViewById(R.id.popup_ok);
        cancel = addPopup.findViewById(R.id.popup_cancel);
        announce = addPopup.findViewById(R.id.popup_annonce);

        radioGroup = addPopup.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((g, id) -> {
            multiplier[0] = Integer.parseInt(
                    (String) addPopup.findViewById(id).getTag());

            int inputScore = Integer.parseInt(score.getText().toString());

            total.setText(
                    computeTotal(inputScore, multiplier[0])
            );

        });

        match_button = addPopup.findViewById(R.id.popup_match);
        clear_button = addPopup.findViewById(R.id.popup_clear);
        erase_button = addPopup.findViewById(R.id.popup_erase);

        digits[0] = addPopup.findViewById(R.id.popup_0);
        digits[1] = addPopup.findViewById(R.id.popup_1);
        digits[2] = addPopup.findViewById(R.id.popup_2);
        digits[3] = addPopup.findViewById(R.id.popup_3);
        digits[4] = addPopup.findViewById(R.id.popup_4);
        digits[5] = addPopup.findViewById(R.id.popup_5);
        digits[6] = addPopup.findViewById(R.id.popup_6);
        digits[7] = addPopup.findViewById(R.id.popup_7);
        digits[8] = addPopup.findViewById(R.id.popup_8);
        digits[9] = addPopup.findViewById(R.id.popup_9);

        for (Button digit : digits) {
            digit.setOnClickListener(l -> {
                String currentInput = score.getText().toString();
                String newInput;

                if (currentInput.length() < 3) {
                    newInput = currentInput + digit.getText().toString();
                    newInput = (newInput.startsWith("0")) ? newInput.substring(1) : newInput;

                    score.setText(newInput);
                    total.setText(
                            computeTotal(Integer.parseInt(newInput), multiplier[0])
                    );
                }

            });
        }

        match_button.setOnClickListener(l -> {
            score.setText(String.valueOf(Game.MATCH_POINTS));
            total.setText(
                    computeTotal(Game.MATCH_POINTS, multiplier[0])
            );
        });

        erase_button.setOnClickListener(l -> {
            String currentInput = score.getText().toString();
            String newInput = (currentInput.length() > 1) ? currentInput.substring(0, currentInput.length() - 1) : "0";

            score.setText(newInput);
            total.setText(
                    computeTotal(Integer.parseInt(newInput), multiplier[0])
            );

        });

        clear_button.setOnClickListener(l -> {
            score.setText("0");
            total.setText(
                    computeTotal(0, multiplier[0])
            );
        });

        ok.setOnClickListener(l -> {
            int inputScore = Integer.parseInt(score.getText().toString());
            game.addPointsForBothDuo(duo, inputScore, multiplier[0]);
            updateGame();
            addPopup.dismiss();
        });

        announce.setOnClickListener(l -> {
            int inputScore = Integer.parseInt(score.getText().toString());
            game.addPoints(duo, inputScore, multiplier[0], true);
            updateGame();
            addPopup.dismiss();
        });

        cancel.setOnClickListener(l -> addPopup.dismiss());

        Objects.requireNonNull(addPopup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addPopup.show();
    }

    private String computeTotal(int input, int multiplier) {
        return String.format("Total: %s (Adversaire: %s)", input * multiplier,
                (input >= 157) ? "0" : (Game.MAX_POINTS - input) * multiplier);
    }

    private void showParamPopup() {

        Button paramReturn = parametersPopup.findViewById(R.id.param_return);

        paramReturn.setOnClickListener(l -> {
            updateUI();
            parametersPopup.dismiss();
        });

        CheckBox touchPointsCheckBox = parametersPopup.findViewById(R.id.param_touch_points_checkBox);
        CheckBox showMarksCheckBox = parametersPopup.findViewById(R.id.param_marks_checkBox);
        CheckBox showGuideCheckBox = parametersPopup.findViewById(R.id.param_guide_checkBox);
        CheckBox bigScoreCheckBox = parametersPopup.findViewById(R.id.param_big_score_checkBox);

        touchPointsCheckBox.setChecked(getPreferences(MODE_PRIVATE).getBoolean(touchPointsKey, true));
        showMarksCheckBox.setChecked(getPreferences(MODE_PRIVATE).getBoolean(showMarksKey, true));
        showGuideCheckBox.setChecked(getPreferences(MODE_PRIVATE).getBoolean(showGuideKey, true));
        bigScoreCheckBox.setChecked(getPreferences(MODE_PRIVATE).getBoolean(bigScoreKey, false));

        touchPointsCheckBox.setOnClickListener(l -> {
            preferences.edit()
                    .putBoolean(touchPointsKey, touchPointsCheckBox.isChecked())
                    .apply();
        });

        showMarksCheckBox.setOnClickListener(l -> {
            preferences.edit()
                    .putBoolean(showMarksKey, showMarksCheckBox.isChecked())
                    .apply();
        });

        showGuideCheckBox.setOnClickListener(l -> {
            preferences.edit()
                    .putBoolean(showGuideKey, showGuideCheckBox.isChecked())
                    .apply();
        });

        bigScoreCheckBox.setOnClickListener(l -> {
            preferences.edit()
                    .putBoolean(bigScoreKey, bigScoreCheckBox.isChecked())
                    .apply();
        });

        Objects.requireNonNull(parametersPopup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        parametersPopup.show();

    }

    private void showStatisticPopup() {
        Button statReturn, reinitialise;
        RecyclerView recyclerView;
        MyAdapter myAdapter;
        TextView historicButt, announceButt, team0_announce, team1_announce;
        ConstraintLayout announceDisplay;

        announceDisplay = statisticPopup.findViewById(R.id.announce_display);
        team0_announce = statisticPopup.findViewById(R.id.announce_team0);
        team1_announce = statisticPopup.findViewById(R.id.announce_team1);

        historicButt = statisticPopup.findViewById(R.id.stat_historic);
        announceButt = statisticPopup.findViewById(R.id.stat_announce);

        recyclerView = statisticPopup.findViewById(R.id.recyclerView);

        myAdapter = new MyAdapter(this, game.getHistoric(), l -> {
            game.undoLastMove();
            recyclerView.setAdapter(new MyAdapter(this, game.getHistoric()));
        });

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        statReturn = statisticPopup.findViewById(R.id.stat_return);
        reinitialise = statisticPopup.findViewById(R.id.historic_reinitialise);

        statReturn.setOnClickListener(l -> {
            updateGame();
            statisticPopup.dismiss();
        });

        reinitialise.setOnClickListener(l -> {
            game.restart();
            updateGame();
            statisticPopup.dismiss();
        });

        historicButt.setOnClickListener(l -> {
            historicButt.setBackgroundColor(getResources().getColor(R.color.primary, getTheme()));
            announceButt.setBackgroundColor(getResources().getColor(R.color.primaryDark, getTheme()));

            recyclerView.setVisibility(View.VISIBLE);
            reinitialise.setVisibility(View.VISIBLE);

            announceDisplay.setVisibility(View.GONE);

        });

        announceButt.setOnClickListener(l -> {
            historicButt.setBackgroundColor(getResources().getColor(R.color.primaryDark, getTheme()));
            announceButt.setBackgroundColor(getResources().getColor(R.color.primary, getTheme()));

            recyclerView.setVisibility(View.GONE);
            reinitialise.setVisibility(View.GONE);

            team0_announce.setText(String.valueOf(duos[0].getTotalAnnounce()));
            team1_announce.setText(String.valueOf(duos[1].getTotalAnnounce()));

            announceDisplay.setVisibility(View.VISIBLE);

        });

        Objects.requireNonNull(statisticPopup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        statisticPopup.show();
    }

    private void updateGame() {
        updateInfosDisplay();
        updateUI();

        if (game.areBothOver()) {
            showDrawPopup();
        } else if (game.isOver()) {
            showVictoryPopup(game.getWinner());
        }

    }

    private void showVictoryPopup(PlayingEntity winner) {
        TextView title_view, message_view, butt1, restart;

        title_view = endPopup.findViewById(R.id.popup_title);
        message_view = endPopup.findViewById(R.id.popup_message);

        title_view.setText(String.format("Félicitation %s !", winner.getName()));
        message_view.setText(String.format("Bravo à %s pour cette belle victoire !", winner.getName()));

        butt1 = endPopup.findViewById(R.id.popup_button_1);
        restart = endPopup.findViewById(R.id.popup_button_2);

        butt1.setVisibility(View.GONE);
        butt1.setEnabled(false);

        restart.setText("Recommencer");
        restart.setOnClickListener(l -> {
            game.restart();
            updateGame();
            endPopup.dismiss();
        });

        Objects.requireNonNull(endPopup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        endPopup.show();
    }

    private void showDrawPopup() {
        TextView title_view, message_view, team0_butt, team1_butt;

        title_view = endPopup.findViewById(R.id.popup_title);
        message_view = endPopup.findViewById(R.id.popup_message);

        title_view.setText("Les deux équipes ont passé le score final");
        message_view.setText("Quelle équipe a passé le score en premier ?");

        team0_butt = endPopup.findViewById(R.id.popup_button_1);
        team1_butt = endPopup.findViewById(R.id.popup_button_2);

        team0_butt.setText(duos[0].getName());
        team0_butt.setOnClickListener(l -> {
            game.setWinner(duos[0]);
            updateGame();
        });

        team1_butt.setText(duos[1].getName());
        team1_butt.setOnClickListener(l -> {
            game.setWinner(duos[1]);
            updateGame();
        });

        Objects.requireNonNull(endPopup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        endPopup.show();
    }

    private void updateInfosDisplay() {
        endScoreView.setText(String.valueOf(game.getEndScore()));

        team0_name.setText(duos[0].getName());
        setTeam0Score(duos[0].getTotalPoints());
        team0_20.setText(tallyMarks(duos[0].getPointsDisplay().getNb20()));
        team0_50.setText(xMarks(duos[0].getPointsDisplay().getNb50()));
        team0_100.setText(tallyMarks(duos[0].getPointsDisplay().getNb100()));
        team0_rest.setText(String.valueOf(duos[0].getPointsDisplay().getRest()));
        team0_V.setText(vMarks(duos[0].getPointsDisplay().getNbV()));

        team1_name.setText(duos[1].getName());
        setTeam1Score(duos[1].getTotalPoints());
        team1_20.setText(tallyMarks(duos[1].getPointsDisplay().getNb20()));
        team1_50.setText(xMarks(duos[1].getPointsDisplay().getNb50()));
        team1_100.setText(tallyMarks(duos[1].getPointsDisplay().getNb100()));
        team1_rest.setText(String.valueOf(duos[1].getPointsDisplay().getRest()));
        team1_V.setText(vMarks(duos[1].getPointsDisplay().getNbV()));
    }

    private void updateUI() {

        boolean touchPoints = getPreferences(MODE_PRIVATE).getBoolean(touchPointsKey, true);

        team0_20.setEnabled(touchPoints);
        team0_50.setEnabled(touchPoints);
        team0_100.setEnabled(touchPoints);
        team0_rest.setEnabled(touchPoints);

        team1_20.setEnabled(touchPoints);
        team1_50.setEnabled(touchPoints);
        team1_100.setEnabled(touchPoints);
        team1_rest.setEnabled(touchPoints);

        if (getPreferences(MODE_PRIVATE).getBoolean(bigScoreKey, false)) {
            team0_score.setVisibility(View.INVISIBLE);
            team0_big_score.setVisibility(View.VISIBLE);

            team1_score.setVisibility(View.INVISIBLE);
            team1_big_score.setVisibility(View.VISIBLE);

            setMarksColor(getResources().getColor(R.color.primary, getTheme()));


        } else {
            team0_score.setVisibility(View.VISIBLE);
            team0_big_score.setVisibility(View.INVISIBLE);

            team1_score.setVisibility(View.VISIBLE);
            team1_big_score.setVisibility(View.INVISIBLE);

            setMarksColor(Color.WHITE);
        }

        int showGuideVisibility = (getPreferences(MODE_PRIVATE).getBoolean(showGuideKey, true)) ? View.VISIBLE : View.INVISIBLE;

        guides[0].setVisibility(showGuideVisibility);
        guides[1].setVisibility(showGuideVisibility);

        int showMarksVisibility = (getPreferences(MODE_PRIVATE).getBoolean(showMarksKey, true)) ? View.VISIBLE : View.INVISIBLE;

        team0_20.setVisibility(showMarksVisibility);
        team0_50.setVisibility(showMarksVisibility);
        team0_100.setVisibility(showMarksVisibility);
        team0_rest.setVisibility(showMarksVisibility);
        team0_V.setVisibility(showMarksVisibility);

        team1_20.setVisibility(showMarksVisibility);
        team1_50.setVisibility(showMarksVisibility);
        team1_100.setVisibility(showMarksVisibility);
        team1_rest.setVisibility(showMarksVisibility);
        team1_V.setVisibility(showMarksVisibility);
    }

    private void setTeam0Score(int score) {
        team0_score.setText(String.valueOf(score));
        team0_big_score.setText(String.valueOf(score));
    }

    private void setTeam1Score(int score) {
        team1_score.setText(String.valueOf(score));
        team1_big_score.setText(String.valueOf(score));
    }

    private void setMarksColor(int color) {
        team0_20.setTextColor(color);
        team0_50.setTextColor(color);
        team0_100.setTextColor(color);
        team0_rest.setTextColor(color);
        team0_V.setTextColor(color);

        team1_20.setTextColor(color);
        team1_50.setTextColor(color);
        team1_100.setTextColor(color);
        team1_rest.setTextColor(color);
        team1_V.setTextColor(color);
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

    private class GamePopup extends Dialog {
        private boolean backEnable;

        public GamePopup(@NonNull Context context, boolean backEnable) {
            super(context);
            this.backEnable = backEnable;
        }

        public GamePopup(@NonNull Context context) {
            this(context, true);
        }

        @Override
        public void onBackPressed() {
            if (backEnable) {
                super.onBackPressed();
                updateGame();
            }
        }
    }
}
