package ru.job4j.searchexam;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Searcher {

    private List<Path> search(Path start, Predicate<Path> predicate) throws IOException {
        SearcherFiles searcherFiles = new SearcherFiles(predicate);
        Files.walkFileTree(start, searcherFiles);
        return searcherFiles.getPaths();
    }

    private void writeOutput(List<Path> list, Args args) {
        try (PrintStream out = new PrintStream(args.getOutPutFileSearch())) {
            for (var i : list) {
                out.println(i.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Predicate<Path> searchCondition(Args args) {
        String type = args.getTypeSearch();
        String mask = args.getMaskSearch();
        if (type.equals("mask")) {
            return (f -> f.toFile()
                    .getName()
                    .endsWith(mask));
        } else if (type.equals("regex")) {
            return (f -> (Pattern.compile(mask.replace("*", ".*")
                    .replace("?", "\\w{1}"))).matcher(f.getFileName().toString()).find());
        }
        return (f -> f.toFile()
                    .getName().equals(mask));
    }

    public static void main(String[] args) throws IOException {
        Searcher searcher = new Searcher();
        Args arguments = new Args(args);
        List<Path> outList;
        outList = searcher.search(arguments.getPath(), searcher.searchCondition(arguments));
        if (outList.isEmpty()) {
            System.out.println("Nothing searched");
            outList.add(Paths.get("Nothing searched"));
            searcher.writeOutput(outList, arguments);
        }
        searcher.writeOutput(outList, arguments);
    }
}
