package ru.job4j.collection;

import java.util.*;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        if (left.length() != right.length()) {
            return false;
        }
        Map<String, Integer> array1 = new HashMap<>();
        Map<String, Integer> array2 = new HashMap<>();
        for (var k : left.split("")) {
            array1.merge(k, 1, ((key, v) -> v + 1));
        }
        for (var k : right.split("")) {
            array2.merge(k, 1, ((key, v) -> v + 1));
        }
        for (var k : left.split("")) {
            if (!array1.get(k).equals(array2.get(k))) {
                return false;
            }
        }
        return true;
    }
}