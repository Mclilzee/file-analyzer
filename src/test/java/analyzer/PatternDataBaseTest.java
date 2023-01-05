package analyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternDataBaseTest {

    PatternDataBase patternDataBase = new PatternDataBase(1, "Pattern", "Output");

    @Test
    void priority() {
        int expected = 1;

        assertEquals(expected, patternDataBase.priority());
    }

    @Test
    void pattern() {
        String expected = "Pattern";

        assertEquals(expected, patternDataBase.pattern());
    }

    @Test
    void output() {
        String expected = "Output";

        assertEquals(expected, patternDataBase.output());
    }

    @Test
    void priorityNaturalOrderBigger() {
        PatternDataBase first = new PatternDataBase(10, "", "");
        PatternDataBase second = new PatternDataBase(5, "", "");

        assertTrue(first.compareTo(second) < 0);
    }

    @Test
    void priorityNaturalOrderSmaller() {
        PatternDataBase first = new PatternDataBase(10, "", "");
        PatternDataBase second = new PatternDataBase(5, "", "");

        assertTrue(second.compareTo(first) > 0);
    }

    @Test
    void priorityNaturalOrderEqual() {
        PatternDataBase first = new PatternDataBase(5, "", "");
        PatternDataBase second = new PatternDataBase(5, "", "");

        assertEquals(0, first.compareTo(second));
    }
}