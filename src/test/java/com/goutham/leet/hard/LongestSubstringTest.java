package com.goutham.leet.hard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubstringTest {

    private final LongestSubstring solution = new LongestSubstring();

    @Test
    void abcabcbb() {

        String input = "abcabcbb";

        print(input);

        int result = solution.lengthOfLongestSubstring(input);

        System.out.println("Longest length: " + result);
        System.out.println("Example: abc");
        System.out.println("--------------------");

        assertEquals(3, result);
    }

    @Test
    void allSameCharacters() {

        String input = "bbbbb";

        print(input);

        int result = solution.lengthOfLongestSubstring(input);

        System.out.println("Longest length: " + result);
        System.out.println("Example: b");
        System.out.println("--------------------");

        assertEquals(1, result);
    }

    @Test
    void duplicateInsideString() {

        String input = "pwwkew";

        print(input);

        int result = solution.lengthOfLongestSubstring(input);

        System.out.println("Longest length: " + result);
        System.out.println("Example: wke");
        System.out.println("--------------------");

        assertEquals(3, result);
    }

    @Test
    void emptyString() {

        String input = "";

        print(input);

        int result = solution.lengthOfLongestSubstring(input);

        System.out.println("Longest length: " + result);
        System.out.println("--------------------");

        assertEquals(0, result);
    }

    @Test
    void twoSampleString() {

        String input = "111123451116";

        print(input);

        int result = solution.lengthOfLongestSubstring(input);

        System.out.println("Longest length: " + result);
        System.out.println("--------------------");

        assertEquals(5, result);
    }

    private void print(String input) {

        System.out.println();
        System.out.println("--------------------");
        System.out.println("Input: \"" + input + "\"");
        System.out.println("--------------------");
    }
}