package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    private static String serverResponce(String str) {
        String resp = "Непонятно что";
            if (str.equals("Bye") || str.equals("Exit")) {
            return "-1";
        }
        if (str.equals("Hello")) {
            return "Hello";
        }
        if (str.equals("")) {
            return "Пустой параметр";
        }
        if (str.equals("Any")) {
            return "What.";
        }
        return resp;
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    String answ;
                    while (!str.isEmpty()) {
                        if (str.contains("msg=")) {
                            int indexQ = str.indexOf("/?msg=");
                            int indexQend = str.indexOf(" HTTP");
                            String msg = str.substring(indexQ + 6, indexQend);
                            answ = serverResponce(msg);
                            if (answ.equals("-1")) {
                                out.write("Bye Bye".getBytes());
                                server.close();
                                break;
                            }
                            out.write(answ.getBytes(Charset.forName("WINDOWS-1251")));
                        }
                        break;
                    }
                    server.close();
                }
            }
        } catch (Exception e) {
            LOG.error("Exception of ServerSocket(9000)", e);
        }
    }
}