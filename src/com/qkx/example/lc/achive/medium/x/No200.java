package com.qkx.example.lc.achive.medium.x;

/**
 * Created by qkx on 17/4/7.
 */
public class No200 {

    /**
     * Given a 2d grid map of '1's (land) and '0's (water),
     * count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     */

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int result = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == '1') {
                    dfs(grid, row, col);
                    result++;
                }
            }
        }


        return result;
    }

    private static void dfs(char[][] grid, int row, int col) {
        if (row < 0 || row > grid.length - 1 || col < 0 || col > grid[0].length - 1) return;
        if (grid[row][col] != '1') return;

        grid[row][col] = 'x';
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
    }

}
