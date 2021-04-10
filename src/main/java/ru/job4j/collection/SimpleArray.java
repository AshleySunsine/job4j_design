package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
   private int modCount = 0;
   private Object[] mainModel;
   private int count = 0;

    public SimpleArray() {
        this.mainModel = new Object[3];
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return (T) mainModel[index];
    }

    public void add(T model) {
        if (!((count + 1) < mainModel.length)) {
            mainModel = Arrays.copyOf(mainModel, mainModel.length * 2);
        }
        mainModel[count++] = model;
        modCount++;
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
                    return (T) mainModel[cursor++];
                }
        };
    }
}