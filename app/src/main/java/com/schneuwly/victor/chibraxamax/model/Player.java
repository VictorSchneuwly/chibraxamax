package com.schneuwly.victor.chibraxamax.model;

import com.schneuwly.victor.chibraxamax.model.abstractEntitiy.PlayingEntity;

/**
 * A user
 *
 * @author Victor Schneuwly
 */
public class Player extends PlayingEntity {

    public Player(String userName, Record record) {
        super(userName, record);
    }

    public Player(String userName) {
        this(userName, new Record(0,0));
    }

    public Player() {
        this("joueur", new Record(0,0));
    }
}
