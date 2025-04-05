package com.qkx.example.lc.achive.hard;

import java.util.Arrays;

/**
 * @author kaixin
 * @since 2020-11-30 15:13
 */
//Write a program to solve a Sudoku puzzle by filling the empty cells.
//
// A sudoku solution must satisfy all of the following rules:
//
//
// Each of the digits 1-9 must occur exactly once in each row.
// Each of the digits 1-9 must occur exactly once in each column.
// Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes
// of the grid.
//
//
// The '.' character indicates empty cells.
//
//
// Example 1:
//
//
//Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5"
//,".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".","."
//,".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".","."
//,"6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"
//],[".",".",".",".","8",".",".","7","9"]]
//Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4
//","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3
//"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],[
//"9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3",
//"4","5","2","8","6","1","7","9"]]
//Explanation: The input board is shown above and the only valid solution is sho
//wn below:
//
//
//
//
//
// Constraints:
//
//
// board.length == 9
// board[i].length == 9
// board[i][j] is a digit or '.'.
// It is guaranteed that the input board has only one solution.
//
// Related Topics Hash Table Backtracking
// 👍 2271 👎 93


//leetcode submit region begin(Prohibit modification and deletion)
public class No37 {

    public static void main(String[] args) {
        char[][] b = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new No37().solveSudoku(b);
        for (char[] r : b) {
            System.out.println(Arrays.toString(r));
        }
    }

    public void solveSudoku(char[][] board) {
         put(board, 0);
    }

    private boolean put(char[][] board, int i) {
        if (i >= 9 * 9) {
            return true;
        }

        int row = i / 9;
        int col = i - row * 9;
        if (board[row][col] != '.') {
            return put(board, i + 1);
        }

        for (char num = '1'; num <= '9'; num++) {
            if (check(board, row, col, num)) {
                board[row][col] = num;
                if (put(board, i + 1)) {
                    return true;
                } else {
                    board[row][col] = '.';
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, int row, int col, char curNum) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == curNum) {
                return false;
            }
        }

        for (int k = 0; k < 9; k++) {
            if (board[row][k] == curNum) {
                return false;
            }
        }

        int subRow = row / 3 * 3;
        int subCol = col / 3 * 3;
        for (int i = subRow; i < subRow + 3; i++) {
            for (int k = subCol; k < subCol + 3; k++) {
                if (board[i][k] == curNum) {
                    return false;
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

