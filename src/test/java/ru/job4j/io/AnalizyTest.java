package ru.job4j.io;
import java.io.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.core.Is.is;

public class AnalizyTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void whenAllGoodWithCommentsAndEmptyLines() throws IOException {
        File source = temp.newFile("ServerAble");
        File target = temp.newFile("unavailable.csv");
            try (PrintWriter out = new PrintWriter(source)) {
                out.println("200 10:56:01");
                out.println("");
                out.println("500 10:57:01");
                out.println("");
                out.println("400 10:58:01");
                out.println("");
                out.println("200 10:59:01");
                out.println("");
                out.println("500 11:01:02");
                out.println("");
                out.println("200 11:02:02");
            }
        Analizy analyz = new Analizy();
        analyz.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> is = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            is = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertThat(is, is(new ArrayList<>(Arrays.asList("10:57:01;10:59:01", "11:01:02;11:02:02"))));
    }

    @Test
    public void whenNoDisable() throws IOException{
        File source = temp.newFile("EmptyText");
        File target = temp.newFile("unavailable2.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("");
        }
        Analizy analyz = new Analizy();
        analyz.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> is = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            is = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertThat(is.get(0), is("Server enable all time"));
    }
}