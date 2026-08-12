package com.goutham.leet.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfIslandsTest {

    private final NumberOfIslands solution = new NumberOfIslands();

    @Test
    void oneIsland() {

        char[][] grid = {
                {'1', '1', '0'},
                {'1', '1', '0'},
                {'0', '0', '0'}
        };

        printGrid(grid);

        int result = solution.numIslands(grid);

        System.out.println("Number of islands: " + result);
        System.out.println("--------------------");

        assertEquals(1, result);
    }

    @Test
    void twoSeparateIslands() {

        char[][] grid = {
                {'1', '1', '0', '0'},
                {'1', '0', '0', '1'},
                {'0', '0', '0', '1'}
        };

        printGrid(grid);

        int result = solution.numIslands(grid);

        System.out.println("Number of islands: " + result);
        System.out.println("--------------------");

        assertEquals(2, result);
    }

    @Test
    void threeSeparateIslands() {

        char[][] grid = {
                {'1', '0', '0', '1'},
                {'1', '0', '0', '0'},
                {'0', '0', '1', '1'}
        };

        printGrid(grid);

        int result = solution.numIslands(grid);

        System.out.println("Number of islands: " + result);
        System.out.println("--------------------");

        assertEquals(3, result);
    }

    @Test
    void noIslands() {

        char[][] grid = {
                {'0', '0', '0'},
                {'0', '0', '0'}
        };

        printGrid(grid);

        int result = solution.numIslands(grid);

        System.out.println("Number of islands: " + result);
        System.out.println("--------------------");

        assertEquals(0, result);
    }

    @Test
    void diagonalCellsAreNotConnected() {

        char[][] grid = {
                {'1', '0'},
                {'0', '1'}
        };

        printGrid(grid);

        int result = solution.numIslands(grid);

        System.out.println("Number of islands: " + result);
        System.out.println("--------------------");

        assertEquals(2, result);
    }

    private void printGrid(char[][] grid) {

        System.out.println();
        System.out.println("--------------------");

        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }

            System.out.println();
        }

        System.out.println("--------------------");
    }
}