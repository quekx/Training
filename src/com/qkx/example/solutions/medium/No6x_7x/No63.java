package com.qkx.example.solutions.medium.No6x_7x;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qkx on 16/5/25.
 */
public class No63 {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] ref = new int[m][n];
        ref[0][0] = 1;
        for (int row = 1; row < m; row++) {
            if (obstacleGrid[row][0] == 1) {
                ref[row][0] = 0;
            } else {
                ref[row][0] = ref[row - 1][0];
            }
        }
        for (int col = 1; col < n; col++) {
            if (obstacleGrid[0][col] == 1) {
                ref[0][col] = 0;
            } else {
                ref[0][col] = ref[0][col - 1];
            }
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (obstacleGrid[row][col] == 1) {
                    ref[row][col] = 0;
                } else {
                    ref[row][col] = ref[row - 1][col] + ref[row][col - 1];
                }
            }
        }

        return ref[m - 1][n - 1];
    }
}
