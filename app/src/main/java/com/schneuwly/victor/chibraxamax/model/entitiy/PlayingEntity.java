package com.schneuwly.victor.chibraxamax.model.entitiy;

import com.schneuwly.victor.chibraxamax.model.Record;

/**
 * A playing entity
 *
 * @author Victor Schneuwly
 */
public abstract class PlayingEntity extends Record.Holder {
    private String name;

    public PlayingEntity(String name, Record record) {
        super(record);
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}
