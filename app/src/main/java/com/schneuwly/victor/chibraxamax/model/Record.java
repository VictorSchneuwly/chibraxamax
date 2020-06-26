package com.schneuwly.victor.chibraxamax.model;

/**
 * Record
 *
 * @author Victor Schneuwly
 */
public class Record {
    private int wins, losses;

    public Record(int wins, int losses) {
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
        private final Record record;

        public Holder(Record record) {
            this.record = record;
        }

        public final Record getRecord() {
            return record;
        }

        public void addWin(){
            record.addWin();
        }

        public void addLoss(){
            record.addLoss();
        }

    }
}
