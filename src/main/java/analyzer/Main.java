package analyzer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String filename = args[0];
        String pattern = args[1];
        String output = args[2];

        if (filename.matches(pattern)) {
            System.out.println(output);
        } else {
            System.out.println("Unknown file type");
        }
    }
}