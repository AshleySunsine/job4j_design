package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;


public class EvenNumbersIterator implements Iterator {

    private final int[] data;
    private int point = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
    return nextPoint() >= 0;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

    private int nextPoint() {
        for (int i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                point = i;
                return i;
            }
        }
        return -1;
    }
}
