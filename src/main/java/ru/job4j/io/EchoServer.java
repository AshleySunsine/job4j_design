package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
                     InputStream inputStream =  socket.getInputStream()) {
                    StringBuilder builderString = new StringBuilder();
                    List<Integer> l = new ArrayList<>();
                    while (inputStream.available() > 0) {
                    l.add(inputStream.read());
                    }
                    l.forEach(c -> builderString.append(Character.toString(c)));
                    String str;
                    str = builderString.substring(0);
                    System.out.println("*******************");
                    System.out.println(str);
                    if (str.contains("msg=")) {
                        int indexQ = str.indexOf("/?msg=");
                        int indexQend = str.indexOf(" HTTP");
                        if (indexQ < indexQend) {
                            String msg = str.substring(indexQ + 6, indexQend);
                            String answ = serverResponce(msg);
                            if (answ.equals("-1")) {
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes(Charset.forName("WINDOWS-1251")));
                                out.write("Bye Bye".getBytes(Charset.forName("WINDOWS-1251")));
                                server.close();
                                break;
                            }
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes(Charset.forName("WINDOWS-1251")));
                            out.write(answ.getBytes(Charset.forName("WINDOWS-1251")));
                        }
                    } else {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes(Charset.forName("WINDOWS-1251")));
                        out.write("Непонятненько".getBytes(Charset.forName("WINDOWS-1251")));
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Exception of ServerSocket(9000)", e);
        }
    }
}

