package com.schneuwly.victor.chibraxamax.controller;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.schneuwly.victor.chibraxamax.R;

public class GameActivity extends AppCompatActivity {
    private TextView[] tallyBarsView = new TextView[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tallyBarsView[0] = findViewById(R.id.team1_20);
        //tallyBarsView[1] = findViewById(R.id.team1_50);
        //tallyBarsView[2] = findViewById(R.id.team1_100);
        //tallyBarsView[3] = findViewById(R.id.team2_20);
        //tallyBarsView[4] = findViewById(R.id.team2_50);
        //tallyBarsView[5] = findViewById(R.id.team2_100);
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
}
