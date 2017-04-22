package com.qkx.example.solutions.LeetCode.medium.No6x_7x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/5/26.
 */
public class No77 {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();

        List<Integer> temp = new LinkedList<>();

        add(res, temp, n, k, 1);

        return res;
    }
    private static void add(List<List<Integer>> res, List<Integer> temp, int n, int k, int start) {
        for (int i = start; i <= n; i++) {
            List<Integer> tempI = new LinkedList<>(temp);
            tempI.add(i);
            if (k == 1) {
                res.add(tempI);
            } else {
                add(res, tempI, n, k - 1, i + 1);
            }
        }
    }
}
