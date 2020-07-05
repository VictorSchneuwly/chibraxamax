package com.schneuwly.victor.chibraxamax.controller;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.view.SplashActivity;

public class MenuActivity extends AppCompatActivity {
    private Button playButt, tournamentButt, paramButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        playButt = findViewById(R.id.play_button);
        playButt.setOnClickListener(l ->{
            Intent playIntent = new Intent(MenuActivity.this, GameActivity.class);
            startActivity(playIntent);
        });

        tournamentButt = findViewById(R.id.tournament_button);
        tournamentButt.setEnabled(false);

        paramButt = findViewById(R.id.param_button);
        paramButt.setEnabled(false);

    }
}
