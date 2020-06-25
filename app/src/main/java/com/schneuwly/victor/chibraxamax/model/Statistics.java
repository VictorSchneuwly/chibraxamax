package com.schneuwly.victor.chibraxamax.model;

/**
 * Statistics
 *
 * @author Victor Schneuwly
 */
public class Statistics {
    private int wins, losses;

    public Statistics(int wins, int losses) {
        this.wins = wins;
        this.losses = losses;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTotalGamesPlayed() {
        return wins + losses;
    }

    public double getWinPercentage() {
        return 100d * wins / getTotalGamesPlayed();
    }

    private void addWin() {
        ++wins;
    }

    private void addLoss() {
        ++losses;
    }

    public abstract static class Holder{
        private final Statistics statistics;

        public Holder(Statistics statistics) {
            this.statistics = statistics;
        }

        public final Statistics getStatistics() {
            return statistics;
        }

        public void addWin(){
            statistics.addWin();
        }

        public void addLoss(){
            statistics.addLoss();
        }

    }
}
