package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss");
        boolean onGoing = true;
        boolean pause = false;
        String answer;
        String botAnswer;
        List<String> answerList;
        Scanner scanner = new Scanner(System.in);
            try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers));
                 BufferedWriter writer = new BufferedWriter(
                         new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
               answerList = reader.lines().collect(Collectors.toList());
               answer = date.toString();
               writer.write(answer + System.lineSeparator() + "Hallow" + System.lineSeparator());
                System.out.println("[" + formatForDateNow.format(date) + "] "
                        + answer + System.lineSeparator() + "Hallow");
                do {
                    answer = scanner.nextLine();
                    writer.write("[" + formatForDateNow.format(date) + "] "
                            + answer + System.lineSeparator());
                    if (answer.equals(STOP)) {
                        pause = true;
                    }
                    if (answer.equals(CONTINUE)) {
                        pause = false;
                    }
                    if (answer.equals(OUT)) {
                        writer.write("[" + formatForDateNow.format(date) + "] " + "Bay");
                        System.out.println("[" + formatForDateNow.format(date) + "] " + "Bay");
                        pause = true;
                        onGoing = false;
                    }
                    if (!pause) {
                        botAnswer = answerList.get(new Random().nextInt(9));
                        System.out.println("[" + formatForDateNow.format(date) + "] "
                                + botAnswer);
                        writer.write("[" + formatForDateNow.format(date) + "] "
                                + botAnswer + System.lineSeparator());
                    }
                } while (onGoing);
            } catch (Exception e) {
                scanner.close();
                e.printStackTrace();
            }
            scanner.close();
        }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("Chat.txt", "Responce.txt");
        cc.run();
    }
}