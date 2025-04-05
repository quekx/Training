package com.qkx.example.lc.achive.medium.x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 17/3/24.
 */
public class No216 {
    /**
     * Find all possible combinations of k numbers that add up to a number n,
     * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new LinkedList<>();
        if (k > n || k * 9 < n) return res;

        List<Integer> temp = new LinkedList<>();
        add(k, 0, n, temp, res);
        return res;
    }

    public static void add(int k, int max, int remain, List<Integer> temp, List<List<Integer>> res) {
        if (max >= remain) return;

        if (k == 1 && remain <= 9) {
            temp.add(remain);
            res.add(temp);
            return;
        }

        for (int next = max + 1; next <= 9; next++) {
            List<Integer> newList = new LinkedList<>(temp);
            newList.add(next);
            add(k - 1, next, remain - next, newList, res);
        }
    }
}
