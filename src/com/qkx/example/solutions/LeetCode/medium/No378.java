package com.qkx.example.solutions.LeetCode.medium;

import java.util.PriorityQueue;

/**
 * Created by qkx on 17/7/8.
 */
public class No378 {
    /**
     * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
     * find the kth smallest element in the matrix.
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     *
     * matrix = [
     * [ 1,  5,  9],
     * [10, 11, 13],
     * [12, 13, 15]
     * ],
     * k = 8,
     *
     * return 13.
     */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) return -1;

        int rows = matrix.length;
        int cols = matrix[0].length;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (int col = 0; col < cols; col++) {
            priorityQueue.add(new Node(0, col, matrix[0][col]));
        }

        for (int i = 1; i <= k - 1; i++) {
            Node node = priorityQueue.poll();
            int nextRow = node.row + 1;
            int nextCol = node.col;
            if (nextRow < rows) {
                priorityQueue.add(new Node(nextRow, nextCol, matrix[nextRow][nextCol]));
            }
        }

        return priorityQueue.poll().val;
    }

    class Node implements Comparable<Node> {

        int row;
        int col;
        int val;

        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            int t = this.val - o.val;
            if (t < 0) return -1;
            else if (t == 0) return 0;
            else return 1;
        }
    }
}
