package com.schneuwly.victor.chibraxamax.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.*;

/**
 * A historic
 *
 * @author Victor Schneuwly
 */
public class Historic<E> implements List<E> {
    public final static String SEPARATOR = "<--->";
    private final List<E> historic;

    public Historic() {
        historic = new ArrayList<>();
    }

    private Historic(List<E> historic) {
        this.historic = new ArrayList<>(historic);
    }

    @Override
    public boolean add(E entry) {
        return historic.add(entry);
    }

    @Override
    public boolean remove(@Nullable Object o) {
        return historic.remove(o);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return historic.containsAll(c);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends E> c) {
        return historic.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NonNull Collection<? extends E> c) {
        return historic.addAll(index, c);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        return historic.removeAll(c);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        return historic.retainAll(c);
    }

    @Override
    public E get(int i) {
        if (i >= 0) {
            return historic.get(i);
        } else {
            return historic.get(size() + i);
        }
    }

    @Override
    public E set(int index, E element) {
        return historic.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        historic.add(index, element);
    }

    public E getLastEntry() {
        return get(-1);
    }

    @Override
    public E remove(int i) {
        return historic.remove(i);
    }

    @Override
    public int indexOf(@Nullable Object o) {
        return historic.indexOf(o);
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return historic.lastIndexOf(o);
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator() {
        return historic.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator(int index) {
        return historic.listIterator(index);
    }

    @NonNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return historic.subList(fromIndex, toIndex);
    }

    @Override
    public int size() {
        return historic.size();
    }

    @Override
    public boolean isEmpty() {
        return historic.isEmpty();
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return historic.contains(o);
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return historic.iterator();
    }

    @Nullable
    @Override
    public Object[] toArray() {
        return historic.toArray();
    }

    @Override
    public <T> T[] toArray(@Nullable T[] a) {
        return historic.toArray(a);
    }

    @Override
    public void clear() {
        historic.clear();
    }

    public Historic<E> copy() {
        return new Historic<>(historic);
    }

    public String getSave() {
        StringBuilder sb = new StringBuilder();
        for (E entry : historic) {
            sb.append(entry.toString())
                    .append(SEPARATOR);
        }

        return sb.toString();
    }

}
