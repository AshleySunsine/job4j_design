package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read
                    .lines()
                    .filter(s -> !s.startsWith("#"))
                    .forEach(s -> values
                            .put(s.substring(0, s.indexOf("=")),
                                    s.substring(s.indexOf("=") + 1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Pairs key=value arn't in " + path);
        }
    }

    public String value(String key) {
        if (!values.isEmpty()) {
            return values.get(key);
        } else {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config c = new Config("app.properties");
        c.load();
        System.out.println(c.value("hibernate.dialect"));
    }

}