package com.qkx.example.lc.achive.medium.No4x_5x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/5/24.
 */
public class No54 {
    private static final int ORIENTATION_RIGHT = 0;
    private static final int ORIENTATION_DOWN = 1;
    private static final int ORIENTATION_LEFT = 2;
    private static final int ORIENTATION_UP = 3;

    // AC worse
    public static List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> res = new LinkedList<>();

        if (matrix.length == 0) {
            return res;
        }

        int orientation = ORIENTATION_RIGHT;
        int row = matrix.length;
        int col = matrix[0].length;
        move(matrix, 0, 0, orientation, res, 0, 0, col - 1, row - 1);

        return res;
    }

    private static void move(int[][] matrix, int currentY, int currentX, int orientation, List<Integer> res, int left, int top, int right, int bottom) {
        res.add(matrix[currentY][currentX]);
        System.out.print(matrix[currentY][currentX]);
        switch (orientation) {
            case ORIENTATION_RIGHT:
                if (currentX == right) {
                    if (currentY < bottom) {
                        move(matrix, currentY + 1, currentX, ORIENTATION_DOWN, res, left, top + 1, right, bottom);
                    }
                } else {
                    move(matrix, currentY, currentX + 1, orientation, res, left, top, right, bottom);
                }
                break;
            case ORIENTATION_DOWN:
                if (currentY == bottom) {
                    if (currentX > left) {
                        move(matrix, currentY, currentX - 1, ORIENTATION_LEFT, res, left, top, right - 1, bottom);
                    }
                } else {
                    move(matrix, currentY + 1, currentX, orientation, res, left, top, right, bottom);
                }
                break;
            case ORIENTATION_LEFT:
                if (currentX == left) {
                    if (currentY > top) {
                        move(matrix, currentY - 1, currentX, ORIENTATION_UP, res, left, top, right, bottom - 1);
                    }
                } else {
                    move(matrix, currentY, currentX - 1, orientation, res, left, top, right, bottom);
                }
                break;
            case ORIENTATION_UP:
                if (currentY == top) {
                    if (currentX < right) {
                        move(matrix, currentY, currentX + 1, ORIENTATION_RIGHT, res, left + 1, top, right, bottom);
                    }
                } else {
                    move(matrix, currentY - 1, currentX, orientation, res, left, top, right, bottom);
                }
                break;
        }
    }

    // AC
    public static List<Integer> spiralOrder2(int[][] matrix) {
        int row = matrix.length;

        List<Integer> res = new LinkedList<>();
        if (row == 0) {
            return res;
        }

        int col = matrix[0].length;

        int level = 0;
        int offset = 0;
        int i = 0;
        while (i < row * col) {
            int m = row - 2 * level;
            int n = col - 2 * level;
            int count;
            if (m == 1) {
                count = n;
            } else if (n == 1) {
                count = m;
            } else {
                count = (m + n - 2) * 2;
            }
            int pos;
            if (i == count + offset) {
                offset = i;
                level++;
                pos = 0;
                m = m - 2;
                n = n - 2;
            } else {
                pos = i - offset;
            }

            int y, x;
            if (pos <= n - 1) {
                y = level;
                x = pos + level;
            } else if (pos <= m + n - 2) {
                y = pos - n + 1 + level;
                x = n - 1 + level;
            } else if (pos <= m + n * 2 - 3) {
                y = m - 1 + level;
                x = m + n * 2 - 3 - pos + level;
            } else {
                y = m * 2 + n * 2 - 4 - pos + level;
                x = level;
            }
            res.add(matrix[y][x]);
            System.out.print(matrix[y][x]);


            i++;
        }

        return res;
    }
}