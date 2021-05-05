package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Cat {
    private boolean isMan;
    private int age;
    private String name;
    private String[] eats;
    private Contact contact;

    public Cat(boolean isMan, int age, String name, String[] eats, Contact contact) {
        this.isMan = isMan;
        this.age = age;
        this.name = name;
        this.eats = eats;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "isMan=" + isMan
                + ", age=" + age
                + ", name='" + name + '\''
                + ", eats=" + Arrays.toString(eats)
                + ", contact=" + contact
                + '}';
    }

    public static void main(String[] args) {
        String[] eats = {"meet", "fish", "water"};
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(new Cat(true, 7, "Dr.Feelgood", eats, new Contact("777-77-77-2"))));
        final String catType = "{\"isMan\":true, \"age\":7, \"name\":\"Dr.Feelgood\", \"eats\":[\"meet\", \"fisf\", \"water\"], \"contact\":{\"phone\":\"777-77-77-2\"}}";
        System.out.println(gson.fromJson(catType, Cat.class));
    }
}
