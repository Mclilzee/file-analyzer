package analyzer;

import analyzer.searcher.SearcherFactory;
import analyzer.searcher.SubstringSearcher;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.Duration;

public class Main {

    public static void main(String[] args) {
        String algorithm = args[0];
        String content = getContentFromFile(Paths.get(args[1]));
        String pattern = args[2];
        String output = args[3];
        SubstringSearcher searcher = SearcherFactory.getSearcher(algorithm);

        Duration startDuration = Duration.ofMillis(System.currentTimeMillis());
        if (searcher.containsSubstring(content, pattern)) {
            printOutput(output, startDuration);
        } else {
            printOutput("Unknown file type", startDuration);
        }
    }

    private static String getContentFromFile(Path path) {
        try (InputStream inputStream = Files.newInputStream(path)) {
            return new String(inputStream.readAllBytes());

        } catch (IOException ex) {
            return "";
        }
    }

    private static void printOutput(String output, Duration startDuration) {
        Duration duration = Duration.ofMillis(System.currentTimeMillis()).minus(startDuration);
        String seconds = String.valueOf(duration.toSecondsPart());
        String milis = String.valueOf(duration.toMillisPart());
        double time = Double.parseDouble(seconds + "." + milis);

        DecimalFormat decimalFormatter = new DecimalFormat("0");
        decimalFormatter.setMaximumFractionDigits(3);

        System.out.printf("%s%nIt took %s seconds%n", output, decimalFormatter.format(time));
    }

}