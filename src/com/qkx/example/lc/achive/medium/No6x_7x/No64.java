package com.qkx.example.lc.achive.medium.No6x_7x;

/**
 * Created by qkx on 16/5/26.
 */
public class No64 {
    public static int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] ref = new int[m][n];
        ref[0][0] = grid[0][0];
        for (int row = 1; row < m; row++) {
            ref[row][0] = ref[row - 1][0] + grid[row][0];
        }
        for (int col = 1; col < n; col++) {
            ref[0][col] = ref[0][col - 1] + grid[0][col];
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                int lastSum = ref[row - 1][col] < ref[row][col - 1] ? ref[row - 1][col] : ref[row][col - 1];
                ref[row][col] = lastSum + grid[row][col];
            }
        }

        return ref[m - 1][n - 1];
    }
}
