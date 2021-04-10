package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
   private int modCount = 0;
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = recursionGet(index, 0, first);
        return rsl.item;
    }

    private Node<E> recursionGet(int index, int countStart, Node<E> accumulator) {
        if (index > countStart) {
            return recursionGet(index, countStart + 1, accumulator.next);
        }
          return accumulator;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator() {
            final int expectedModCount = modCount;
            Node<E> result = first;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (!(expectedModCount == modCount)) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
                E rsl = result.item;
                if (!(expectedModCount == modCount)) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                result = result.next;
                cursor++;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}