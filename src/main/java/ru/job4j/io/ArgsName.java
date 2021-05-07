package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    @SuppressWarnings("checkstyle:RightCurly")
    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        for (var par : args) {
            int indexEq = par.indexOf("=");
            if (indexEq == (-1)) {
                throw new IllegalArgumentException("indexEq == -1 in " + par);
            }
            String key = par.substring(1, indexEq);
            String value = par.substring(indexEq + 1);
            if ((key.length() == 0) || (value.length() == 0)) {
                throw new IllegalArgumentException("Pairs key=value arn't in " + key + " " + value);
            }
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}