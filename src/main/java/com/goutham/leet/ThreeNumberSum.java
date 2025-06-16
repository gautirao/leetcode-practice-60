package com.goutham.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Write a function that takes in a non-empty array of distinct integers and an
integer representing a target sum. The function should find all triplets in
the array that sum up to the target sum and return a two-dimensional array of
all these triplets. The numbers in each triplet should be ordered in ascending
order, and the triplets themselves should be ordered in ascending order with
respect to the numbers they hold.

  If no three numbers sum up to the target sum, the function should return an
  empty array.
Sample Input
array = [12, 3, 1, 2, -6, 5, -8, 6] target=0
output [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]
*/
public class ThreeNumberSum {

    private static List<Integer[]> getThreeNumberSum(int[] arr, int target){
    Arrays.sort(arr);
    List<Integer[]> triplets = new ArrayList<>();
    for( int i = 0 ; i < arr.length -2 ; i++){
        int left = i + 1;
        int right = arr.length -1;
        int currentSum = arr[i]+arr[left]+arr[right];
        if(currentSum == target){
            Integer[]  triplet = {arr[i],arr[left],arr[right]};
            triplets.add(triplet);
            left++;
            right--;
        }else if(currentSum<target){
            left++;
        }else if(currentSum >target ){
            right--;
        }
    }
        return triplets;
    }

    public static void main(String[] args){
        int[] array = {};
    }
}
