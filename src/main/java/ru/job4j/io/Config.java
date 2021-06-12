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
            String line = read.readLine();
            while ((line != null) && !(line.equals(""))) {
                if (!line.startsWith("#")) {
                    int inx = line.indexOf("=");
                    values.put(line.substring(0, inx), line.substring(inx + 1));
                }
                    line = read.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Pairs key=value arn't in " + path);

        }
    }

    public String value(String key) {
        return values.getOrDefault(key, null);
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