package analyzer.searcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NaiveSearcherTest {

    SubstringSearcher searcher = new NaiveSearcher();

    @Test
    void containsString() {
        String content = "Something to find, interesting find indeed";
        String pattern = "interesting";

        assertTrue(searcher.containsSubstring(content, pattern));
    }

    @Test
    void doesNotContainString() {
        String content = "Something to find here";
        String pattern = "SpongeBob";

        assertFalse(searcher.containsSubstring(content, pattern));
    }

    @Test
    void doesNotContainEmptyString() {
        String content = "";
        String pattern = "something";

        assertFalse(searcher.containsSubstring(content, pattern));
    }

}