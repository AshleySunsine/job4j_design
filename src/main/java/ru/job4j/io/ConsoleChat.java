package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
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

    private List<String> readBotAnswers(String file) {
        List<String> answerList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            answerList = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answerList;
    }

    public void run() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss");
        boolean onGoing = true;
        boolean pause = false;
        List<String> dialogList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        List<String> answerList = readBotAnswers(botAnswers);
        String answer = date.toString();
        dialogList.add(answer + System.lineSeparator() + "Hallow" + System.lineSeparator());
        System.out.println("[" + formatForDateNow.format(date) + "] "
                + answer + System.lineSeparator() + "Hallow");
                do {
                    answer = scanner.nextLine();
                    dialogList.add("[" + formatForDateNow.format(date) + "] "
                            + answer + System.lineSeparator());
                    if (answer.equals(STOP)) {
                        pause = true;
                    }
                    if (answer.equals(CONTINUE)) {
                        pause = false;
                    }
                    if (answer.equals(OUT)) {
                        dialogList.add("[" + formatForDateNow.format(date) + "] " + "Bay");
                        System.out.println("[" + formatForDateNow.format(date) + "] " + "Bay");
                        pause = true;
                        onGoing = false;
                    }
                    if (!pause) {
                        String botAnswer = answerList.get(new Random().nextInt(9));
                        System.out.println("[" + formatForDateNow.format(date) + "] "
                                + botAnswer);
                        dialogList.add("[" + formatForDateNow.format(date) + "] "
                                + botAnswer + System.lineSeparator());
                    }
                } while (onGoing);
            scanner.close();
            writeToFile(dialogList);
        }

        private void writeToFile(List<String> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251")))) {
            for (String s : list) {
                writer.write(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("Chat.txt", "answer.txt");
        cc.run();
    }
}