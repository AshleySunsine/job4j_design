package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        DuplicatesVisitor dub = new DuplicatesVisitor();
        Files.walkFileTree(Path.of(args[0]), dub);
        dub.getDublicateList().forEach(System.out::println);
    }
}