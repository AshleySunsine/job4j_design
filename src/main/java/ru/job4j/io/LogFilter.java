package ru.job4j.io;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = Collections.emptyList();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            rsl = reader
                    .lines()
                    .filter(s -> {
                        int inx = s.lastIndexOf(" ");
                        return s.substring(inx - 3, inx).equals("404");
                    }
                    ).collect(Collectors.toList());
        } catch (Exception e) {
           e.printStackTrace();
       }
        return rsl;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }
}