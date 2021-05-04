package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        byte byt = 5;
        char cha = 'G';
        long lon = 500000;
        double dou = 45.6d;
        float flo = 56.7f;
        short sho = 128;
        boolean bool = true;
        LOG.debug("User info int : {}, byte : {}, char : {}, long : {},"
                + "double : {}, float : {}, short : {}, boolean : {}", age, byt, cha, lon, dou, flo, sho, bool);
    }
}