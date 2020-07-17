package com.schneuwly.victor.chibraxamax.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * A historic
 *
 * @author Victor Schneuwly
 */
public class Historic<T> {
    public final static String SEPARATOR = "<--->";
    private final List<T> historic;

    public Historic() {
        historic = new ArrayList<>();
    }

    protected Historic(List<T> historic) {
        this.historic = new ArrayList<>(historic);
    }


    public void add(T entry) {
        historic.add(entry);
    }


    public T get(int i) {
        if (i >= 0) {
            return historic.get(i);
        } else {
            return historic.get(size() + i);
        }
    }

    public T getLastEntry() {
        return get(-1);
    }


    public void remove(int i) {
        historic.remove(i);
    }


    public int size() {
        return historic.size();
    }


    public boolean isEmpty() {
        return historic.isEmpty();
    }


    public void clear() {
        historic.clear();
    }


    public Historic<T> clone() {
        return new Historic<>(historic);
    }

    public String getSave() {
        StringBuilder sb = new StringBuilder();
        for (T entry : historic) {
            sb.append(entry.toString())
                    .append(SEPARATOR);
        }

        return sb.toString();
    }

}
