package com.schneuwly.victor.chibraxamax.model;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A duo of two players
 *
 * @author Victor Schneuwly
 */
public class Duo extends Statistics.Holder {
    private final Player[] players = new Player[2];
    private int totalPoints;

    public Duo(Player player1, Player player2, Statistics statistics) {
        super(statistics);
        players[0] = player1;
        players[1] = player2;
        Arrays.sort(players, Comparator.comparing(Player::getUserName));
        totalPoints = 0;
    }

    public String getDuoName() {
        return players[0].getUserName() + " - " + players[1].getUserName();
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public boolean contains(Player player) {
        return player.equals(players[0]) || player.equals(players[1]);
    }

    public void reinitialisePoints() {
        totalPoints = 0;
    }

    void addPoints(int points) {
        totalPoints += points;
    }

    @Override
    public void addWin() {
        super.addWin();
        for (Player player : players) {
            player.addWin();
        }
    }

    @Override
    public void addLoss() {
        super.addLoss();
        for (Player player : players) {
            player.addLoss();
        }
    }
}
