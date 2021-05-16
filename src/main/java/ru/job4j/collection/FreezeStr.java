package ru.job4j.collection;

import java.util.*;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        List<SimbolV2> l = new ArrayList<>();
        Set<String> r = new HashSet<>();
        long hashSumLeft = 0;
        long hashSumRight = 0;
        for (int i = 0; i < left.length(); i++) {
            hashSumLeft += new SimbolV2(left.charAt(i)).hashCode();
        }
        for (int i = 0; i < right.length(); i++) {
            hashSumRight += new SimbolV2(right.charAt(i)).hashCode();
        }
       return hashSumLeft == hashSumRight;
    }

   private static class SimbolV2 {
        char simbol;

        public SimbolV2(char simbol) {
            this.simbol = simbol;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ru.job4j.collection.Simbol simbol1 = (ru.job4j.collection.Simbol) o;
            return Objects.equals(simbol, simbol1.simbol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(simbol);
        }
    }
}