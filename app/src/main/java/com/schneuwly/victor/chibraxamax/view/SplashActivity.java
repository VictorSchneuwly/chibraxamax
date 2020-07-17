package com.schneuwly.victor.chibraxamax.view;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.controller.GameActivity;
import com.schneuwly.victor.chibraxamax.controller.RestartGameActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        boolean gameInProgress = getSharedPreferences(GameActivity.IN_PROGRESS, MODE_PRIVATE).getBoolean(GameActivity.IN_PROGRESS, false);

        new Handler().postDelayed(() -> {
            //TODO: A changer quand menu plus fourni
            //Intent startIntent = new Intent(SplashActivity.this, MenuActivity.class);
            Intent startIntent = new Intent(SplashActivity.this,
                    (gameInProgress)
                            ? RestartGameActivity.class
                            : GameActivity.class
            );
            startActivity(startIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, SPLASH_SCREEN_TIME);
    }
}
