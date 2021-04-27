package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean disable = false;
            int countOfDisable = 0;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if ((line.length() == 0)
                        || (line.substring(0, 1).equals(System.lineSeparator())
                        || line.substring(0, 1).equals("#"))) {
                    continue;
                }
                String serverCode = line.substring(0, 3);
                int status = Integer.parseInt(serverCode);
                String time = line.substring(4);
                if (((status == 400) || (status == 500))  && (!disable)) {
                    disable = true;
                    countOfDisable++;
                    writer.print(time + ";");
                }
                if (!((status == 400) || (status == 500)) && disable) {
                    disable = false;
                    writer.print(time + System.lineSeparator());
                }
            }
            if (countOfDisable == 0) {
                writer.println("Server enable all time");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analyz = new Analizy();
        analyz.unavailable("ServerAble", "unavailable.csv");
    }
}