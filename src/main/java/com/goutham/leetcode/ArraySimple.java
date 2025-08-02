package com.goutham.leetcode;

import java.util.HashSet;
import java.util.Set;

// First Duplicate Value
// Given an array of integers between 1 and n, inclusive, where n is the length of the array, write
// a function that returns the first integer that appears more than once (when the array is read
// from left to right).
//
// In other words, out of all the integers that might occur more than once in the input array, your
// function should return the one whose first duplicate value has the minimum index.
//
// If no integer appears more than once, your function should return -1.
//
// Note that you’re allowed to mutate the input array.
//
// Sample Input #1:
// javascript
// Copy
// Edit
// array = [2, 1, 5, 2, 3, 3, 4]
// Sample Output #1:
// javascript
// Copy
// Edit
// 2
//// 2 is the first integer that appears more than once.
//// 3 also appears more than once, but the second 3 appears after the second 2.
public class ArraySimple {

  public int firstDuplicateValue(int[] array) {
    Set<Integer> seen = new HashSet<>();
    for (int value : array) {
      if (seen.contains(value)) return value;
      seen.add(value);
    }
    return -1;
  }
}
