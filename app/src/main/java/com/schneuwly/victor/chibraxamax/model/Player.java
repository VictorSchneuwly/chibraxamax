package com.schneuwly.victor.chibraxamax.model;

import java.util.Objects;

/**
 * A user
 *
 * @author Victor Schneuwly
 */
public class Player{
    private final String userName;
    private int wins, losses, totalAnnounce;

    public Player(String userName, int wins, int losses, int totalAnnounce) {
        this.userName = userName;
        this.wins = wins;
        this.losses = losses;
        this.totalAnnounce = totalAnnounce;
    }

    public String getUserName() {
        return userName;
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

    public int getTotalAnnounce() {
        return totalAnnounce;
    }

    public double getAnnouncePerGame() {
        return totalAnnounce * 1d / getTotalGamesPlayed();
    }

    void addAnnounce(int announce) {
        totalAnnounce += announce;
    }

    @Override
    public String toString() {
        return "Player {" +
                "userName = '" + userName + '\'' +
                ", P = " + getTotalGamesPlayed() +
                ", V = " + wins +
                ", D = " + losses +
                ", V% = " + getWinPercentage() + '%' +
                ", TA = " + totalAnnounce +
                ", AP = " + getAnnouncePerGame() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return wins == player.wins &&
                losses == player.losses &&
                totalAnnounce == player.totalAnnounce &&
                userName.equals(player.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, wins, losses, totalAnnounce);
    }
}
