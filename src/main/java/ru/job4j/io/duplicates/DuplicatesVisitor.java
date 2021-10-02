package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<String, List<FileProperty>> map = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long size = Files.size(file);
        String name = file.getFileName().toString();
        FileProperty next = new FileProperty(size, file.getFileName().toString() ,file.toString());
        if(map.containsKey(name)) {
            List<FileProperty> list = new ArrayList<>(map.get(name));
            list.add(next);
            map.put(name, list);
        } else {
            map.put(name, List.of(next));
        }
        return FileVisitResult.CONTINUE;
    }

    public List<FileProperty> getDublicateList() {
        for (var unit : map.entrySet()) {
            if (unit.getValue().size() > 1) { // Здесь косяк, возвращает только один список дубликатов из множества
                return unit.getValue();
            }
        }
        return null;
    }
}