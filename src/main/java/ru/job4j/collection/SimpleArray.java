package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    int modCount = 0;
    Object[] mainModel;
    int count = 0;

    public SimpleArray() {
        this.mainModel = new Object[3];
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return (T) mainModel[index];
    }

    public void add(T model) {
        Object[] helper;
        if ((count + 1) < mainModel.length) {
            mainModel[count++] = model;
        } else {
            helper = new Object[count + 10];
            System.arraycopy(mainModel, 0, helper, 0, count++);
            helper[count] = model;
            mainModel = helper;
        }
        modCount++;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            final int expectedModCount = modCount;
            int cursor = 0;
            @Override
            public boolean hasNext() {
                if (!(expectedModCount == modCount)) {
                    throw new ConcurrentModificationException();
                }
                    for (int i = cursor; cursor < count; i++) {
                        if (mainModel[i] != null) {
                            return true;
                        }
                    }
                    return false;
                }

            @Override
            public T next() {
                if (!(expectedModCount == modCount)) {
                    throw new ConcurrentModificationException();
                }
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return (T) mainModel[cursor++];
                }
        };
    }
}