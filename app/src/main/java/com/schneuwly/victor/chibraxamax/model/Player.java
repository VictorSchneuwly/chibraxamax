package com.schneuwly.victor.chibraxamax.model;

/**
 * A user
 *
 * @author Victor Schneuwly
 */
public class Player{
    private final String userName;
    private final UserStatistics stat;

    public Player(String userName, UserStatistics stat) {
        this.userName = userName;
        this.stat = stat;

    }

    public String getUserName() {
        return userName;
    }

    private class PlayerStatistics extends Statistics{

        public PlayerStatistics(int wins, int losses) {
            super(wins, losses);
        }
    }
}
