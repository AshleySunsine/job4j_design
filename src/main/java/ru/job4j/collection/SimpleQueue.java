package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int size = 0;

    public T poll() {
        int count = size;
        T rsl = null;
        while (count > 0) {
            out.push(in.pop());
            count--;
        }
        rsl = out.pop();
        while (count + 1 < size) {
            in.push(out.pop());
            count++;
        }
        size--;
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}