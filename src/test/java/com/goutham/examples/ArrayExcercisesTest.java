package com.goutham.examples;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class ArrayExcercisesTest {
    private ArrayExcercises arrayExcercises ;

    @BeforeEach
    public void doEach(){
        arrayExcercises = new ArrayExcercises();
    }
    @Test
    public void testFindDuplicatesArray() {
        int[] unsorted = { 2,2,1};
        List<Integer> expected = Arrays.asList(2);
        List<Integer> actual = arrayExcercises.findDuplicatesInArray(unsorted);
        assertEquals(actual, expected);
    }    @Test
    public void testFindDuplicatesConstantSpaceArray() {
        int[] unsorted = { 2,2,1};
        List<Integer> expected = Arrays.asList(2);
        List<Integer> actual = arrayExcercises.findDuplicatesConstantSpace(unsorted);
        assertEquals(actual, expected);
    }

    @Test
    public void testQuickSortArray() {
        int[] unsorted = {10, 2,2,1,9};
        Integer [] sorted = new Integer[]{ 1,2,2,9,10};
        List<Integer> expected = Arrays.asList(sorted);
        List<Integer> actual = arrayExcercises.sortUsingQuickSort(unsorted);
        assertEquals(actual, expected);
    }
    @Test
    public void testPalindrome(){

        assertFalse( arrayExcercises.isPalindromeString("Hello"));
        assertTrue( arrayExcercises.isPalindromeString("HabccbaH"));

    }

    @Test
    public void testBinarySearch(){
            int[] array = {1,2,3,4};
      assertEquals(1,arrayExcercises.search(array,2));
      assertEquals(-1,arrayExcercises.search(array,20));

    }
}
