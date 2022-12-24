package analyzer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get(args[0]);
        Pattern pattern = Pattern.compile(args[1]);
        String output = args[2];

        if (patternFound(path, pattern)) {
            System.out.println(output);
        } else {
            System.out.println("Unknown file type");
        }
    }

    private static boolean patternFound(Path path, Pattern pattern) {
        try (InputStream inputStream = Files.newInputStream(path)) {
            String content = new String(inputStream.readAllBytes());

            return pattern.matcher(content).find();
        } catch (IOException ex) {
            System.out.println("Failed finding file");
            return false;
        }
    }
}