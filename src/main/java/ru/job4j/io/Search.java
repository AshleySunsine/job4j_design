package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Search search = new Search();
        Path start = Paths.get(".");
        search.search(start, p -> p.toFile()
                .getName()
                .endsWith("txt"))
                .forEach(System.out::println);
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }


}