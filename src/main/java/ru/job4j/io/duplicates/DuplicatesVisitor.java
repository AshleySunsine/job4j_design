package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, List<Path>> map = new HashMap<>();
    private List<Path> paths = new ArrayList<>();

    public List<Path> getPaths() {
        for (var fileProperty : map.keySet()) {
            List<Path> temp = map.get(fileProperty);
            if (temp.size() > 1) {
                paths.addAll(temp);
            }
        }
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long size = Files.size(file);
        String name = file.getFileName().toString();
        FileProperty next = new FileProperty(size, name);
        if (map.containsKey(next)) {
            map.get(next).add(file);
        } else {
            ArrayList<Path> newList = new ArrayList<>();
            newList.add(file);
            map.put(next, newList);
        }
        return FileVisitResult.CONTINUE;
    }
}