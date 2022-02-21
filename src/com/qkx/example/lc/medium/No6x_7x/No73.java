package com.qkx.example.lc.medium.No6x_7x;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qkx on 16/5/26.
 */
public class No73 {
    public static void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == 0) {
                    rowSet.add(row);
                    colSet.add(col);
                }
            }
        }

        for (int row : rowSet) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] != 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        for (int col : colSet) {
            for (int row = 0; row < m; row++) {
                if (matrix[row][col] != 0) {
                    matrix[row][col] = 0;
                }
            }
        }
    }
}
