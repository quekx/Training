package com.qkx.example.solutions.medium.x;

/**
 * Created by qkx on 16/11/17.
 */
public class No221 {
    public static int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) return 0;
        if (matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        int res = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = matrix[0][i] == '1' ? 1 : 0;
            res = Math.max(res, dp[i]);
        }


        /**
         * upperLeft    up
         *      left  current
         */
        for (int row = 1; row < m; row++) {
            int upperLeft = dp[0];
            dp[0] = matrix[row][0] == '1' ? 1 : 0;
            res = Math.max(res, dp[0]);

            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == '1') {
                    int current = min(dp[col - 1], dp[col], upperLeft) + 1;
                    upperLeft = dp[col];
                    dp[col] = current;

                    res = Math.max(res, current);
                } else {
                    upperLeft = dp[col];
                    dp[col] = 0;
                }
            }
        }

        return res * res;
    }

    private static int min(int a, int b, int c) {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }

    public static int maximalSquare2(char[][] matrix) {
        if (matrix.length == 0) return 0;
        if (matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        int res = 0;
        int[][] dp = new int[m][n];
        for (int row = 0; row < m; row++) {
            dp[row][0] = matrix[row][0] == '1' ? 1 : 0;
            res = Math.max(res, dp[row][0]);
        }
        for (int col = 0; col < n; col++) {
            dp[0][col] = matrix[0][col] == '1' ? 1 : 0;
            res = Math.max(res, dp[0][col]);
        }
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == '1') {
                    dp[row][col] = min(dp[row - 1][col], dp[row - 1][col - 1], dp[row][col - 1]) + 1;
                    res = Math.max(res, dp[row][col]);
                } else {
                    dp[row][col] = 0;
                    res = Math.max(res, dp[row][col]);
                }
            }
        }
        return res * res;
    }
}
