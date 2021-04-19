package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> par = findBy(parent);
        if (par.isPresent() && findBy(child).isEmpty()) {
            par.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    public boolean isBinary() {
        return predictFind(f -> f.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> predictFind(Predicate<Node<E>> pred) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        Optional<Node<E>> rsl = Optional.empty();
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (pred.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return predictFind(f -> f.value.equals(value));
    }
}