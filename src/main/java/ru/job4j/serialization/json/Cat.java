package ru.job4j.serialization.json;

import org.json.JSONObject;
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

    public boolean isMan() {
        return isMan;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String[] getEats() {
        return eats;
    }

    public Contact getContact() {
        return contact;
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
        Cat cat = new Cat(true, 7, "Dr.Feelgood", eats, new Contact("777-77-77-2"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isMan", cat.isMan());
        jsonObject.put("age", cat.getAge());
        jsonObject.put("name", cat.getName());
        jsonObject.put("eats", cat.getEats());
        jsonObject.put("contact", cat.getContact());
        System.out.println(jsonObject.toString());
        }
}
