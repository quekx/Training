package com.qkx.example.lc.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kaixin
 * @since 2020-12-02 10:59
 */
//The n-queens puzzle is the problem of placing n queens on an n x n chessboard
//such that no two queens attack each other.
//
// Given an integer n, return the number of distinct solutions to the n-queens p
//uzzle.
//
//
// Example 1:
//
//
//Input: n = 4
//Output: 2
//Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
//
//
//
// Example 2:
//
//
//Input: n = 1
//Output: 1
//
//
//
// Constraints:
//
//
// 1 <= n <= 9
//
// Related Topics Backtracking
// 👍 665 👎 167


//leetcode submit region begin(Prohibit modification and deletion)
public class No52 {
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                board[i][k] = '.';
            }
        }
        List<List<String>> result = new ArrayList<>();
        put(0, board, result, n);
        return result.size();
    }

    /**
     * 开始放置第row行
     * @param row
     * @param board
     * @param result
     * @param n
     */
    private void put(int row, char[][] board, List<List<String>> result, int n) {
        for (int col = 0; col <= n - 1; col++) {
            if (!check(row, col, board, n)) {
                continue;
            }
            board[row][col] = 'Q';
            if (row == n - 1) {
                // 填充完成
                List<String> list = new ArrayList<>(n);
                for (int i = 0; i <= n - 1; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k <= n - 1; k++) {
                        sb.append(board[i][k]);
                    }
                    list.add(sb.toString());
                }
                result.add(list);
            } else {
                put(row + 1, board, result, n);
            }
            // 回溯将数据还原
            board[row][col] = '.';
        }
    }

    /**
     * 判断当前[row, col]是否可以放置
     *
     * @param row
     * @param col
     * @param board
     * @return
     */
    private boolean check(int row, int col, char[][] board, int n) {
        // 同列检查
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // 左上斜检查
        for (int i = row - 1, k = col - 1; i >= 0 && k >= 0; i--, k--) {
            if (board[i][k] == 'Q') {
                return false;
            }
        }
        // 右上斜检查
        for (int i = row - 1, k = col + 1; i >= 0 && k <= n - 1; i--, k++) {
            if (board[i][k] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

