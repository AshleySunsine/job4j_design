package ru.job4j.searchExam;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Searcher {

    private static Path path;
    private static String maskSearch = "";
    private static String typeSearch = "";
    private static String outPutFileSearch = "";

    private static void help() {
        System.out.println("!!! Подсказка !!!"
                + "-jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt"
                + System.lineSeparator()
                + "-d - директория, в которой начинать поиск."
                + System.lineSeparator()
                + "-n - имя файла, маска, либо регулярное выражение."
                + System.lineSeparator()
                + "-t - тип поиска: mask искать по маске, name по полному"
                + " совпадение имени, regex по регулярному выражению."
                + System.lineSeparator()
                + "-o - результат записать в файл."
                + System.lineSeparator()
                + "Пример: find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt");
    }

    private static void validateAndHelp(String[] args) {
        Pattern[] regPatterns = new Pattern[4];
        regPatterns[0] = Pattern.compile("-\\b[d]=\\w:\\\\");
        regPatterns[1] = Pattern.compile("-\\b[n]=");
        regPatterns[2] = Pattern.compile("-\\b[t]=|(mask)|(regex)|(name)]");
        regPatterns[3] = Pattern.compile("-\\b[o]=");
        if (args.length != 4) {
            help();
            throw new IllegalArgumentException("Программа должна запускаться c обязательными ключами.");
        }
        for (int i = 0; i < 4; i++) {
            if (!regPatterns[i].matcher(args[i]).find()) {
                help();
                throw new IllegalArgumentException("Ошибка ключ=значение в " + args[i]);
            }
        }
    }

    private static void init(String[] args) {
        path = Paths.get(args[0].substring(args[0].indexOf("=") + 1));
        typeSearch = args[2].substring(args[2].indexOf("=") + 1);
        if (typeSearch.equals("mask")) {
            maskSearch = args[1].substring(args[1].indexOf("=") + 2);
        } else {
            maskSearch = args[1].substring(args[1].indexOf("=") + 1);
        }
        outPutFileSearch = args[3].substring(args[3].indexOf("=") + 1);
    }

    private static List<Path> search(Path start, Predicate<Path> predicate) throws IOException {
        SearcherFiles searcherFiles = new SearcherFiles(predicate);
        Files.walkFileTree(start, searcherFiles);
        return searcherFiles.getPaths();
    }

    private static void writeOutput(List<Path> list) {
        try (FileOutputStream out = new FileOutputStream(outPutFileSearch)) {
            for (var i : list) {
                out.write((i.toString() + System.lineSeparator()).getBytes(StandardCharsets.US_ASCII));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        List<Path> outList = Collections.EMPTY_LIST;
        validateAndHelp(args);
        init(args);
        if (typeSearch.equals("mask")) {
            outList = Searcher.search(path, f -> f.toFile()
            .getName()
            .endsWith(maskSearch));
        } else if (typeSearch.equals("regex")) {
            outList = Searcher.search(path, f -> (Pattern.compile(maskSearch)).matcher(f.getFileName().toString()).find());
        } else {
            outList = Searcher.search(path, f -> f.toFile()
                    .getName().equals(maskSearch));
        }
        if (outList.isEmpty()) {
            System.out.println("Nothing searched");
            outList.add(Paths.get("Nothing searched"));
            writeOutput(outList);
        }
        writeOutput(outList);
    }
}
