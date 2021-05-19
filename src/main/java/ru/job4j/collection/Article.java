package ru.job4j.collection;

import java.util.HashMap;

public class Article {
    public static boolean generateBy(String text, String origin) {
        String[] source = origin.split("");
        String[] array = text.split("");
        HashMap<String, Integer> counts = new HashMap<>();
        for (int i = 0; i < source.length; i++) {
            String key = source[i];
            if (counts.containsKey(key)) {
                counts.put(key, counts.get(key) + 1);
            } else {
                counts.put(key, 1);
            }
        }
        for (int j = 0; j < array.length; j++) {
            String key = array[j];
            if (counts.containsKey(key)) {
                int val = counts.get(key);
                if (val > 0) {
                    counts.put(key, val - 1);
                }
            }
        }
        for (int v : counts.values()) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}