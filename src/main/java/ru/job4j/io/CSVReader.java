package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * Задача класса прочитать данные из CSV файла и вывести их.
 * @author Alina Sharonina
 * @version 1.0
 */
public class CSVReader {
    /**
     * Метод предназначен для формирования шапки таблицы в новом файле,
     * а также для создания списка индексов отфильтрованных столбцов.
     * @param scanner сканер.
     * @param delimiter разделитель.
     * @param filter содержит названия столбцов, по которым будет поиск.
     * @param out содержит путь к файлу записи.
     * @return indexes список индексов, по которым мы будем искать таблицы в методе {@code handle}
     */
    public static List<Integer> makeHead(Scanner scanner, String delimiter, String filter,
                                         PrintWriter out) {
        List<Integer> indexes = new ArrayList<>();
        String[] lineHead = scanner.nextLine().split(delimiter);
        StringBuilder builderHead = new StringBuilder();
        for (int i = 0; i < lineHead.length; i++) {
            if (filter.contains(lineHead[i])) {
                indexes.add(i);
                builderHead.append(lineHead[i]).append(";");
            }
        }
        builderHead.setLength(Math.max(builderHead.length() - 1, 0));
        out.println(builderHead);
        return indexes;
    }

    /**
     * Метод предназначен для заполнения остальной части таблицы в заполняемом файле.
     * В качестве входных данных метода задается путь к файлу path,
     * разделитель delimiter, приемник данных out и фильтр по столбцам filter.
     * @param argsName содержит массив параметров: path, delimiter, out, filter.
     */
    public static void handle(ArgsName argsName) {
        String inPath = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String outPath = argsName.get("out");
        String filter = argsName.get("filter");
        validation(inPath, outPath);
        File fileIn = Paths.get(inPath).toFile();
        try (
                PrintWriter out = new PrintWriter(new FileOutputStream(outPath));
                Scanner scanner = new Scanner(fileIn)
        ) {
            List<Integer> indexes = makeHead(scanner, delimiter, filter, out);
            while (scanner.hasNextLine()) {
                StringBuilder builder = new StringBuilder();
                String[] nextLine = scanner.nextLine().split(delimiter);
                for (Integer index : indexes) {
                    builder.append(nextLine[index]).append(";");
                }
                builder.setLength(Math.max(builder.length() - 1, 0));
                out.println(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод предназначен для валидации файлов.
     * @param inPath путь до считываемого файла.
     * @param outPath путь до файла, в который мы будем записывать данные.
     */
    public static void validation(String inPath, String outPath) {
        File fileIn = Paths.get(inPath).toFile();
        File fileOut = Paths.get(outPath).toFile();
        if (!fileIn.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", fileIn.getAbsoluteFile()
                            + System.lineSeparator() + fileOut.getAbsoluteFile()));
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
