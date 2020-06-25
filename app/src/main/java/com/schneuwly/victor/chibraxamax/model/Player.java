package com.schneuwly.victor.chibraxamax.model;

/**
 * A user
 *
 * @author Victor Schneuwly
 */
public class Player extends Statistics.Holder {
    private final String userName;

    public Player(String userName, Statistics statistics) {
        super(statistics);
        this.userName = userName;

    }

    public String getUserName() {
        return userName;
    }


}
