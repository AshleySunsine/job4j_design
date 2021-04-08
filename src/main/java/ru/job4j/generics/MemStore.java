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
        int c = searching(id);
        if (c >= 0) {
            mem.set(c, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int c = searching(id);
        if (c >= 0) {
            mem.remove(c);
            return true;
        }
        return false;
    }

    private int searching(String id) {
        int c = 0;
        for (var item : mem) {
            if (item.getId().equals(id)) {
                return c;
            }
            c++;
        }
        return -1;
    }

    @Override
    public T findById(String id) {
        return mem.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }
}