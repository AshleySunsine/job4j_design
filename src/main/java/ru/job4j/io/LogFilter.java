package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
       List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<List<String>> list = new ArrayList<>();
            int i = 0;
            reader.lines().forEach(s -> list
                    .add(Arrays
                            .stream(s.split(" "))
                            .collect(Collectors.toList())));
            for (var s : list) {
                if (s.get(s.size() - 2).equals("404")) {
                    String lineEnd = Arrays.toString(
                            s.toArray())
                            .replaceAll("\\[|\\]|,|\\s", "");
                    rsl.add(lineEnd);
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
       }

        return rsl;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}