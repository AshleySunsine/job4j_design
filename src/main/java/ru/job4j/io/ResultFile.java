package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            String rsl;
            for (int i = 0; i < 99; i++) {
                for (int j = 0; j < 99; j++) {
                    rsl = i + " x " + j + " = " + (j * i) + "; ";
                    out.write(rsl.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}