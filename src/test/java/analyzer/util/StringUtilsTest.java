package analyzer.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void prefixFunction() {
        String content = "abcdabca";

        int[] expected = {0, 0, 0, 0, 1, 2, 3, 1};

        assertArrayEquals(expected, StringUtils.prefixFunction(content));
    }

    @Test
    void correctValueReturned() {
        String content = "aabaabaaa";

        int[] expected = {0, 1, 0, 1, 2, 3, 4, 5, 2};

        assertArrayEquals(expected, StringUtils.prefixFunction(content));
    }

    @Test
    void emptyStringTest() {
        String content = "";

        int[] expected = {};

        assertArrayEquals(expected, StringUtils.prefixFunction(content));
    }
}