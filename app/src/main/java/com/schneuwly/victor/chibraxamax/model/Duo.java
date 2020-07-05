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

    public Duo(Player player1, Player player2, Record record, String teamName) {
        super(teamName, record);
        players[0] = player1;
        players[1] = player2;
        Arrays.sort(players, Comparator.comparing(Player::getName));

        pointsDisplay = new PointsDisplay();
        totalPoints = 0;
    }

    public Duo(Player player1, Player player2, Record record) {
        this(player1, player2, record, "");
        setName(players[0].getName() + " - " + players[1].getName());
    }

    public Duo(Player player1, Player player2, String teamName) {
        this(player1, player2, new Record(0, 0), teamName);
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

        private int nbV, nb20, nb50, nb100, restToDisplay;

        private PointsDisplay() {
            reinitialise();
        }

        public void reinitialise() {
            nbV = 0;
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

        public int getNbV() {
            return nbV;
        }

        private void calculatePointsToDisplay(int totalPoints) {
            int pointsToCompute = Math.abs(totalPoints);
            int sign = (totalPoints < 0) ? -1 : 1;
            while (pointsToCompute >= 100) {
                pointsToCompute -= 100;
                nb100 += sign;

                if (nb100 > 20) {
                    nb100 = 16;
                    ++nbV;
                }
            }

            while (pointsToCompute >= 50) {
                pointsToCompute -= 50;
                nb50 += sign;

                if (nb50 > 18) {
                    nb50 = 15;

                    ++nb100;
                    if (nb100 > 20) {
                        nb100 = 16;
                        ++nbV;
                    }

                    ++nb100;
                    if (nb100 > 20) {
                        nb100 = 16;
                        ++nbV;
                    }
                }
            }

            while (pointsToCompute >= 20) {
                pointsToCompute -= 20;
                nb20 += sign;

                if (nb20 > 35) {
                    nb20 = 26;

                    ++nb100;
                    if (nb100 > 20) {
                        nb100 = 16;
                        ++nbV;
                    }

                    ++nb100;
                    if (nb100 > 20) {
                        nb100 = 16;
                        ++nbV;
                    }
                }
            }

            computeRest(pointsToCompute, sign);

            System.out.printf("V:%s 100:%s 50:%s 20:%s Rest:%s%n", nbV, nb100, nb50, nb20, restToDisplay);

        }

        private void computeRest(int pointsToCompute, int sign) {
            if (sign == 1) {

                if (restToDisplay + pointsToCompute >= 20) {
                    restToDisplay += pointsToCompute - 20;
                    ++nb20;
                } else {
                    restToDisplay += pointsToCompute;
                }

            } else if (sign == -1) {
                /*
                if (restToDisplay - pointsToCompute < 0) {
                    restToDisplay += pointsToCompute - 20;
                    --nb20;
                } else {
                    restToDisplay -= pointsToCompute;
                }
                 */

                restToDisplay -= pointsToCompute;

            }
        }

    }
}
