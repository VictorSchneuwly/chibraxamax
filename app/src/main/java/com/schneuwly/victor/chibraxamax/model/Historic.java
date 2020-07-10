package com.schneuwly.victor.chibraxamax.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A historic
 *
 * @author Victor Schneuwly
 */
public class Historic<T> {

    private final List<T> historic;

    public Historic() {
        historic = new ArrayList<>();
    }

    private Historic(List<T> historic){
        this.historic = Collections.unmodifiableList(historic);
    }

    
    public void add(T entry) {
        historic.add(entry);
    }

    
    public T get(int i) {
        return historic.get(i);
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


}
