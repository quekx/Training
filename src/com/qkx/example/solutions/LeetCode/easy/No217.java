package com.qkx.example.solutions.LeetCode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qkx on 17/3/24.
 */
public class No217 {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }
}
