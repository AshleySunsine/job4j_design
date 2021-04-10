package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int size = 0;

    public T poll() {
        int count = size;
        T rsl = null;
        boolean itsOk = false;
        if (size == 0) {
            throw new NoSuchElementException();
        }
        while (count > 0) {
            if (!itsOk) {
                if (count == 1) {
                    out.push(in.pop());
                    rsl = out.pop();
                    count = size - 1;
                    itsOk = true;
                } else {
                        out.push(in.pop());
                        count--;
                        }
                }
            if (itsOk) {
                    in.push(out.pop());
                    count--;
                }
            }
        size--;
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}