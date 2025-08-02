package com.goutham.datastructures.arrays;

import java.util.Arrays;

public class Sorting {
    public static void mergeSort(int[] array, int startIndex, int endIndex) {
    System.out.println("startIndex : "+startIndex+", endIndex:"+endIndex);
      if (startIndex < endIndex) {
        int[] splitArray = Arrays.copyOfRange(array, startIndex, endIndex + 1);
        System.out.println("Splitting: " + Arrays.toString(splitArray));

        int middleIndex = startIndex + (endIndex - startIndex) / 2;
      System.out.println("Sorting left array startIndex: "+startIndex + " endIndex "+middleIndex);
        // Recursively sort left half
        mergeSort(array, startIndex, middleIndex);

      System.out.println("Sorting right array startIndex: "+ (middleIndex + 1) + " endIndex "+endIndex);
        // Recursively sort right half
        mergeSort(array, middleIndex + 1, endIndex);

      System.out.println(" merging array startIndex "+startIndex+", middleIndex: "+middleIndex+", endIndex:"+endIndex);
        // Merge both halves
        merge(array, startIndex, middleIndex, endIndex);

        System.out.println("Merged:   " + Arrays.toString(array));
      }
    }

    private static void merge(int[] array, int startIndex, int middleIndex, int endIndex) {
      int leftSize = middleIndex - startIndex + 1;
      int rightSize = endIndex - middleIndex;

      int[] leftArray = new int[leftSize];
      int[] rightArray = new int[rightSize];

      // Copy data to temporary arrays
      for (int i = 0; i < leftSize; i++) {
        leftArray[i] = array[startIndex + i];
      }
      for (int j = 0; j < rightSize; j++) {
        rightArray[j] = array[middleIndex + 1 + j];
      }

      // Merge the two sorted arrays
      int leftPointer = 0, rightPointer = 0;
      int mergedIndex = startIndex;
    System.out.println("left Array "+ Arrays.toString(leftArray)+ "right array : "+Arrays.toString(rightArray));
      while (leftPointer < leftSize && rightPointer < rightSize) {
        if (leftArray[leftPointer] <= rightArray[rightPointer]) {
          array[mergedIndex] = leftArray[leftPointer];
          mergedIndex++;
          leftPointer++;
        } else {
          array[mergedIndex] = rightArray[rightPointer];
          mergedIndex++;
          rightPointer++;;
        }
      }

      // Copy any remaining elements
      while (leftPointer < leftSize) {
        array[mergedIndex] = leftArray[leftPointer];
        mergedIndex++;
        leftPointer++;
      }
      while (rightPointer < rightSize) {
        array[mergedIndex] = rightArray[rightPointer];
        mergedIndex++;
        rightPointer++;
      }
    }

    public static void main(String[] args) {
      int[] inputArray = {0, 20, 3, 9, 3, 10};

      System.out.println("Original Array: " + Arrays.toString(inputArray));
      mergeSort(inputArray, 0, inputArray.length - 1);
      System.out.println("Sorted Array:   " + Arrays.toString(inputArray));
    }
  }


