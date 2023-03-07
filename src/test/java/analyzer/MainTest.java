package analyzer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    String database = "src/test/java/analyzer/patterns.db";
    static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    void setup() {
        outputStream.reset();
    }

    @Test
    void correctPatternFound() throws InterruptedException {
        String[] args = {"src/test/java/analyzer/files/pdfTest.pdf", database};
        Main.main(args);

        String expected = System.lineSeparator() + "pdfTest.pdf: PDF document" + System.lineSeparator();
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void patternNotFoundPrintsUnknown() throws InterruptedException {
        String[] args = {"src/test/java/analyzer/files/pdfTest.pdf", "1;none existence pattern;some output"};
        Main.main(args);

        String expected = System.lineSeparator() + "pdfTest.pdf: Unknown file type" + System.lineSeparator();

        assertEquals(expected, outputStream.toString());
    }

    @Test
    void testMultipleDirectories() throws InterruptedException {
        String[] args = {"src/test/java/analyzer/files/", database};
        Main.main(args);

        String expectedFirstFile = "notpdf.txt: Unknown file type" + System.lineSeparator();
        String expectedSecondFile = "pdfTest.pdf: PDF document" + System.lineSeparator();
        String expectedThirdFile = "docTest.doc: MS Office Word 2003";

        String outputString = outputStream.toString();

        assertTrue(outputString.contains(expectedFirstFile));
        assertTrue(outputString.contains(expectedSecondFile));
        assertTrue(outputString.contains(expectedThirdFile));
    }

}