package com.goutham.leet;

// he task in the image is to write a function that takes a sorted array of integers and returns a
// new array containing the squares of those integers, also sorted in ascending order.

import java.util.Arrays;

public class SquaredArray {

  public int[] sortedSquares(int[] array) {
    int[] newArray = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      int value = array[i];
      newArray[i] = value * value;
    }
    Arrays.sort(newArray);
    return newArray;
  }
}
