package ru.job4j.searchexam;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Args {
    private final Path path;
    private final String maskSearch;
    private final String typeSearch;
    private final String outPutFileSearch;

    public Args(String[] args) {
        validateAndHelp(args);
        path = Paths.get(args[0].substring(args[0].indexOf("=") + 1));
        typeSearch = args[2].substring(args[2].indexOf("=") + 1);
        if (typeSearch.equals("mask")) {
            maskSearch = args[1].substring(args[1].indexOf("=") + 2);
        } else {
            maskSearch = args[1].substring(args[1].indexOf("=") + 1);
        }
        outPutFileSearch = args[3].substring(args[3].indexOf("=") + 1);
    }

    private void validateAndHelp(String[] args) {
        Pattern[] regPatterns = new Pattern[4];
        regPatterns[0] = Pattern.compile("-\\b[d]=\\w:\\\\");
        regPatterns[1] = Pattern.compile("-\\b[n]=");
        regPatterns[2] = Pattern.compile("-\\b[t]=|(mask)|(regex)|(name)]");
        regPatterns[3] = Pattern.compile("-\\b[o]=");
        if (args.length != 4) {
            Helper.help();
            throw new IllegalArgumentException("Программа должна запускаться c обязательными ключами.");
        }
        for (int i = 0; i < 4; i++) {
            if (!regPatterns[i].matcher(args[i]).find()) {
                Helper.help();
                throw new IllegalArgumentException("Ошибка ключ=значение в " + args[i]);
            }
        }
    }

    public Path getPath() {
        return path;
    }

    public String getMaskSearch() {
        return maskSearch;
    }

    public String getTypeSearch() {
        return typeSearch;
    }

    public String getOutPutFileSearch() {
        return outPutFileSearch;
    }
}
