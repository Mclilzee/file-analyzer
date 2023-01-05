package analyzer.util;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FilesUtilTest {



    @Test
    void returnCorrectString() {
        Path path = Paths.get("src/test/java/analyzer/util/testFile.txt");

        String actual = FilesUtil.getFileContent(path);
        String expected = "this is a test file";

        assertEquals(expected, actual);
    }

    @Test
    void readNoneTextFilesCorrectly() {
        Path path = Paths.get("src/test/java/analyzer/util/notText.db");

        String actual = FilesUtil.getFileContent(path);
        String expected = "this is not a text file";

        assertEquals(expected, actual);
    }

    @Test
    void wrongFilePathReturnsEmptyString() {
        Path path = Paths.get("invalid/file/path");

        String actual = FilesUtil.getFileContent(path);
        String expected = "";

        assertEquals(expected, actual);
    }
}