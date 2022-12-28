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
    void correctPatternFound() {
        String[] args = {"--naive", "src/test/java/analyzer/pdfTest.pdf", "%PDF-", "PDF document"};
        Main.main(args);

        String output = outputStream.toString();
        String expected = "PDF document\\r?\\nIt took \\d+(\\.\\d+)? seconds\\r?\\n";

        assertTrue(outputStream.toString().matches(expected));
    }

    @Test
    void patternNotFoundPrintsUnknown() {
        String[] args = {"--naive", "src/test/java/analyzer/pdfTest.pdf", "Pattern to not be found", "PDF document"};
        Main.main(args);

        String expected = "Unknown file type\\r?\\nIt took \\d+(\\.\\d+)? seconds\\r?\\n";

        assertTrue(outputStream.toString().matches(expected));
    }

}