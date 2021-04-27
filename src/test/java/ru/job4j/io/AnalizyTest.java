package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.core.Is.is;

public class AnalizyTest {

    @Test
    public void whenAllGoodWithCommentsAndEmptyLines() {
        Analizy analyz = new Analizy();
        analyz.unavailable("ServerAble", "unavailable.csv");
        List<String> is = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("unavailable.csv"))) {
            is = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertThat(is, is(new ArrayList<>(Arrays.asList("10:57:01;10:59:01", "11:01:02;11:02:02"))));
    }

    @Test
    public void whenNoDisable() {
        Analizy analyz = new Analizy();
        analyz.unavailable("EmptyText", "unavailable2.csv");
        List<String> is = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("unavailable2.csv"))) {
            is = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertThat(is.get(0), is("Server enable all time"));
    }
}