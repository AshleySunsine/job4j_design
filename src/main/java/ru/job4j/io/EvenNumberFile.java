package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt");
             FileOutputStream out = new FileOutputStream("evenOUT.txt")) {
                StringBuilder builder = new StringBuilder();
                int read;
                while ((read = in.read()) != -1) {
                    builder.append((char) read);
                }
                String str = builder.toString();
                String[] strLine = str.split(System.lineSeparator());
                for (var i : strLine) {
                     int digit = Integer.parseInt(i);
                    if (digit % 2 == 0) {
                        out.write((digit + " even!" + System.lineSeparator()).getBytes());
                    } else {
                        out.write((digit + " isn't even!" + System.lineSeparator()).getBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}