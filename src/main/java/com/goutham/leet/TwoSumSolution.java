package com.goutham.leet;

import java.util.HashMap;
import java.util.Map;

/*Approach

 A more efficient approach is to use a hash table (unordered_map in C++). We can iterate through the array once, and for each element, check if the target minus the current element exists in the hash table. If it does, we have found a valid pair of numbers. If not, we add the current element to the hash table.
Approach using a hash table:

Create an empty hash table to store elements and their indices.
Iterate through the array from left to right.
For each element nums[i], calculate the complement by subtracting it from the target: complement = target - nums[i].
Check if the complement exists in the hash table. If it does, we have found a solution.
If the complement does not exist in the hash table, add the current element nums[i] to the hash table with its index as the value.
Repeat steps 3-5 until we find a solution or reach the end of the array.
If no solution is found, return an empty array or an appropriate indicator.
This approach has a time complexity of O(n) since hash table lookups take constant time on average. It allows us to solve the Two Sum problem efficiently by making just one pass through the array.

* */
public class TwoSumSolution {

    private final Map<Integer, Integer> numMap = new HashMap<>();
    public int[] twoSum(int[] nums, int target){

        int[] result = new int[2];

        for ( int i=0; i< nums.length ; i++ ){
            int difference = target - nums[i];
            if (numMap.containsKey(difference)){
                result[1] = i;
                result[0] = numMap.get(difference);
                return result;
            }
            numMap.put(nums[i],i);
        }

        //return nothing if no match found
        return result;
    }


}
