package com.qkx.example.solutions.LeetCode.medium.No6x_7x;

/**
 * Created by qkx on 16/5/27.
 */
public class No79 {
    /**
     * AC
     * You are here!
     * Your runtime beats 99.44% of javasubmissions.
     */
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == words[0]) {
                    boolean temp = find(board, words, 0, row, col);
                    if (temp) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static boolean find(char[][] board, char[] words, int current, int currentY, int currentX) {
        if (current == words.length - 1) {
            return true;
        }
        board[currentY][currentX] = '.';
        if (currentY - 1 >= 0 && board[currentY - 1][currentX] == words[current + 1]) {
            boolean temp = find(board, words, current + 1, currentY - 1, currentX);
            if (temp) {
                return true;
            }
        }
        if (currentY + 1 <= board.length - 1 && board[currentY + 1][currentX] == words[current + 1]) {
            boolean temp = find(board, words, current + 1, currentY + 1, currentX);
            if (temp) {
                return true;
            }
        }
        if (currentX - 1 >= 0 && board[currentY][currentX - 1] == words[current + 1]) {
            boolean temp = find(board, words, current + 1, currentY, currentX - 1);
            if (temp) {
                return true;
            }
        }
        if (currentX + 1 <= board[0].length - 1 && board[currentY][currentX + 1] == words[current + 1]) {
            boolean temp = find(board, words, current + 1, currentY, currentX + 1);
            if (temp) {
                return true;
            }
        }
        board[currentY][currentX] = words[current];
        return false;
    }

}
