package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Article {
    public static boolean generateBy(String origin, String line) {
        StringBuilder filtredOrg = new StringBuilder();
        StringBuilder filtredLine = new StringBuilder();
        for (int i = 0; i < origin.length(); i++) {
            if (Character.isLetterOrDigit(origin.charAt(i)) || (origin.charAt(i) == ' ')) {
                filtredOrg.append(origin.charAt(i));
            }
        }
        for (int i = 0; i < line.length(); i++) {
            if (Character.isLetterOrDigit(line.charAt(i)) || (line.charAt(i) == ' ')) {
                filtredLine.append(line.charAt(i));
            }
        }
        List<String> org = new ArrayList<>(Arrays.asList(filtredOrg.toString().split(" ")));
        List<String> lin = new ArrayList<>(Arrays.asList(filtredLine.toString().split(" ")));
        return org.containsAll(lin);
    }
}