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

    public static void main(String[] args) throws IOException {
        List<File> list;
        Search search = new Search();
        list = search.search(Paths.get(ArgsName.of(args).get("d")),
                f -> f.toFile()
                      .getName()
                      .endsWith(ArgsName.of(args).get("e")))
                .stream()
                .map(p -> p.toFile())
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        //new Zip().packFiles(list, new File(ArgsName.of(args).get("o")));
    }
}