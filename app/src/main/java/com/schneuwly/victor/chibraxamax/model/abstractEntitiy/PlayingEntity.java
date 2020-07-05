package com.schneuwly.victor.chibraxamax.model.abstractEntitiy;

import com.schneuwly.victor.chibraxamax.model.Record;

import java.util.Objects;

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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingEntity that = (PlayingEntity) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
