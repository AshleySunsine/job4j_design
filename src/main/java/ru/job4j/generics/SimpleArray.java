package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable {
   private Object[] mainModel = new Object[10];
   private int count = 0;

    public void add(T model) {
        mainModel[count] = model;
        count++;
    }

    public void set(int index, T model) {
        index = Objects.checkIndex(index, count);
        mainModel[index] = model;
    }

    public void remove(int index) {
        index = Objects.checkIndex(index, count);
        System.arraycopy(mainModel, index + 1, mainModel, index, mainModel.length - index - 1);
        count--;
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return (T) mainModel[index];
        }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            int cursor = 0;
            Object[] arr = mainModel;
            @Override
            public boolean hasNext() {
                    return cursor < count;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) arr[cursor++];
            }
        };
    }
}
