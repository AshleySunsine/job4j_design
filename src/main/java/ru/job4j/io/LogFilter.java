package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
             rsl = reader.lines().filter(f -> f.contains("404")).collect(Collectors.toList());
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