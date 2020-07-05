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

        //tallyBarsView[0] = findViewById(R.id.team1_20);
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

        if (toCompute == 1){
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
