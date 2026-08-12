package com.goutham.leet.hard;

import java.util.*;


public class ContainVirus {

    private static class Region {
        // basically every connected infected cell coordinates becomes one region
        List<int[]> infected = new ArrayList<>();
        // using positions has position = row * cols + col
        Set<Integer> threatened = new HashSet<>();
        // every infected to a healthy needs a wall
        int walls = 0;
    }

    private final int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public int containVirus(int[][] isInfected) {

        int rows = isInfected.length;
        int cols = isInfected[0].length;

        int totalWalls = 0;

        while (true) {

            boolean[][] visited = new boolean[rows][cols];
            List<Region> regions = new ArrayList<>();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {

                    if (isInfected[row][col] == 1 && !visited[row][col]) {

                        Region region = bfs(
                                isInfected,
                                row,
                                col,
                                visited
                        );

                        regions.add(region);
                    }
                }
            }

            if (regions.isEmpty()) {
                // no more regions to infect
                break;
            }

            int quarantineIndex = 0;

            // Find the region that threatens the most healthy cells.
            for (int i = 1; i < regions.size(); i++) {
                if (regions.get(i).threatened.size()
                        > regions.get(quarantineIndex).threatened.size()) {
                    quarantineIndex = i;
                }
            }

            Region quarantineRegion = regions.get(quarantineIndex);

            // virus cannot spread anymore
            if (quarantineRegion.threatened.isEmpty()) {
                break;
            }
            // 3. add walls needed to quarantine region
            totalWalls += quarantineRegion.walls;

            //4. Mark all quarantined cells as 2
            for (int[] cell : quarantineRegion.infected) {
                int row = cell[0];
                int col = cell[1];
                isInfected[row][col] = 2;
            }

            //5. Let every non-quarantined infected region spread
            for (int i = 0; i < regions.size(); i++) {
                if (i == quarantineIndex) {
                    continue;
                }

                for (int position : regions.get(i).threatened) {
                    int row = position / cols;
                    int col = position % cols;
                    isInfected[row][col] = 1;
                }
            }
        }

        return totalWalls;
    }

    private Region bfs(
            int[][] grid,
            int startRow,
            int startCol,
            boolean[][] visited) {

        int rows = grid.length;
        int cols = grid[0].length;

        Region region = new Region();

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            region.infected.add(current);

            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];

                if (nextRow < 0 || nextRow >= rows
                        || nextCol < 0 || nextCol >= cols) {
                    continue;
                }

                if (grid[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol});
                } else if (grid[nextRow][nextCol] == 0) {
                    region.walls++;
                    region.threatened.add(nextRow * cols + nextCol);
                }
            }
        }

        return region;
    }
}