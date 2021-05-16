package ru.job4j.searchexam;

public class Helper {
    public static void help() {
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
}
