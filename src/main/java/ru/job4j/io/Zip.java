package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Exception validation(String[] args) throws IllegalArgumentException {
        if (!args[0].contains("=")) {
            throw new IllegalArgumentException("First argument is wrong " + args[0]);
        }
        if (!args[1].contains("=")) {
            throw new IllegalArgumentException("Second argument is wrong " + args[1]);
        }
        if (!args[2].contains("=")) {
            throw new IllegalArgumentException("Third argument is wrong " + args[2]);
        }
        if (!Paths.get(args[0].substring(args[0].indexOf("=") + 1)).toFile().exists()) {
            throw new IllegalArgumentException("Root folder isn't exist. " + args[0]);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        List<File> list;
        Search search = new Search();
        try {
            validation(args);
        } catch (Exception e) {
            e.printStackTrace();
                }
        list = search.search(Paths.get(ArgsName.of(args).get("d")),
                f -> !f.toFile()
                      .getName()
                      .endsWith(ArgsName.of(args).get("e")))
                               .stream()
                               .map(p -> p.toFile())
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        new Zip().packFiles(list, new File(ArgsName.of(args).get("o")));
    }
}
