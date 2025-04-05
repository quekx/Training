package com.qkx.example.lc.achive.medium.No8x_9x;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/5/29.
 */
public class No90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        List<Integer> temp = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        res.add(temp);

        add(nums, temp, res, 0);

        return res;
    }
    private void add(int[] nums, List<Integer> temp, List<List<Integer>> res, int start) {

        int last = Integer.MIN_VALUE;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] != last) {
                List<Integer> subSet = new LinkedList<>(temp);
                subSet.add(nums[i]);
                res.add(subSet);
                last = nums[i];

                if (i != nums.length - 1) {
                    add(nums, subSet, res, i + 1);
                }
            }
        }

    }
}
