package com.schneuwly.victor.chibraxamax.model;

/**
 * A game of Chibre
 *
 * @author Victor Schneuwly
 */
public class Game {
    public static int MAX_POINTS = 157;

    private final Duo[] duos;
    private final int endScore;

    private Duo winner;
    private boolean over, draw;

    public Game(Duo duo1, Duo duo2, int endScore) {
        this.endScore = endScore;
        duos = new Duo[2];
        duos[0] = duo1;
        duos[1] = duo2;
        over = false;
        draw = false;
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

    public void addPoints(Duo duo, int points) throws IllegalArgumentException {
        if (!(contains(duo))) {
            throw new IllegalArgumentException("Duo not in the game.");
        } else if (duo.equals(duos[0])) {
            duos[0].addPoints(points);
            duos[1].addPoints(MAX_POINTS - points);
        } else if (duo.equals(duos[1])) {
            duos[1].addPoints(points);
            duos[0].addPoints(MAX_POINTS - points);
        }

        checkForWinner();
    }

    public void addAnnounce(Duo duo, int announce) throws IllegalArgumentException {
        if (!(contains(duo))) {
            throw new IllegalArgumentException("Duo not in the game.");
        } else {
            duo.addPoints(announce);
            checkWinner(duo);
        }
    }

    public boolean isOver() {
        return over;
    }

    public boolean isDrawn() {
        return draw;
    }

    public void restart() {
        winner = null;
        for (Duo duo : duos) {
            duo.reinitialisePoints();
        }
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

            if (duos[0].getTotalPoints() == duos[1].getTotalPoints()) {
                draw = true;
            } else {
                winner = (duos[0].getTotalPoints() < duos[1].getTotalPoints()) ? duos[1] : duos[0];
                addWin();
                over = true;
            }

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
