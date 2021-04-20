package ru.job4j.collection.exam;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {
    private Map<Integer, User> previusMap = new HashMap<>();
    private Map<Integer, User> currentMap = new HashMap<>();

    public Info diff(List<User> previous, List<User> current) {
        previusMap = previous.stream().collect(Collectors.toMap(f -> f.id, f -> f));
        currentMap = current.stream().collect(Collectors.toMap(f -> f.id, f -> f));

        int diffCount = 0;
        int deleteCount = 0;
        int addCount = currentMap.size();


        for (var i : previusMap.entrySet()) {

            User z = (currentMap.put(i.getKey(), i.getValue()));
            if (z != null && !(z.name.equals(i.getValue().name))) {
                    diffCount++;
                    currentMap.remove(i.getKey());
                }

            if (z == null) {
                deleteCount++;
            }

            if (z != null && z.id == i.getValue().id) {
                currentMap.remove(i.getKey());
                addCount--;
            }
        }
        return (addCount + deleteCount + diffCount) == 0 ? null : new Info(addCount, diffCount, deleteCount);
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

        public static class Info {
            int added;
            int changed;
            int deleted;

            public Info(int added, int changed, int deleted) {
                this.added = added;
                this.changed = changed;
                this.deleted = deleted;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                Info info = (Info) o;
                return added == info.added && changed == info.changed && deleted == info.deleted;
            }

            @Override
            public int hashCode() {
                return Objects.hash(added, changed, deleted);
            }
        }
}