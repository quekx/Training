package com.qkx.example.lc.achive.medium.No6x_7x;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/5/26.
 */
public class No78 {
    public static List<List<Integer>> subsets(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        res.add(temp);

        add(res, temp, nums.length, 0, nums);

        return res;
    }
    private static void add(List<List<Integer>> res, List<Integer> temp, int k, int start, int[] nums) {
        for (int i = start; i < nums.length; i++) {
            List<Integer> tempI = new LinkedList<>(temp);
            tempI.add(nums[i]);
            res.add(tempI);
            if (k != 1) {
                add(res, tempI, k - 1, i + 1, nums);
            }
        }
    }
}
