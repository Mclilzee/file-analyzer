package analyzer;

import analyzer.searcher.SearcherFactory;
import analyzer.searcher.SubstringSearcher;
import analyzer.util.FilesUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Main {

    private static final SubstringSearcher searcher = SearcherFactory.getSearcher("");
    private static ExecutorService executorService;
    private static List<PatternDataBase> patternDataBase;

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            System.out.println("Need to pass the file path as Command-Line argument");
            System.exit(-1);
        }
        executorService = Executors.newCachedThreadPool();

        Path path = Paths.get(args[0]);
        patternDataBase = loadDataBase(Paths.get(args[1]));

        printFiles(path);

        executorService.shutdown();
        while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {}
    }

    private static List<PatternDataBase> loadDataBase(Path path) {
        return FilesUtil.getFileContent(path).lines()
                        .map(PatternDataBaseFactory::convertToPatternDatabase)
                        .toList();
    }

    private static void printFiles(Path path) {
       if (Files.isDirectory(path)) {
           printFilesInDirectory(path);
       } else {
           executorService.execute(() -> printOutput(path));
       }

    }

    private static void printFilesInDirectory(Path path) {
        try (Stream<Path> paths = Files.list(path)) {
            paths.forEach(Main::printFiles);
        } catch (IOException ex) {
            System.out.println("Failed to retrieve paths for " + path);
        }
    }

    private static void printOutput(Path path) {
        String content = FilesUtil.getFileContent(path);
        Optional<String> output = patternDataBase.stream()
                                                 .filter(database -> searcher.containsSubstring(content, database.pattern()))
                                                 .sorted()
                                                 .map(PatternDataBase::output)
                                                 .findFirst();
        System.out.println(path.getFileName() + ": " + output.orElse("Unknown file type"));
    }
}