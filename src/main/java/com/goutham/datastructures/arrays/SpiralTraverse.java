package com.goutham.datastructures.arrays;

import java.util.ArrayList;
import java.util.List;

/*
* Spiral Traverse
Write a function that takes in an n x m two-dimensional array (that can be square-shaped when n == m) and returns a one-dimensional array of all the array’s elements in spiral order.

Spiral order starts at the top left corner of the two-dimensional array, goes to the right, and proceeds in a spiral pattern all the way until every element has been visited.

Sample Input
text
array = [
  [1,  2,  3,  4],
  [12, 13, 14, 5],
  [11, 16, 15, 6],
  [10, 9,  8,  7],
]
Sample Output
text
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
* */
public class SpiralTraverse {

  public static List<Integer> spiralTraverse(int[][] array) {
    if (array.length == 0) return new ArrayList<Integer>();

    var result = new ArrayList<Integer>();
    var startRow = 0;
    var endRow = array.length - 1;
    var startCol = 0;
    var endCol = array[0].length - 1;
    while (startRow <= endRow && startCol <= endCol) {
      for (int col = startCol; col <= endCol; col++) {
        result.add(array[startRow][col]);
      }
      for (int row = startRow + 1; row <= endRow; row++) {
        result.add(array[row][endCol]);
      }

      for (int col = endCol - 1; col >= startCol; col--) {
        if (startRow == endRow) break;
        result.add(array[endRow][col]);
      }

      for (int row = endRow - 1; row > startRow; row--) {
        if (startCol == endCol) break;
        result.add(array[row][startCol]);
      }

      startRow++;
      endRow--;
      startCol++;
      endCol--;
    }
    return result;
  }

  public static void main(String[] args) {
    int[][] matrix = {
      {1, 2, 3, 4},
      {12, 13, 14, 5},
      {11, 16, 15, 6},
      {10, 9, 8, 7}
    };
    List<Integer> integers = SpiralTraverse.spiralTraverse(matrix);
  }
}
