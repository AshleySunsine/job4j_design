package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input,2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemove() {
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        ListUtils.removeIf(input, f -> f > 2);
        assertThat(Arrays.asList(0, 1, 2), Is.is(input));
    }

    @Test
    public void whenReplace() {
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        ListUtils.replaceIf(input, f -> f > 2, 99);
        assertThat(Arrays.asList(0, 1, 2, 99, 99, 99, 99), Is.is(input));
    }

    @Test
    public void whenDeleteColOfCol() {
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        List<Integer> elem = new ArrayList<Integer>(Arrays.asList(2, 3, 6));
        ListUtils.removeAll(input, elem);
        assertThat(Arrays.asList(0, 1, 4, 5), Is.is(input));
    }

}