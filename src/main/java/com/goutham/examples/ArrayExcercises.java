package com.goutham.examples;

import java.util.*;
import java.util.stream.Collectors;

/** Hello world! */
public class ArrayExcercises {
  public static void main(String[] args) {
    int[] unsorted = {5, 2, 4, 4, 1, 3};
    ArrayExcercises arr = new ArrayExcercises();
    arr.findDuplicatesInArray(unsorted);
    arr.findDuplicatesConstantSpace(unsorted);
  }

  // assume ; values do not exceed size of array and are positive
  // hint the value of the item can be a index of the array , iterate each element get the index
  // value by substracting by 1
  // set the value as negative , if we come across the item as negative then we know it is
  // duplicated
  public List<Integer> findDuplicatesConstantSpace(int[] unsorted) {
    List<Integer> duplicateItems = new ArrayList<>();
    for (int i = 0; i < unsorted.length; i++) {
      int index = Math.abs(unsorted[i]) - 1;
      if (unsorted[index] < 0) duplicateItems.add(index + 1);
      unsorted[index] = unsorted[index] * -1;
    }

    return duplicateItems;
  }

  public List<Integer> findDuplicatesInArray(int[] unsorted) {
    Set<Integer> uniqueSet = new HashSet<Integer>();
    Set<Integer> duplicateItems = new HashSet<Integer>();

    for (int num : unsorted) {
      if (uniqueSet.contains(num)) duplicateItems.add(num);
      uniqueSet.add(num);
    }
    return duplicateItems.stream().collect(Collectors.toList());
  }

  public List<Integer> sortUsingBubbleSort(int[] unsorted) {

    // time complexity = o(n^2)
    for (int i = 0; i < unsorted.length - 1; i++) { // this runs n-1 times , can be assumed n
      for (int j = 0; j < unsorted.length - i - 1; j++) {
        // runs n-1 first time, then n-2 times .. so can be assumed n
        if (unsorted[j] > unsorted[j + 1]) {
          int temp = unsorted[j];
          unsorted[j] = unsorted[j + 1];
          unsorted[j + 1] = temp;
        }
      }
    }

    return Arrays.stream(unsorted).boxed().collect(Collectors.toList());
  }

  // notes : BubbleSort , Insertion , Selection sort have o(n^2) complexity hence should be avoided
  // as they are slow

  // quick sort and merge sort have o(n log n) complexity and are hence preferred

  public List<Integer> sortUsingQuickSort(int[] unsorted) {
    // 1. choose a pivot
    // 2. partitioning : move all the number less than the pivot to the left and greater than pivot
    // to right
    // 3. recursively quick sort all the items in the left and right sub array in the same way
    quickSort(unsorted, 0, unsorted.length - 1);
    return Arrays.stream(unsorted).boxed().collect(Collectors.toList());
  }

  void quickSort(int[] arr, int lowIndex, int highIndex) {
    // when left and right pointer over lapp
    if (lowIndex >= highIndex) {
      return;
    }
    // arr= { 1 3 5 9 8 9 7 }
    int pivot = arr[highIndex];
    // pivot = 7

    // arr= { 1 3 5 9 8 9 7 }
    // lp rp piv

    int leftPointer = lowIndex;
    int rightPointer = highIndex;

    while (leftPointer < rightPointer) {
      // move leftpointer towards right
      while (arr[leftPointer] <= pivot && leftPointer < rightPointer) {
        leftPointer++;
      }
      // move rightPointer towards left
      while (arr[rightPointer] >= pivot && leftPointer < rightPointer) {
        rightPointer--;
      }

      // when
      swap(arr, leftPointer, rightPointer);
    }
    // when the pointers meet , swap the pivot with the value at the pointer
    swap(arr, leftPointer, highIndex);

    quickSort(arr, lowIndex, leftPointer - 1); // recursively sort left partition
    quickSort(arr, leftPointer + 1, highIndex); // recursively sort right partition
  }

  private void swap(int[] arr, int index1, int index2) {
    int temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
  }

  public boolean isPalindromeString(String value) {
    char[] valueArray = value.toCharArray();
    for (int i = 0, j = value.length() - 1; i < j; i++, j--) {
      if (valueArray[i] != valueArray[j]) {
        return false;
      }
    }
    return true;
  }

  public int search(int[] arr, int target) {
    // assume array is sorted
    // [ 1, 2, 3, 4, 5, 6, 11]
    // start at beginningPointer and endPointer , find the mid. if mid is less than target point
    // beginningPointer to left of mid , else point
    // endPointer to right of mid .. keep repeating until beginningPointer and endPointer cross
    // over....

    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
      int mid = (left + right) / 2;
      if (arr[mid] == target) {
        return mid;
      } else if (target < arr[mid]) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return -1;
  }
}
