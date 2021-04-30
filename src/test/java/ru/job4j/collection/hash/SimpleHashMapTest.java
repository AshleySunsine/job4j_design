package ru.job4j.collection.hash;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleHashMapTest {
    @Test
    public void whenGet() {
        SimpleHashMap<String, String> table = new SimpleHashMap<>();
        table.insert("two", "oo");
        assertThat(table.get("two"), is("oo"));
    }

    @Test
    public void whenCantGet() {
        SimpleHashMap<String, String> table = new SimpleHashMap<>();
        table.insert("two", "oo");
        assertNull(table.get("one"));
    }

    @Test
    public void whenAddToTable() {
        SimpleHashMap<String, String> table = new SimpleHashMap<>();
        table.insert("one", "o");
        table.insert("two", "oo");
        table.insert("three", "ooo");
        assertThat(table.get("two"), is("oo"));
    }

    @Test
    public void whenDelete() {
        SimpleHashMap<String, String> table = new SimpleHashMap<>();
        table.insert("two", "oo");
        table.insert("one", "o");
        table.delete("one");
        assertNull(table.get("one"));
    }

    @Test
    public void whenCantDelete() {
        SimpleHashMap<String, String> table = new SimpleHashMap<>();
        table.insert("two", "oo");
        table.insert("one", "o");
        assertFalse(table.delete("three"));
    }

    @Test
    public void whenAddThenDeleteThenAdd() {
        SimpleHashMap<String, String> table = new SimpleHashMap<>();
        table.insert("two", "oo");
        table.insert("one", "o");
        table.delete("one");
        table.insert("threee", "ooo");
        assertThat(table.get("threee"), is("ooo"));
    }

    @Test
    public void whenInsertInONEBucket() {
        SimpleHashMap<String, String> table = new SimpleHashMap<>();
        table.insert("two", "oo"); // bucket == 13
        assertFalse(table.insert("three", "ooo")); // bucket == 13
    }

    @Test
    public void whenResize() {
        /*
        Использовать циклы в тестах - плохая практика, но тут необходимо.
         */
        SimpleHashMap<String, String> table = new SimpleHashMap<>();
        for (int i = 0; i < 101; i++) {
            table.insert(Integer.toString(i), Integer.toString(i + 2));
        }
        assertThat(table.getMapSize(), is(32));
    }
}