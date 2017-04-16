package com.qkx.example.solutions.medium;

/**
 * Created by qkx on 17/4/16.
 */
public class No240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            int cur = matrix[bottom][left];
            if (cur < target) {
                left++;
            } else if (cur > target) {
                bottom--;
            } else {
                return true;
            }
        }

        return false;
    }
}
