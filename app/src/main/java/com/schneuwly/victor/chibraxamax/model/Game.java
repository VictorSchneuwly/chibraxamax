package com.schneuwly.victor.chibraxamax.model;

import java.util.*;

/**
 * A game of Chibre
 *
 * @author Victor Schneuwly
 */
public class Game {
    public final static int MAX_POINTS = 157;
    public final static int MATCH_POINTS = 257;
    public final static String END_SCORE_KEY = "endScore";
    public final static String HISTORIC_KEY = "historic";
    public final static String DUO_0_NAME_KEY = "duo0Name";
    public final static String DUO_1_NAME_KEY = "duo1Name";
    public final static String DUO_0_SCORE_KEY = "duo0Score";
    public final static String DUO_1_SCORE_KEY = "duo1Score";
    public final static String DUO_0_ANNOUNCE_KEY = "duo0Announce";
    public final static String DUO_1_ANNOUNCE_KEY = "duo1Announce";
    public final static String DUO_0_DISPLAY_KEY = "duo0Display";
    public final static String DUO_1_DISPLAY_KEY = "duo1Display";

    private final Duo[] duos;
    private final Historic<HistoricEntry> historic;

    private Duo winner;
    private boolean over, bothOver;
    private int endScore;

    private Game(Duo duo1, Duo duo2, int endScore, Historic<HistoricEntry> historic) {
        this.endScore = endScore;
        duos = new Duo[2];
        duos[0] = duo1;
        duos[1] = duo2;
        over = false;
        bothOver = false;
        this.historic = historic;
    }

    public Game(Duo duo1, Duo duo2, int endScore) {
        this(duo1, duo2, endScore, new Historic<>());
    }

    public static Game restore(Map<String, ?> savedMap) {
        Map<String, Object> save = new HashMap<>(savedMap);

        int savedEndScore = (int) save.get(END_SCORE_KEY);

        Historic<HistoricEntry> savedHistoric = new Historic<>();

        String savedEntry = (String) save.get(HISTORIC_KEY);

        if (savedEntry.length() > 0) {
            for (String line : savedEntry.split(Historic.SEPARATOR)) {
                savedHistoric.add(HistoricEntry.restore(line));
            }
        }

        String savedNamedDuo0 = (String) save.get(DUO_0_NAME_KEY);
        String savedNamedDuo1 = (String) save.get(DUO_1_NAME_KEY);
        int savedScoreDuo0 = (int) save.get(DUO_0_SCORE_KEY);
        int savedScoreDuo1 = (int) save.get(DUO_1_SCORE_KEY);
        int savedAnnounceDuo0 = (int) save.get(DUO_0_ANNOUNCE_KEY);
        int savedAnnounceDuo1 = (int) save.get(DUO_1_ANNOUNCE_KEY);
        String savedDisplayDuo0 = (String) save.get(DUO_0_DISPLAY_KEY);
        String savedDisplayDuo1 = (String) save.get(DUO_1_DISPLAY_KEY);

        return new Game(
                Duo.restore(savedNamedDuo0, savedDisplayDuo0, savedScoreDuo0, savedAnnounceDuo0),
                Duo.restore(savedNamedDuo1, savedDisplayDuo1, savedScoreDuo1, savedAnnounceDuo1),
                savedEndScore,
                savedHistoric
        );
    }

    public Map<String, Object> saveGame() {
        Map<String, Object> scoreSave = new HashMap<>();
        scoreSave.put(END_SCORE_KEY, endScore);
        scoreSave.put(HISTORIC_KEY, historic.getSave());
        scoreSave.put(DUO_0_NAME_KEY, duos[0].getName());
        scoreSave.put(DUO_1_NAME_KEY, duos[1].getName());
        scoreSave.put(DUO_0_SCORE_KEY, duos[0].getTotalPoints());
        scoreSave.put(DUO_1_SCORE_KEY, duos[1].getTotalPoints());
        scoreSave.put(DUO_0_ANNOUNCE_KEY, duos[0].getTotalAnnounce());
        scoreSave.put(DUO_1_ANNOUNCE_KEY, duos[1].getTotalAnnounce());
        scoreSave.put(DUO_0_DISPLAY_KEY, duos[0].getPointsDisplay().save());
        scoreSave.put(DUO_1_DISPLAY_KEY, duos[1].getPointsDisplay().save());


        return Collections.unmodifiableMap(scoreSave);
    }

    public Duo[] getDuos() {
        return duos;
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

            historic.add(new HistoricEntry(points * multiplier, otherPoints * multiplier, false));

        } else if (inputDuo.equals(duos[1])) {
            duos[1].addPoints(points, multiplier, false);

            int otherPoints = (points >= MAX_POINTS) ? 0 : MAX_POINTS - points;
            duos[0].addPoints(otherPoints, multiplier, false);

            historic.add(new HistoricEntry(otherPoints * multiplier, points * multiplier, false));
        }

        checkForWinner();
    }

    public void addPoints(Duo duo, int points, int multiplier, boolean announce) throws IllegalArgumentException {
        addPoints(duo, points, multiplier, announce, true);
    }

    private void addPoints(Duo duo, int points, int multiplier, boolean announce, boolean addToHistoric) throws IllegalArgumentException {

        if (!(contains(duo))) {
            throw new IllegalArgumentException("Duo not in the game.");
        } else if (duo.equals(duos[0])) {
            duos[0].addPoints(points, multiplier, announce);

            if (addToHistoric) {
                historic.add(new HistoricEntry(points * multiplier, 0, announce));
            }

            checkWinner(duos[0]);

        } else if (duo.equals(duos[1])) {
            duos[1].addPoints(points, multiplier, announce);

            if (addToHistoric) {
                historic.add(new HistoricEntry(0, points * multiplier, announce));
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

    public Historic<HistoricEntry> getHistoric() {
        return historic.copy();
    }

    public void undoLastMove() {
        HistoricEntry lastEntry = historic.removeLastEntry();

        addPoints(duos[0], -lastEntry.first(), 1, lastEntry.isAnnounce(), false);
        addPoints(duos[1], -lastEntry.second(), 1, lastEntry.isAnnounce(), false);
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

    public static class HistoricEntry {
        private int team0Pts, team1Pts;
        private boolean announce;

        public HistoricEntry(int team0Pts, int team1Pts, boolean announce) {
            this.team0Pts = team0Pts;
            this.team1Pts = team1Pts;
            this.announce = announce;
        }

        public static HistoricEntry restore(String save) {
            String[] savedArray = save.split(";");

            return new HistoricEntry(
                    Integer.parseInt(savedArray[0]),
                    Integer.parseInt(savedArray[1]),
                    Boolean.parseBoolean(savedArray[2])
            );
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

        @Override
        public String toString() {
            return String.format("%s;%s;%s", team0Pts, team1Pts, announce);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HistoricEntry that = (HistoricEntry) o;
            return team0Pts == that.team0Pts &&
                    team1Pts == that.team1Pts &&
                    announce == that.announce;
        }

        @Override
        public int hashCode() {
            return Objects.hash(team0Pts, team1Pts, announce);
        }
    }
}
