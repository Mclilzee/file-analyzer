package analyzer.util;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FilesUtilTest {



    @Test
    void returnCorrectString() {
        Path path = Paths.get("src/test/java/analyzer/util/testFile.txt");

        String actual = FilesUtil.getContentFromFile(path);
        String expected = "this is a test file";

    }
}