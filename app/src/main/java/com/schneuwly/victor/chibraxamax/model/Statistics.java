package com.schneuwly.victor.chibraxamax.model;

import java.util.Objects;

/**
 * Statistics
 *
 * @author Victor Schneuwly
 */
public abstract class Statistics {
    private int wins, losses;

    public Statistics(int wins, int losses) {
        this.wins = wins;
        this.losses = losses;
    }

    public int getWins() {
        return wins;
    }

    void addWin() {
        ++wins;
    }

    public int getLosses() {
        return losses;
    }

    void addLosses() {
        ++losses;
    }

    public int getTotalGamesPlayed() {
        return wins + losses;
    }

    public double getWinPercentage() {
        return 100d * wins / getTotalGamesPlayed();
    }
}
