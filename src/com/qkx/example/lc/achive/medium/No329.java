package com.qkx.example.lc.achive.medium;

/**
 * @author kaixin
 * @since 2021-09-27 11:06
 */
//Given an m x n integers matrix, return the length of the longest increasing
//path in matrix.
//
// From each cell, you can either move in four directions: left, right, up, or
//down. You may not move diagonally or move outside the boundary (i.e., wrap-
//around is not allowed).
//
//
// Example 1:
//
//
//Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
//Output: 4
//Explanation: The longest increasing path is [1, 2, 6, 9].
//
//
// Example 2:
//
//
//Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
//Output: 4
//Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally
//is not allowed.
//
//
// Example 3:
//
//
//Input: matrix = [[1]]
//Output: 1
//
//
//
// Constraints:
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 200
// 0 <= matrix[i][j] <= 2³¹ - 1
//
// Related Topics Dynamic Programming Depth-First Search Breadth-First Search
//Graph Topological Sort Memoization 👍 4118 👎 69


//leetcode submit region begin(Prohibit modification and deletion)
public class No329 {

    //Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
    //Output: 4
    //Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally
    public static void main(String[] args) {
        int[][] matrix = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        int len = new No329().longestIncreasingPath(matrix);
        System.out.println(len);
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] count = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                count[row][col] = Integer.MIN_VALUE;
            }
        }

        int max = Integer.MIN_VALUE;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                max = Math.max(max, traverse(matrix, row, col, Integer.MIN_VALUE, count));
            }
        }
        return max;
    }

    private int traverse(int[][] matrix, int i, int k, int pre, int[][] count) {
        if (i < 0 || i > matrix.length - 1) {
            return 0;
        }
        if (k < 0 || k > matrix[0].length - 1) {
            return 0;
        }

        int cur = matrix[i][k];
        if (cur == Integer.MIN_VALUE) {
            return 0;
        }
        if (cur <= pre) {
            return 0;
        }

        if (count[i][k] >= 0) {
            return count[i][k];
        }

        matrix[i][k] = Integer.MIN_VALUE;

        int nextLen = Integer.MIN_VALUE;
        nextLen = Math.max(nextLen, traverse(matrix, i - 1, k, cur, count));
        nextLen = Math.max(nextLen, traverse(matrix, i + 1, k, cur, count));
        nextLen = Math.max(nextLen, traverse(matrix, i, k - 1, cur, count));
        nextLen = Math.max(nextLen, traverse(matrix, i, k + 1, cur, count));

        matrix[i][k] = cur;
        count[i][k] = nextLen + 1;
        return nextLen + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

