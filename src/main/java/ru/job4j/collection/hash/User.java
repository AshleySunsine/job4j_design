package ru.job4j.collection.hash;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar  birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

  @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

   @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        User first = new User("AAA", 3, now);
        User second = new User("AAA", 3, now);

        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        System.out.println(first.equals(second));
        map.entrySet().forEach(System.out::println);

    }

}
