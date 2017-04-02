package com.qkx.example.solutions.medium.No6x_7x;

/**
 * Created by qkx on 16/5/26.
 */
public class No74 {
    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;
        int middle;
        while (left <= right) {
            middle = (left + right) / 2;
            int middleValue = matrix[middle / n][middle % n];
            if (middleValue == target) {
                return true;
            } else if (middleValue < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return false;
    }
}
