package ru.job4j.generics;

import java.util.Date;

public class User extends Base {
    public User(String id) {
        super(id);
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
