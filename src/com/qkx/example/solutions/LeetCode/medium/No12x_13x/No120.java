package com.qkx.example.solutions.LeetCode.medium.No12x_13x;

import java.util.List;

/**
 * Created by qkx on 16/9/20.
 */
public class No120 {
    public int minimumTotal(List<List<Integer>> triangle) {

        int rows = triangle.size();
        if (rows == 0) {
            return 0;
        } else if (rows == 1) {
            return triangle.get(0).get(0);
        }

        int[] temp = new int[rows];
        int[] res = new int[rows];
        temp[0] = triangle.get(0).get(0);
        res[0] = triangle.get(0).get(0);

        for (int row = 1; row < rows; row++) {
            List<Integer> cur = triangle.get(row);

            temp[0] = res[0] + cur.get(0);
            for (int k = 1; k <= row - 1; k++) {
                temp[k] = Math.min(res[k - 1], res[k]) + cur.get(k);
            }
            temp[row] = res[row - 1] + cur.get(row);

            int[] t = temp;
            temp = res;
            res = t;
        }
        int min = Integer.MAX_VALUE;
        for (int num : res) {
            min = num < min ? num : min;
        }
        return min;
    }
}
