package com.qkx.example.lc.achive.medium;
//Given an m x n matrix board where each cell is a battleship 'X' or empty '.',
//return the number of the battleships on board.
//
// Battleships can only be placed horizontally or vertically on board. In other
//words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k
//rows, 1 column), where k can be of any size. At least one horizontal or vertical
//cell separates between two battleships (i.e., there are no adjacent battleships)
//.
//
//
// Example 1:
//
//
//Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
//Output: 2
//
//
// Example 2:
//
//
//Input: board = [["."]]
//Output: 0
//
//
//
// Constraints:
//
//
// m == board.length
// n == board[i].length
// 1 <= m, n <= 200
// board[i][j] is either '.' or 'X'.
//
//
//
// Follow up: Could you do it in one-pass, using only O(1) extra memory and
//without modifying the values board?
// Related Topics Array Depth-First Search Matrix 👍 1128 👎 663
/**
 * @author kaixin
 * @since 2021-09-09 16:52
 */
public class No419 {
    public int countBattleships(char[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }

        int rows = board.length;
        int cols = board[0].length;
        int result = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == 'X') {
                    result++;
                    dfs(board, row, col);
                }
            }
        }

        return result;
    }

    private void dfs(char[][] board, int row, int col) {
        if (row < board.length && col < board[0].length && board[row][col] == 'X') {
            board[row][col] = 'Y';
            dfs(board, row + 1, col);
            dfs(board, row, col + 1);
        }
    }
}
