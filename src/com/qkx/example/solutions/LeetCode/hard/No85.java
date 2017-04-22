package com.qkx.example.solutions.LeetCode.hard;

/**
 * Created by qkx on 16/11/24.
 */
public class No85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        if (matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] height = new int[m + 1][n + 1];
        int[][] width = new int[m + 1][n + 1];
        for (int row = 0; row <= m; row++) {
            height[row][0] = 0;
            width[row][0] = 0;
        }
        for (int col = 0; col <= n; col++) {
            height[0][col] = 0;
            width[0][col] = 0;
        }



        return 0;
    }
}
