package com.goutham.banking;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class SimpleBankingExcercises {
  // Write a function that returns the number of high-severity risk events in the last 5 minutes.

  Deque<RiskEvent> window = new ArrayDeque<>();

  public synchronized long countRecentHighSeverityEvents(Instant now) {

    while (!window.isEmpty()
        && window.peekFirst().timestamp().isBefore(now.minus(Duration.ofMinutes(5)))) {
      window.pollFirst();
    }

    return window.stream().filter(e -> e.severity().equals(Severity.HIGH)).count();
  }

  public static int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0; // Or throw an IllegalArgumentException
    }

    int maxSoFar = nums[0];
    int currentMax = nums[0];

    for (int i = 1; i < nums.length; i++) {
      currentMax = Math.max(nums[i], currentMax + nums[i]);
      maxSoFar = Math.max(maxSoFar, currentMax);
    }

    return maxSoFar;
  }

  void useStreams() {
    Map<Integer, Set<Integer>> amountUsers = new HashMap();
    List<Integer> collect =
        amountUsers.values().stream()
            .flatMap(users -> users.stream())
            .limit(100)
            .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    int[] arr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println("Max subarray sum for arr1: " + maxSubArray(arr1)); // Expected: 6

    int[] arr2 = {1};
    System.out.println("Max subarray sum for arr2: " + maxSubArray(arr2)); // Expected: 1

    int[] arr3 = {5, 4, -1, 7, 8};
    System.out.println(
        "Max subarray sum for arr3: " + maxSubArray(arr3)); // Expected: 23 (5+4-1+7+8)

    int[] arr4 = {-1, -2, -3, -4};
    System.out.println(
        "Max subarray sum for arr4: "
            + maxSubArray(arr4)); // Expected: -1 (the largest single negative number)
  }
}
