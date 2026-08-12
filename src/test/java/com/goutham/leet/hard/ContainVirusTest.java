package com.goutham.leet.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainVirusTest {

    private final ContainVirus solution = new ContainVirus();

    @Test
    void simpleOneCellVirus() {

        int[][] grid = {
                {1, 0}
        };

        printGrid(grid);

        int result = solution.containVirus(grid);

        System.out.println("Walls used: " + result);
        System.out.println("--------------------");

        assertEquals(1, result);
    }

    @Test
    void virusSurroundingOneHealthyCell() {

        int[][] grid = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        printGrid(grid);

        int result = solution.containVirus(grid);

        System.out.println("Walls used: " + result);
        System.out.println("--------------------");

        assertEquals(4, result);
    }

    @Test
    void twoVirusRegions() {

        int[][] grid = {
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        printGrid(grid);

        int result = solution.containVirus(grid);

        System.out.println("Walls used: " + result);
        System.out.println("--------------------");

        assertEquals(10, result);
    }

    private void printGrid(int[][] grid) {

        System.out.println("Input:");

        for (int[] row : grid) {

            for (int cell : row) {
                System.out.print(cell + " ");
            }

            System.out.println();
        }

        System.out.println("--------------------");
    }
}