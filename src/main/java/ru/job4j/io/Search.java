package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        Search search = new Search();
        Path start = Paths.get(args[0]);
        search.search(start, p -> p.toFile()
                .getName()
                .endsWith(args[1]))
                .forEach(System.out::println);
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }


}