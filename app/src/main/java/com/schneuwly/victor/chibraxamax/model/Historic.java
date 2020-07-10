package com.schneuwly.victor.chibraxamax.model;

/**
 * A historic
 *
 * @author Victor Schneuwly
 */
public interface Historic<T> {

    void add(T entry);
    T get(int i);
    void remove(int i);
    int size();
    boolean isEmpty();
    void clear();
    Historic<T> clone();

}
