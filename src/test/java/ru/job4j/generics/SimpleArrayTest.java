package ru.job4j.generics;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {
    @Test
    public void dynamicCapacity() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("6");
        assertThat(array.get(4), is("5"));
        array.remove(4);
        assertThat(array.get(4), is("6"));
    }
}