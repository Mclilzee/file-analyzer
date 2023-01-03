package analyzer;

import analyzer.searcher.SearcherFactory;
import analyzer.searcher.SubstringSearcher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static SubstringSearcher searcher = SearcherFactory.getSearcher("");
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static String output = "";
    private static String pattern = "";

    public static void main(String[] args) throws InterruptedException {
        pattern = args[1];
        output = args[2];
        Path path = Paths.get(args[0]);

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
        System.out.print(file.getName() + ": ");
        if (searcher.containsSubstring(content, pattern)) {
            System.out.println(output);
        } else {
            System.out.println("Unknown file type");
        }
    }
}