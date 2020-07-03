package com.schneuwly.victor.chibraxamax.model;

import com.schneuwly.victor.chibraxamax.model.abstractEntitiy.PlayingEntity;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A duo of two players
 *
 * @author Victor Schneuwly
 */
public class Duo extends PlayingEntity {
    private final Player[] players = new Player[2];
    private final PointsDisplay pointsDisplay;
    private int totalPoints;

    public Duo(Player player1, Player player2, Record record) {
        super("", record);
        players[0] = player1;
        players[1] = player2;
        Arrays.sort(players, Comparator.comparing(Player::getName));

        setName(players[0].getName() + " - " + players[1].getName());

        pointsDisplay = new PointsDisplay();
        totalPoints = 0;
    }

    public PointsDisplay getPointsDisplay() {
        return pointsDisplay;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public boolean contains(Player player) {
        return player.equals(players[0]) || player.equals(players[1]);
    }

    public void reinitialisePoints() {
        pointsDisplay.reinitialise();
        totalPoints = 0;
    }

    void addPoints(int points) {
        pointsDisplay.calculatePointsToDisplay(points);
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

    public class PointsDisplay {
        //TODO: Problème de suppression de l'historique.
        private int nb20, nb50, nb100, restToDisplay;

        private PointsDisplay() {
            reinitialise();
        }

        public void reinitialise() {
            nb20 = 0;
            nb50 = 0;
            nb100 = 0;
            restToDisplay = 0;
        }

        public int getNb20() {
            return nb20;
        }

        public int getNb50() {
            return nb50;
        }

        public int getNb100() {
            return nb100;
        }

        public int getRest() {
            return restToDisplay;
        }

        private void calculatePointsToDisplay(int totalPoints) {
            int pointsToCompute = totalPoints;
            while (pointsToCompute >= 100) {
                pointsToCompute -= 100;
                ++nb100;
            }

            while (pointsToCompute >= 50) {
                pointsToCompute -= 50;
                ++nb50;
            }

            while (pointsToCompute >= 20) {
                pointsToCompute -= 20;
                ++nb20;
            }

            computeRest(pointsToCompute);

        }

        private void computeRest(int pointsToCompute) {
            if (restToDisplay + pointsToCompute >= 20) {
                restToDisplay += pointsToCompute - 20;
                ++nb20;
            } else {
                restToDisplay += pointsToCompute;
            }
        }

    }
}
