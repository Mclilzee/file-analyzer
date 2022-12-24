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
        String[] args = {"src/test/java/analyzer/Emad Ali Lebenslauf.pdf", "%PDF-", "PDF document"};
        Main.main(args);

        String expected = "PDF document" + System.lineSeparator();

        assertEquals(expected, outputStream.toString());
    }

    @Test
    void patternNotFoundPrintsUnknown() {
        String[] args = {"src/test/java/analyzer/Emad Ali Lebenslauf.pdf", "Pattern to not be found", "PDF document"};
        Main.main(args);

        String expected = "Unknown file type" + System.lineSeparator();

        assertEquals(expected, outputStream.toString());
    }

}