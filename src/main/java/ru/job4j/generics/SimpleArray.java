package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable {
    Object[] mainModel;
    int count = 0;

    public SimpleArray(int length) {
        mainModel = new Object[length];
    }
    public void add(T model) {
        mainModel[count] = model;
        count++;
    }

    public void set(int index, T model) {
        index = Objects.checkIndex(index, mainModel.length);
        mainModel[index] = model;
    }

    public void remove(int index) {
        index = Objects.checkIndex(index, mainModel.length);
        Object[] helper = new Object[mainModel.length - 1];
        System.arraycopy(mainModel, 0, helper, 0, mainModel.length - index);
        System.arraycopy(mainModel, index, helper, index - 1, mainModel.length - index);
        mainModel = helper;
        count--;
    }

    public Object get(int index) {
        Objects.checkIndex(index, mainModel.length);
        return mainModel[index];
        }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int cursor = 0;
            Object[] arr = mainModel;
            @Override
            public boolean hasNext() {
                return (cursor + 1) < mainModel.length;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arr[cursor++];
            }
        };
    }
}
