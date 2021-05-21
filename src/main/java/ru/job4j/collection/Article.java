package ru.job4j.collection;

import java.util.*;
import java.util.regex.Pattern;

public class Article {
    public static boolean generateBy(String origin, String text) {
        Pattern pattern = Pattern.compile("\\p{Punct}|\\p{Space}");
       Set<String> source = new HashSet<>(Arrays.asList(origin.toLowerCase(Locale.ROOT).split(pattern.pattern())));
       for (var v : text.toLowerCase(Locale.ROOT).split(pattern.pattern())) {
           if (source.add(v)) {
               return false;
           }
       }
       return true;
    }
}