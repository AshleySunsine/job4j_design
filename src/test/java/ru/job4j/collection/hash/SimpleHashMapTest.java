package ru.job4j.collection.hash;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.is;


public class SimpleHashMapTest {
    @Test
    public void whenAddToTable() {
        SimpleHashMap<String,String> table = new SimpleHashMap<>();
        table.insert("one", "o");
        table.insert("two", "oo");
        table.insert("three", "ooo");
        assertThat(table.get("two"), is("oo"));
    }



}