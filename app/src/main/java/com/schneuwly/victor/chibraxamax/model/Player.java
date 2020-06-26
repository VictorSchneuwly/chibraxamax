package com.schneuwly.victor.chibraxamax.model;

import com.schneuwly.victor.chibraxamax.model.entitiy.PlayingEntity;

/**
 * A user
 *
 * @author Victor Schneuwly
 */
public class Player extends PlayingEntity {

    public Player(String userName, Record record) {
        super(userName, record);
    }
}
