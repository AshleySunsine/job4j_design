package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dub = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\Users\\Ash\\Desktop\\n"), dub);
        System.out.println(dub.getDublicateList());
    }
}