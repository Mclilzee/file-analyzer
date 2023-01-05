package analyzer.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesUtil {

    public static String getFileContent(Path path) {
        try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(path))) {
            return new String(inputStream.readAllBytes());
        } catch (IOException ex) {
            return "";
        }
    }
}
