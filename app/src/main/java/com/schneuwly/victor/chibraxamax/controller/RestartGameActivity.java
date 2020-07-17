package com.schneuwly.victor.chibraxamax.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.model.Game;

public class RestartGameActivity extends AppCompatActivity {
    public static final String RESTART = "restart";

    private TextView team0Name, team0Score, team1Name, team1Score;
    private Button newGame, continueGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart_game);

        SharedPreferences gameSave = getSharedPreferences("gameSave", MODE_PRIVATE);

        newGame = findViewById(R.id.new_game);
        continueGame = findViewById(R.id.continue_game);
        team0Name = findViewById(R.id.saved_team0_name);
        team0Score = findViewById(R.id.saved_team0_score);
        team1Name = findViewById(R.id.saved_team1_name);
        team1Score = findViewById(R.id.saved_team1_score);

        team0Name.setText(gameSave.getString(Game.DUO_0_NAME_KEY, getResources().getString(R.string.default_name_team0)));
        team0Score.setText(String.valueOf(gameSave.getInt(Game.DUO_0_SCORE_KEY, 0)));
        team1Name.setText(gameSave.getString(Game.DUO_1_NAME_KEY, getResources().getString(R.string.default_name_team1)));
        team1Score.setText(String.valueOf(gameSave.getInt(Game.DUO_1_SCORE_KEY, 0)));



        newGame.setOnClickListener(l -> {
            getPreferences(MODE_PRIVATE).edit()
                    .putBoolean(GameActivity.IN_PROGRESS, false)
                    .apply();

            startGame(false);
        });

        continueGame.setOnClickListener(l -> startGame(true));
    }

    private void startGame(boolean restart){
        Intent startIntent = new Intent(RestartGameActivity.this, GameActivity.class);
        startIntent.putExtra(RESTART, restart);
        startActivity(startIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
