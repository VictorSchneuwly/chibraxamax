package com.schneuwly.victor.chibraxamax.model;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A game of Chibre
 *
 * @author Victor Schneuwly
 */
public class Game {
    public final static int MAX_POINTS = 157;
    public final static int MATCH_POINTS = 257;

    private final Duo[] duos;
    private final List<Pair<Integer, Integer>> historic;

    private Duo winner;
    private boolean over, bothOver;
    private int endScore;

    public Game(Duo duo1, Duo duo2, int endScore) {
        this.endScore = endScore;
        duos = new Duo[2];
        duos[0] = duo1;
        duos[1] = duo2;
        over = false;
        bothOver = false;
        historic = new ArrayList<>();
    }

    public boolean contains(Duo duo) {
        return duo.equals(duos[0]) || duo.equals(duos[1]);
    }

    public boolean containsPlayer(Player player) {
        for (Duo duo : duos) {
            if (duo.contains(player))
                return true;
        }
        return false;
    }

    public void addPointsForBothDuo(Duo inputDuo, int points, int multiplier) throws IllegalArgumentException {
        if (!(contains(inputDuo))) {
            throw new IllegalArgumentException("Duo not in the game.");
        } else if (inputDuo.equals(duos[0])) {
            duos[0].addPoints(points, multiplier);

            int otherPoints = (points >= MAX_POINTS) ? 0 : MAX_POINTS - points;
            duos[1].addPoints(otherPoints, multiplier);

            historic.add(new Pair<>(points * multiplier, otherPoints * multiplier));

        } else if (inputDuo.equals(duos[1])) {
            duos[1].addPoints(points, multiplier);

            int otherPoints = (points >= MAX_POINTS) ? 0 : MAX_POINTS - points;
            duos[0].addPoints(otherPoints, multiplier);

            historic.add(new Pair<>(otherPoints * multiplier, points * multiplier));
        }

        checkForWinner();
    }

    public void addPoints(Duo duo, int points, int multiplier) throws IllegalArgumentException {

        if (!(contains(duo))) {
            throw new IllegalArgumentException("Duo not in the game.");
        } else if (duo.equals(duos[0])) {
            duos[0].addPoints(points, multiplier);
            historic.add(new Pair<>(points * multiplier, 0));

            checkWinner(duos[0]);

        } else if (duo.equals(duos[1])) {
            duos[1].addPoints(points, multiplier);
            historic.add(new Pair<>(0, points * multiplier));

            checkWinner(duos[1]);
        }
    }

    public void add1Point(Duo duo) throws IllegalArgumentException {
        addPoints(duo, 1,1);
    }

    public void add20Points(Duo duo) throws IllegalArgumentException {
        addPoints(duo, 20,1);
    }

    public void add50Points(Duo duo) throws IllegalArgumentException {
        addPoints(duo, 50,1);
    }

    public void add100Points(Duo duo) throws IllegalArgumentException {
        addPoints(duo, 100,1);
    }

    public void setEndScore(int endScore) {
        this.endScore = endScore;
        checkForWinner();
    }

    public int getEndScore() {
        return endScore;
    }

    public boolean isOver() {
        return over;
    }

    public boolean areBothOver() {
        return bothOver;
    }

    public Duo getWinner() {
        return winner;
    }

    public void restart() {
        winner = null;
        over = false;
        bothOver = false;
        for (Duo duo : duos) {
            duo.reinitialisePoints();
        }
    }

    public void setWinner(Duo winner) {
        this.winner = winner;
        bothOver = false;
        over = true;
        addWin();
    }

    public List<Pair<Integer, Integer>> getHistoric() {
        return Collections.unmodifiableList(historic);
    }

    public void undoLastMove() {
        Pair<Integer, Integer> lastPair = historic.get(historic.size() - 1);

        addPoints(duos[0], -lastPair.first,1);
        addPoints(duos[1], -lastPair.second,1);

        historic.remove(historic.size() - 1);
    }

    private void checkWinner(Duo duo) {
        if (duo.getTotalPoints() >= endScore) {
            over = true;
            winner = duo;
            addWin();
        }
    }

    private void checkForWinner() {
        if (duos[0].getTotalPoints() >= endScore && duos[1].getTotalPoints() >= endScore) {

            bothOver = true;

        } else {

            for (Duo duo : duos) {
                checkWinner(duo);
            }

        }
    }

    private void addWin() {
        if (winner.equals(duos[0])) {
            duos[0].addWin();
            duos[1].addLoss();
        } else if (winner.equals(duos[1])) {
            duos[1].addWin();
            duos[0].addLoss();
        }
    }

}
