package analyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternDataBaseFactoryTest {

    @Test
    void convertToPatternDatabase() {
        String format = "1;A Pattern;The Output";
        PatternDataBase actual = PatternDataBaseFactory.convertToPatternDatabase(format);
        PatternDataBase expected = new PatternDataBase(1, "A Pattern", "The Output");

        assertEquals(expected, actual);
    }

    @Test
    void convertToPatternIgnoringDoubleQuotes() {
        String format = "1;\"A Pattern\";\"The Output\"";
        PatternDataBase actual = PatternDataBaseFactory.convertToPatternDatabase(format);
        PatternDataBase expected = new PatternDataBase(1, "A Pattern", "The Output");

        assertEquals(expected, actual);
    }
}