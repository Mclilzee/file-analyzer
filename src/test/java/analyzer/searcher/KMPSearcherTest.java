package analyzer.searcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KMPSearcherTest {

    SubstringSearcher searcher = new KMPSearcher();

    @Test
    void containsSubstring() {
        String content = "Something to search inside";
        String pattern = "to search";

        assertTrue(searcher.containsSubstring(content, pattern));
    }

    @Test
    void doesNotContainSubstring() {
        String content = "Some string to search that does not contain what you are looking for";
        String pattern = "Patrick";

        assertFalse(searcher.containsSubstring(content, pattern));
    }
}