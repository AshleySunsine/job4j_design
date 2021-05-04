package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    while (!str.isEmpty()) {
                        if (str.contains("msg=")) {
                            int indexQ = str.indexOf("/?msg=");
                            int indexQend = str.indexOf(" HTTP");
                            String msg = str.substring(indexQ + 6, indexQend);
                            if (msg.equals("Bye")) {
                                System.out.println("Bye Bye");
                                server.close();
                            }
                        }
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                }
            }
        }
    }
}