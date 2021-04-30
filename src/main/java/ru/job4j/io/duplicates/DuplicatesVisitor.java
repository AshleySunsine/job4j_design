package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<String, FileProperty> files = new HashMap<>();
    Map<String, FileProperty> dublicates = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileName = new FileProperty(Files.size(file),
                file.getFileName().toString(),
                file.toFile().getAbsolutePath());
        if (files.containsKey(fileName.getName())
                && (files.get(fileName.getName()).equals(fileName))) {
            dublicates.put(fileName.getFullName(), fileName);
        } else {
            files.put(fileName.getName(), fileName);
        }
        return FileVisitResult.CONTINUE;
    }

    public Map<String, FileProperty> getDublicateList() {
        return dublicates;
    }
}