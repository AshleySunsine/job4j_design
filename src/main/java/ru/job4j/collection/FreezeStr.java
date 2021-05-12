package ru.job4j.collection;

import java.util.*;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        List<Simbol> l = new ArrayList<>();
        Set<String> r = new HashSet<>();
        long hashSumLeft = 0;
        long hashSumRight = 0;
        for (int i = 0; i < left.length(); i++) {
            hashSumLeft += new Simbol(left.charAt(i)).hashCode();
        }
        for (int i = 0; i < right.length(); i++) {
            hashSumRight += new Simbol(right.charAt(i)).hashCode();
        }
       return hashSumLeft == hashSumRight;
    }
}