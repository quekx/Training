package com.qkx.example.solutions.medium.No4x_5x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/5/24.
 */
public class No59 {
    public static int[][] generateMatrix(int n) {

        int[][] res = new int[n][n];

        int row = n;
        int col = n;

        int level = 0;
        int offset = 0;
        int i = 0;
        while (i < row * col) {
            int r = row - 2 * level;
            int c = col - 2 * level;
            int count;
            if (r == 1) {
                count = c;
            } else if (c == 1) {
                count = r;
            } else {
                count = (r + c - 2) * 2;
            }
            int pos;
            if (i == count + offset) {
                offset = i;
                level++;
                pos = 0;
                r = r - 2;
                c = c - 2;
            } else {
                pos = i - offset;
            }

            int y, x;
            if (pos <= c - 1) {
                y = level;
                x = pos + level;
            } else if (pos <= r + c - 2) {
                y = pos - c + 1 + level;
                x = c - 1 + level;
            } else if (pos <= r + c * 2 - 3) {
                y = r - 1 + level;
                x = r + c * 2 - 3 - pos + level;
            } else {
                y = r * 2 + c * 2 - 4 - pos + level;
                x = level;
            }
            res[y][x] = i + 1;

            i++;
        }

        return res;
    }
}
