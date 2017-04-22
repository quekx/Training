package com.qkx.example.solutions.LeetCode.medium.x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 17/3/24.
 */
public class No228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;

        int n = nums.length;
        int left = 0;
        while (left < n) {
            StringBuilder builder = new StringBuilder();
            int right = left + 1;
            while (right < n && nums[right - 1] + 1 == nums[right]) {
                right++;
            }
            builder.append(nums[left]);
            if (left < right - 1) {
                builder.append("->");
                builder.append(nums[right - 1]);
            }

            res.add(builder.toString());
            left = right;
        }

        return res;
    }
}
