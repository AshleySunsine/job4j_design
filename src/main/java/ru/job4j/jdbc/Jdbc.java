package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
    private boolean validate(String url, String login, String password) {
       return !(url == null || (url.isEmpty()) ||  (login == null || login.isEmpty()) ||  (password == null || password.isEmpty()));
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Jdbc jdbc = new Jdbc();
        Class.forName("org.postgresql.Driver");
        String url = null;
        String login = null;
        String password = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("app.properties"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] arg = line.split("=");
                if (arg[0].equals("postgres.url")) {
                    url = arg[1];
                }
                if (arg[0].equals("postgres.login")) {
                    login = arg[1];
                }
                if (arg[0].equals("postgres.password")) {
                    password = arg[1];
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!jdbc.validate(url, login, password)) { // Мини валидация.
            System.out.println("Url, логин или пароль не считались из файла app.properties");

        }
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
