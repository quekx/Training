package com.qkx.example.solutions.LeetCode.easy.No10x_11x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkx on 16/9/20.
 */
public class No118 {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }

        List<Integer> first = new ArrayList<>(1);
        first.add(1);
        res.add(first);

        for (int i = 1; i < numRows; i++) {
            List<Integer> last = res.get(i - 1);
            List<Integer> temp = new ArrayList<>(i + 1);
            temp.add(1);

            for (int k = 1; k <= i - 1; k++) {
                int cur = last.get(k - 1) + last.get(k);
                temp.add(cur);
            }

            temp.add(1);
            res.add(temp);
        }

        return res;
    }
}
