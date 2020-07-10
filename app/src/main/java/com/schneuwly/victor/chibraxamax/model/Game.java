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
    private final Historic historic;

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
        historic = new GameHistoric();
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
            duos[0].addPoints(points, multiplier, false);

            int otherPoints = (points >= MAX_POINTS) ? 0 : MAX_POINTS - points;
            duos[1].addPoints(otherPoints, multiplier, false);

            historic.add(new GameHistoric.Entry(points * multiplier, otherPoints * multiplier, false));

        } else if (inputDuo.equals(duos[1])) {
            duos[1].addPoints(points, multiplier, false);

            int otherPoints = (points >= MAX_POINTS) ? 0 : MAX_POINTS - points;
            duos[0].addPoints(otherPoints, multiplier, false);

            historic.add(new GameHistoric.Entry(otherPoints * multiplier, points * multiplier, false));
        }

        checkForWinner();
    }

    public void addPoints(Duo duo, int points, int multiplier, boolean announce) throws IllegalArgumentException {
        addPoints(duo, points, multiplier, true, announce);
    }

    private void addPoints(Duo duo, int points, int multiplier, boolean addToHistoric, boolean announce) throws IllegalArgumentException {

        if (!(contains(duo))) {
            throw new IllegalArgumentException("Duo not in the game.");
        } else if (duo.equals(duos[0])) {
            duos[0].addPoints(points, multiplier, announce);

            if (addToHistoric) {
                historic.add(new GameHistoric.Entry(points * multiplier, 0, announce));
            }

            checkWinner(duos[0]);

        } else if (duo.equals(duos[1])) {
            duos[1].addPoints(points, multiplier, announce);

            if (addToHistoric) {
                historic.add(new GameHistoric.Entry(0, points * multiplier, announce));
            }

            checkWinner(duos[1]);
        }
    }

    public void add1Point(Duo duo, boolean announce) throws IllegalArgumentException {
        addPoints(duo, 1, 1, announce);
    }

    public void add20Points(Duo duo, boolean announce) throws IllegalArgumentException {
        addPoints(duo, 20, 1, announce);
    }

    public void add50Points(Duo duo, boolean announce) throws IllegalArgumentException {
        addPoints(duo, 50, 1, announce);
    }

    public void add100Points(Duo duo, boolean announce) throws IllegalArgumentException {
        addPoints(duo, 100, 1, announce);
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
        historic.clear();
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

    public Historic getHistoric() {
        return historic.clone();
    }

    public void undoLastMove() {
        Historic.Entry<Integer> lastEntry = historic.;

        addPoints(duos[0], -lastEntry.first(), 1, false, lastEntry.);
        addPoints(duos[1], -lastEntry.second(), 1, false, false);

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

//TODO: tout refaire
    public static class GameHistoric implements Historic<GameHistoric.Entry> {
        private final List<GameHistoric.Entry> historic;

        public GameHistoric() {
            historic = new ArrayList<>();
        }

        private GameHistoric(List<Historic.Entry<Integer>> historic){
            this.historic = Collections.unmodifiableList(historic);
        }

        @Override
        public void add(Historic.Entry<Integer> entry) {
            historic.add((GameHistoric.Entry) entry);
        }

        @Override
        public Historic.Entry<Integer> get(int i) {
            return historic.get(i);
        }

        @Override
        public void remove(int i) {
            historic.remove(i);
        }

        @Override
        public int size() {
            return historic.size();
        }

        @Override
        public boolean isEmpty() {
            return historic.isEmpty();
        }

        @Override
        public void clear() {
            historic.clear();
        }

        @Override
        public Historic<Integer> clone() {
            return new GameHistoric(historic);
        }


        public static class Entry{
            private int team0Pts, team1Pts;
            private boolean announce;

            public Entry(int team0Pts, int team1Pts, boolean announce) {
                this.team0Pts = team0Pts;
                this.team1Pts = team1Pts;
                this.announce = announce;
            }



            public boolean isAnnounce() {
                return announce;
            }
            public int first() {
                return team0Pts;
            }
            public int second() {
                return team1Pts;
            }
        }
    }

}
