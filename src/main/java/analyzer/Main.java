package analyzer;

import analyzer.searcher.SearcherFactory;
import analyzer.searcher.SubstringSearcher;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final SubstringSearcher searcher = SearcherFactory.getSearcher("");
    private static ExecutorService executorService;
    private static PatternDataBase patternDataBase;

    public static void main(String[] args) throws InterruptedException {
        executorService = Executors.newCachedThreadPool();

        Path path = Paths.get(args[0]);
        patternDataBase = loadDataBase(Paths.get(args[1]));

        printFiles(path);

        executorService.shutdown();
        while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {}
    }

    private static void printFiles(Path path) {
       File file = path.toFile();
       if (file.isDirectory()) {
           Arrays.stream(file.listFiles())
                   .map(File::getPath)
                   .map(Paths::get)
                   .forEach(Main::printFiles);
       } else {
           executorService.execute(() -> printOutput(file));
       }

    }

    private static String getContentFromFile(File file) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            return new String(inputStream.readAllBytes());
        } catch (IOException ex) {
            return "";
        }
    }

    private static void printOutput(File file) {
        String content = getContentFromFile(file);
        if (searcher.containsSubstring(content, pattern)) {
            System.out.println(file.getName() + ": " + output);
        } else {
            System.out.println(file.getName() + ": Unknown file type");
        }
    }

    private static PatternDataBase loadDataBase(Path path) {

    }
}