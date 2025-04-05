package com.qkx.example.lc.achive.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kaixin
 * @since 2020-11-10 19:05
 */
//Given an m x n matrix of non-negative integers representing the height of each
// unit cell in a continent, the "Pacific ocean" touches the left and top edges of
// the matrix and the "Atlantic ocean" touches the right and bottom edges.
//
// Water can only flow in four directions (up, down, left, or right) from a cell
// to another one with height equal or lower.
//
// Find the list of grid coordinates where water can flow to both the Pacific an
//d Atlantic ocean.
//
// Note:
//
//
// The order of returned grid coordinates does not matter.
// Both m and n are less than 150.
//
//
//
//
// Example:
//
//
//Given the following 5x5 matrix:
//
//  Pacific ~   ~   ~   ~   ~
//       ~  1   2   2   3  (5) *
//       ~  3   2   3  (4) (4) *
//       ~  2   4  (5)  3   1  *
//       ~ (6) (7)  1   4   5  *
//       ~ (5)  1   1   2   4  *
//          *   *   *   *   * Atlantic
//
//Return:
//
//[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with paren
//theses in above matrix).
//
//
//
// Related Topics Depth-first Search Breadth-first Search
// 👍 1548 👎 331


//leetcode submit region begin(Prohibit modification and deletion)
public class No417 {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }

        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        boolean[][] pacific = new boolean[rowLen][colLen];
        for (int col = 0; col < colLen; col++) {
            visitPacific(pacific, matrix, 0, col);
        }
        for (int row = 0; row < rowLen; row++) {
            visitPacific(pacific, matrix, row, 0);
        }

        boolean[][] atlantic = new boolean[rowLen][colLen];
        for (int col = 0; col < colLen; col++) {
            visitPacific(atlantic, matrix, rowLen - 1, col);
        }
        for (int row = 0; row < rowLen; row++) {
            visitPacific(atlantic, matrix, row, colLen - 1);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (pacific[row][col] && atlantic[row][col]) {
                    List<Integer> pair = new ArrayList<>(2);
                    pair.add(row);
                    pair.add(col);
                    result.add(pair);
                }
            }
        }

        return result;
    }

    public void visitPacific(boolean[][] area, int[][] matrix, int row, int col) {
        if (area[row][col]) {
            return;
        }

        area[row][col] = true;
        if (row - 1 >= 0 && matrix[row - 1][col] >= matrix[row][col]) {
            visitPacific(area, matrix, row - 1, col);
        }
        if (row + 1 <= matrix.length - 1 && matrix[row + 1][col] >= matrix[row][col]) {
            visitPacific(area, matrix, row + 1, col);
        }
        if (col - 1 >= 0 && matrix[row][col - 1] >= matrix[row][col]) {
            visitPacific(area, matrix, row, col - 1);
        }
        if (col + 1 <= matrix[0].length - 1 && matrix[row][col + 1] >= matrix[row][col]) {
            visitPacific(area, matrix, row, col + 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

