package com.goutham.leet.hard;

import java.util.LinkedList;
import java.util.Queue;

/*
Number of Islands

Given:

1 1 0
1 0 0
0 1 1

Start at one 1 and keep visiting connected land:

↓
→
↑
←

You don't want to process row by row. You want to completely explore one island before moving to the next.
* */
public class NumberOfIslands {

public int numIslands(char[][] grid){
    int rowsInGrid = grid.length; // height of grid
    int columnsInGrid = grid[0].length; //width of grid

    boolean[][] visited = new boolean[rowsInGrid][columnsInGrid];

    int[][] directions = {{0,1}, //right
            {0,-1}, //left
            {1,0}, //up
            {-1,0}  //down
    };

    int numIslands = 0;

    // lets naviggate through the grid

    for(int r = 0; r < rowsInGrid; r++){
        for(int c = 0; c < columnsInGrid; c++){

            if(grid[r][c] == '1' && !visited[r][c]){
            // if cell is land and not visited
                numIslands++;

                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{r,c});
                visited[r][c] = true;
                // do a BFS from current cell
                while(!queue.isEmpty()){
                    // get the cell to be explored from queue
                    int[] current = queue.poll();
                    int row = current[0];
                    int col = current[1];

                    //explore all adjacent cells in all directions
                    for(int[] dir : directions){
                        int newRow = row + dir[0];
                        int newCol = col + dir[1];

                        if(isWithinGrid(newRow, newCol, grid) &&  // check if new position is within grid
                                grid[newRow][newCol] == '1' && !visited[newRow][newCol] // check if new position is not visited
                        ){
                            queue.add(new int[]{newRow, newCol}); // add new position to queue
                            visited[newRow][newCol] = true;
                        }
                    }
                    // visited and noted all adjacent positions of current cell in all directions
                }
            }

        }
    }
    return numIslands;
}

    private boolean isWithinGrid(int newRow, int newCol, char[][] grid) {
        int rowsInGrid = grid.length;
        int columnsInGrid = grid[0].length;
        return newRow >= 0 && newRow < rowsInGrid &&
                newCol >= 0 && newCol < columnsInGrid;
    }

    private static boolean isWithinGrid(int newRow, int rowsInGrid, int newCol, int columnsInGrid) {
        return newRow >= 0 && newRow < rowsInGrid &&
                newCol >= 0 && newCol < columnsInGrid;
    }
}
