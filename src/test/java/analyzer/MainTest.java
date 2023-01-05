package analyzer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

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
        String[] args = {"src/test/java/analyzer/files/pdfTest.pdf", "%PDF-", "PDF document"};
        Main.main(args);

        Thread.sleep(500);
        String expected = "pdfTest.pdf: PDF document" + System.lineSeparator();
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void patternNotFoundPrintsUnknown() throws InterruptedException {
        String[] args = {"src/test/java/analyzer/files/pdfTest.pdf", "Pattern to not be found", "PDF document"};
        Main.main(args);

        String expected = "pdfTest.pdf: Unknown file type" + System.lineSeparator();

        assertEquals(expected, outputStream.toString());
    }

    @Test
    void testMultipleDirectories() throws InterruptedException {
        String[] args = {"src/test/java/analyzer/files/", "%PDF-", "PDF document"};
        Main.main(args);

        String expectedFirstFile = "notpdf.txt: Unknown file type" + System.lineSeparator();
        String expectedSecondFile = "pdfTest.pdf: PDF document" + System.lineSeparator();

        assertTrue(outputStream.toString().contains(expectedFirstFile));
        assertTrue(outputStream.toString().contains(expectedSecondFile));
    }

}