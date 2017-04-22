package com.qkx.example.solutions.LeetCode.medium.x;

/**
 * Created by qkx on 16/11/17.
 */
public class No304 {
    public class NumMatrix {

        private int[][] sums;
        private boolean isEmpty;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                isEmpty = true;
                return;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            sums = new int[m][n];
            sums[0][0] = matrix[0][0];
            for (int row = 1; row < m; row++) {
                sums[row][0] = sums[row - 1][0] + matrix[row][0];
            }
            for (int col = 1; col < n; col++) {
                sums[0][col] = sums[0][col - 1] + matrix[0][col];
            }

            for (int row = 1; row < m; row++) {
                for (int col = 1; col < n; col++) {
                    sums[row][col] = sums[row - 1][col] + sums[row][col - 1] - sums[row - 1][col - 1] + matrix[row][col];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (isEmpty) return 0;

            int upperLeft;
            if (row1 == 0 || col1 == 0) {
                upperLeft = 0;
            } else {
                upperLeft = sums[row1 - 1][col1 - 1];
            }

            int up;
            if (row1 == 0) {
                up = 0;
            } else {
                up = sums[row1 - 1][col2];
            }

            int left;
            if (col1 == 0) {
                left = 0;
            } else {
                left = sums[row2][col1 - 1];
            }

            return sums[row2][col2] + upperLeft - up - left;
        }
    }
}
