package com.qkx.example.solutions.LeetCode.medium.No12x_13x;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by qkx on 16/11/24.
 */
public class No130 {
    /**
     * DFS深度优先搜索递归===>>>递归深度过大,造成栈溢出
     * 由:
     * if (row > board.length - 1 || row < 0 || col > board[0].length - 1 || col < 0) return;
     * 改为:
     * if (row >= board.length - 1 || row <= 0 || col >= board[0].length - 1 || col <= 0) return;
     * 上述条件改良只是规避了该极端的测试用例,虽然在OJ上AC但是不可取
     *
     * 为了防止栈溢出,不能用递归
     * 应用:
     * 非递归DFS深度优先搜索
     * BFS广度优先搜索
     */
    // 非递归DFS
    public static void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int row = board.length;
        int col = board[0].length;

        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') dfs(i, 0, board);
            if (board[i][col - 1] == 'O') dfs(i, col - 1, board);
        }
        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') dfs(0, i, board);
            if (board[row - 1][i] == 'O') dfs(row - 1, i, board);
        }

        for (int j = 0; j < row; j++) {
            for (int k = 0; k < col; k++) {
                if (board[j][k] == '#') board[j][k] = 'O';
                else if (board[j][k] == 'O') board[j][k] = 'X';
            }
        }

    }

    // DFS >> 栈
    private static void dfs(int row, int col, char[][] board) {
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(row, col));

        while (!stack.isEmpty()) {
            Point point = stack.pop();
            int curRow = point.row;
            int curCol = point.col;
            board[curRow][curCol] = '#';

            if (curRow - 1 >= 0 && board[curRow - 1][curCol] == 'O') stack.push(new Point(curRow - 1, curCol));
            if (curRow + 1 <= board.length - 1 && board[curRow + 1][curCol] == 'O') stack.push(new Point(curRow + 1, curCol));
            if (curCol - 1 >= 0 && board[curRow][curCol - 1] == 'O') stack.push(new Point(curRow, curCol - 1));
            if (curCol + 1 <= board[0].length - 1 && board[curRow][curCol + 1] == 'O') stack.push(new Point(curRow, curCol + 1));
        }
    }

    // BFS >> 队列
    private static void bfs(int row, int col, char[][] board) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));

        while (!queue.isEmpty()) {
            Point point = queue.remove();
            int curRow = point.row;
            int curCol = point.col;
            board[curRow][curCol] = '#';

            if (curRow - 1 >= 0 && board[curRow - 1][curCol] == 'O') queue.add(new Point(curRow - 1, curCol));
            if (curRow + 1 <= board.length - 1 && board[curRow + 1][curCol] == 'O') queue.add(new Point(curRow + 1, curCol));
            if (curCol - 1 >= 0 && board[curRow][curCol - 1] == 'O') queue.add(new Point(curRow, curCol - 1));
            if (curCol + 1 <= board[0].length - 1 && board[curRow][curCol + 1] == 'O') queue.add(new Point(curRow, curCol + 1));
        }
    }

    /**
     * sample
     *
     */
    public static void solveTest(char[][] board) {
        if (board == null || board.length == 0) return;

        int row = board.length;
        int col = board[0].length;

        //check first and last col
        for (int i = 0; i < row; i++) {

            if (board[i][0] == 'O') getEmAll(board, i, 1);
            if (board[i][col - 1] == 'O') getEmAll(board, i, col - 2);
        }
        //check first and last  row
        for (int i = 0; i < col; i++) {

            if (board[0][i] == 'O') getEmAll(board, 1, i);
            if (board[row - 1][i] == 'O') getEmAll(board, row - 2, i);
        }

        //Switch all 'O's to 'X's and 'Y's to 'O's
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++)
                if (board[i][j] == 'Y') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
        }
    }

    private static void getEmAll(char[][] board, int row, int col) {
        if (row >= board.length - 1 || row <= 0 || col >= board[0].length - 1 || col <= 0) return;
        if (board[row][col] == 'X' || board[row][col] == 'Y') return;
        if (board[row][col] == 'O') board[row][col] = 'Y';

        getEmAll(board, row + 1, col);
        getEmAll(board, row, col + 1);
        getEmAll(board, row - 1, col);
        getEmAll(board, row, col - 1);

    }

    static class Point {
        int row;
        int col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
