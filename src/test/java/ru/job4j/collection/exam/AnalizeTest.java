package ru.job4j.collection.exam;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.core.Is.is;

public class AnalizeTest {

    @Test
    public void whenNoChange() {
        Analize an = new Analize();
        List<Analize.User> l1 = new ArrayList<>();
        List<Analize.User> l2 = new ArrayList<>();

        l1.add(new Analize.User(1, "a"));
        l1.add(new Analize.User(2, "aa"));

        l2.add(new Analize.User(1, "a"));
        l2.add(new Analize.User(2, "aa"));

        assertNull(an.diff(l1, l2));
    }

    @Test
    public void whenDelete() {
        Analize an = new Analize();
        List<Analize.User> l1 = new ArrayList<>();
        List<Analize.User> l2 = new ArrayList<>();

        l1.add(new Analize.User(1, "a"));
        l1.add(new Analize.User(2, "aa"));

        l2.add(new Analize.User(1, "a"));

        int exec = an.diff(l1, l2).deleted;
        System.out.println("Added:" + an.diff(l1, l2).added
                + " Changed:" + an.diff(l1, l2).changed
                + " Deleted:" + an.diff(l1, l2).deleted);
        assertThat(exec, is(1));
    }

    @Test
    public void whenChange() {
        Analize an = new Analize();
        List<Analize.User> l1 = new ArrayList<>();
        List<Analize.User> l2 = new ArrayList<>();

        l1.add(new Analize.User(1, "a"));
        l1.add(new Analize.User(2, "aa"));

        l2.add(new Analize.User(1, "a"));
        l2.add(new Analize.User(2, "ajja"));

        int exec = an.diff(l1, l2).changed;
        System.out.println("Added:" + an.diff(l1, l2).added
                + " Changed:" + an.diff(l1, l2).changed
                + " Deleted:" + an.diff(l1, l2).deleted);
        assertThat(exec, is(1));
    }

    @Test
    public void whenAdded() {
        Analize an = new Analize();
        List<Analize.User> l1 = new ArrayList<>();
        List<Analize.User> l2 = new ArrayList<>();

        l1.add(new Analize.User(1, "a"));
        l1.add(new Analize.User(2, "aa"));

        l2.add(new Analize.User(1, "a"));
        l2.add(new Analize.User(2, "aa"));
        l2.add(new Analize.User(3, "aaa"));
        l2.add(new Analize.User(4, "aaaa"));

        int exec = an.diff(l1, l2).added;
        System.out.println("Added:" + an.diff(l1, l2).added
                + " Changed:" + an.diff(l1, l2).changed
                + " Deleted:" + an.diff(l1, l2).deleted);
        assertThat(exec, is(2));
    }

    @Test
    public void whenChangedAndDeletedAndAdded() {
        Analize an = new Analize();
        List<Analize.User> l1 = new ArrayList<>();
        List<Analize.User> l2 = new ArrayList<>();

        l1.add(new Analize.User(1, "a"));
        l1.add(new Analize.User(2, "aa"));
        l1.add(new Analize.User(3, "aaa"));
        l1.add(new Analize.User(4, "aaaa"));

        l2.add(new Analize.User(1, "a"));
        l2.add(new Analize.User(3, "Change!"));
        l2.add(new Analize.User(4, "Change!"));
        l2.add(new Analize.User(5, "Added"));
        l2.add(new Analize.User(6, "Added"));
        l2.add(new Analize.User(7, "Added"));

        Analize.Info exec = an.diff(l1, l2);
        System.out.println("Added:" + an.diff(l1, l2).added
                + " Changed:" + an.diff(l1, l2).changed
                + " Deleted:" + an.diff(l1, l2).deleted);
        assertEquals(exec, new Analize.Info(3,2, 1));
    }
}