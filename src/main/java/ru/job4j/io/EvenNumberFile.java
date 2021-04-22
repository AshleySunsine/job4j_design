package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            try (FileOutputStream out = new FileOutputStream("evenOUT.txt")) {
                StringBuilder builder = new StringBuilder();
                int read;
                while ((read = in.read()) != -1) {
                    builder.append((char) read);
                }
                String str = builder.toString();
                String[] strLine = str.split("\r\n");
                for (var i : strLine) {
                     int digit = Integer.parseInt(i);
                    if (digit % 2 == 0) {
                        out.write((digit + " even!\n").getBytes());
                    } else {
                        out.write((digit + " isnt even!\n").getBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}