package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<T> implements Set<T> {
    private int count = 0;
    private int modCount = 0;
    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        for (var item : set) {
            if (item == null) {
                if (value == null) {
                    return true;
                }
            } else {
                if (item.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            final int expectedModCount = modCount;
            int cursor = 0;
            @Override
            public boolean hasNext() {
                if (!(expectedModCount == modCount)) {
                    throw new ConcurrentModificationException();
                }
                return cursor < count;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return set.get(cursor++);
            }
        };
    }
}