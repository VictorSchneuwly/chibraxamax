package com.schneuwly.victor.chibraxamax.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A duo of two users
 *
 * @author Victor Schneuwly
 */
public class Duo {
    private final Player[] players;
    private int totalPoints;

    public Duo(Player player1, Player player2) {
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        totalPoints = 0;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    void addPoints(int points) {
        totalPoints += points;
    }

    void addAnnounce(Player player, int announce) throws IllegalArgumentException {
        if (!(player.equals(players[0]) || player.equals(players[1])))
            throw new IllegalArgumentException("Player not in the Duo");

        addPoints(announce);
        player.addAnnounce(announce);
    }

    void win() {
        for (Player player : players) {
            player.addWin();
        }
    }

    void lose() {
        for (Player player : players) {
            player.addLosses();
        }
    }

    public boolean contains(Player player){
        return player.equals(players[0]) || player.equals(players[1]);
    }

    @Override
    public String toString() {
        return "Duo { " +
                "player 1 = " + players[0] +
                "player 2 = " + players[1] +
                ", totalPoints = " + totalPoints +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duo duo = (Duo) o;
        return totalPoints == duo.totalPoints &&
                Arrays.equals(players, duo.players);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(totalPoints);
        result = 31 * result + Arrays.hashCode(players);
        return result;
    }
}
