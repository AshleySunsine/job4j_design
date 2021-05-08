package ru.job4j.searchExam;

public class Searcher {

    private String path = "";
    private String nameSearch = "";
    private String maskSearch = "";
    private String typeSearch = "";
    private String outPutFileSearch = "";


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
        if (args.length != 4) {
            help();
            throw new IllegalArgumentException("Программа должна запускаться c обязательными ключами.");
        }
        for (var a : args) {
            if (!a.contains("=")) {
                help();
                throw new IllegalArgumentException("Ошибка ключ=значение в " + a);
            }
        }
    }

    public static void main(String[] args) {
        validateAndHelp(args);
        Searcher searcher = new Searcher();
    }
}
