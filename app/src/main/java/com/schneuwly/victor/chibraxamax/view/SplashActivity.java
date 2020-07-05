package com.schneuwly.victor.chibraxamax.view;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.controller.MenuActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Intent startIntent = new Intent(SplashActivity.this, MenuActivity.class);
            startActivity(startIntent);
            finish();
        }, SPLASH_SCREEN);
    }
}
