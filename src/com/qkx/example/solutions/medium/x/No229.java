package com.qkx.example.solutions.medium.x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 17/3/24.
 */
public class No229 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;

        int n = nums.length;
        int k = n / 3;
        int x = Integer.MIN_VALUE;
        int countX = 0;
        int y = Integer.MAX_VALUE;
        int countY = 0;

        for (int num : nums) {
            if (num == x) {
                countX++;
            } else if (num == y) {
                countY++;
            } else if (countX == 0) {
                x = num;
                countX = 1;
            } else if (countY == 0) {
                y = num;
                countY = 1;
            } else {
                countX--;
                countY--;
            }
        }

        countX = 0;
        countY = 0;
        for (int num : nums) {
            if (num == x) countX++;
            else if (num == y) countY++;
        }
        if (countX > k) res.add(x);
        if (countY > k) res.add(y);

        return res;
    }
}
