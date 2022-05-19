package ru.job4j;

import java.nio.charset.StandardCharsets;
import java.util.*;
/*

Примечания
Рассмотрим детально первый пример входных данных:

    Начальное значение переменной W равно 0 (по условию задачи).
    Сначала Алиса произносит символ a, поэтому значение переменной W становится равным 20=1.
    Затем произносится символ l, которому соответствует 11-й разряд, поэтому значение переменной W становится равным 20+211=2049.
    Далее следует символ i (8-й разряд), поэтому W=20+211+28=2305.
    Предпоследним символом является c (2-й разряд)  — W=20+211+28+22=2309.
    Завершает фразу символ e (4-й разряд)  — итоговое значение W равно 20+211+28+22+24=2325.

Во втором входном примере последовательность W следующая:

    После первого символа a значение W=20=1.
    После символа b значение W=20+21=3.
    После произнесения второго символа a значение W будет равно 21=2, так как 0-й бит инвертируется из 1 в 0.


12
4 132 148 262292 262164 262420 393492 393476 67502340 67502336 67502337 68026625
cheshire cat

5
1 2049 2305 2309 2325
alice


3
1 3 2
aba

 */
public class yandex {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        int two = 2;
        map.put(26, " ");
        String s = "abcdefghijklmnopqrstuvwxyz";
        String[] b = s.split("");
        for (var chars : b) {
            int c = chars.getBytes(StandardCharsets.UTF_8)[0];
          //  System.out.println((long) Math.pow(2, (c - 97)));
           // map.put((long) Math.pow(2, (c - 97)), chars);
            map.put((c - 97), chars);
         //  System.out.println(map.get(0));
        }
        text(5, "4 132 148 262292 262164 262420", map);
    }

    private static void text(int count, String str, Map<Integer, String>map) {
        int sum = 0;
        int d = 0;
        String[] s = str.split(" ");
        List<Integer> i = new ArrayList<>();
        for (var v: s) {
            i.add(Integer.parseInt(v));
        }
        for (var simb : i) {
            double a = Math.log(simb - sum);
            double b = Math.log(2);
            int ca = (int)(a / b);
            String out = map.get(ca);
            System.out.println(simb + " " + ca + " " + out);
            d = (int)Math.pow(2, ca);
            sum = sum + d;
        }
    }

}
