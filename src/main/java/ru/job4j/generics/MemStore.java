package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int c = 0;
        for (var item : mem) {
            if (item.getId().equals(id)) {
                mem.set(c, model);
                return true;
            }
            c++;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int c = 0;
        for (var item : mem) {
            if (item.getId().equals(id)) {
                mem.remove(c);
                return true;
            }
            c++;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        return mem.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }
}