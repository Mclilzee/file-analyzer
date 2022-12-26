package analyzer;

import analyzer.searcher.SearcherFactory;
import analyzer.searcher.SubstringSearcher;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String algorithm = args[0];
        String content = getContentFromFile(Paths.get(args[1]));
        String pattern = args[2];
        String output = args[3];
        SubstringSearcher searcher = SearcherFactory.getSearcher(algorithm);

        if (searcher.containsSubstring(content, pattern)) {
            System.out.println(output);
        } else {
            System.out.println("Unknown file type");
        }
    }

    private static String getContentFromFile(Path path) {
        try (InputStream inputStream = Files.newInputStream(path)) {
            return new String(inputStream.readAllBytes());

        } catch (IOException ex) {
            return "";
        }
    }

}