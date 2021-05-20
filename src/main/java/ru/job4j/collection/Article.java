package ru.job4j.collection;

import java.util.*;

public class Article {
    public static boolean generateBy(String origin, String text) {
       Set<String> source = new HashSet<>(Arrays.asList(origin.toLowerCase(Locale.ROOT).split("[^A-Za-zА-Яа-я0-9]")));
       for (var v : text.toLowerCase(Locale.ROOT).split(" ")) {
           if (source.add(v)) {
               return false;
           }
       }
       return true;
    }
}