package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONPropertyIgnore;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class A {
    private B b;

    @JSONPropertyIgnore
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }

}