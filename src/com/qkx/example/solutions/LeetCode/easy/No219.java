package com.qkx.example.solutions.LeetCode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qkx on 17/3/24.
 */
public class No219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer pre = map.get(nums[i]);
            if (pre != null && i - pre <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
