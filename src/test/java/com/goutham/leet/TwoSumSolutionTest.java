package com.goutham.leet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class TwoSumSolutionTest {
    private final TwoSumSolution solution = new TwoSumSolution();

    @Test
    void givenTarget_ifFound_shouldReturnCorrectValue() {

        int[] nums = new int[]{ 7,2,1,1} ;int target = 9;
        int[] expected = new int[]{ 0,1};
        assertThat(solution.twoSum(nums, target)).isEqualTo(expected);
    }

    @Test
    void givenTarget_ifN0tFound_shouldReturnCorrectValue() {

        int[] nums = new int[]{ 7,2,1,1} ;int target = 15;
        int[] expected = new int[2];
        assertThat(solution.twoSum(nums, target)).isEqualTo(expected);
    }
}