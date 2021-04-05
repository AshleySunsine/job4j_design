package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        cursor = data.next();
    }

    @Override
    public boolean hasNext() {
        setupCursor();
        return (cursor != null && cursor.hasNext());
    }

    @Override
    public T next() {
        setupCursor();
        if (cursor == null)
        {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    private void setupCursor()
    {
        if (cursor != null && cursor.hasNext())
        {
            return;
        }
        cursor = null;
        while (data.hasNext())
        {
            Iterator<T> nextIterator = data.next();
            if (nextIterator.hasNext())
            {
                cursor = nextIterator;
                break;
            }
        }
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
