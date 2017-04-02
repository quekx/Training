package com.qkx.example.solutions.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by qkx on 16/9/22.
 */
public class No128 {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        Map<Integer, Integer> frontMap = new HashMap<>();
        Map<Integer, Integer> rearMap = new HashMap<>();
//        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
//            if (set.contains(num)) {
//                continue;
//            } else {
//                set.add(num);
//            }
            if (frontMap.containsKey(num) || rearMap.containsKey(num)) continue;

            if (!frontMap.containsKey(num + 1) && !rearMap.containsKey(num - 1)) {
                frontMap.put(num, 1);
                rearMap.put(num, 1);
            } else if (frontMap.containsKey(num + 1) && rearMap.containsKey(num - 1)) {
                int preLen = rearMap.get(num - 1);
                int frontIndex = num - preLen;

                int postLen = frontMap.get(num + 1);
                int rearIndex = num + postLen;

                int len = preLen + postLen + 1;
                frontMap.remove(num + 1);
                rearMap.remove(num - 1);

                rearMap.put(rearIndex, len);
                frontMap.put(frontIndex, len);
            } else if (frontMap.containsKey(num + 1)) {
                int postLen = frontMap.get(num + 1);
                int rearIndex = num + postLen;

                frontMap.remove(num + 1);
                frontMap.put(num, postLen + 1);
                rearMap.put(rearIndex, postLen + 1);
            } else {
                int preLen = rearMap.get(num - 1);
                int frontIndex = num - preLen;

                rearMap.remove(num - 1);
                rearMap.put(num, preLen + 1);
                frontMap.put(frontIndex, preLen + 1);
            }
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : frontMap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }

        return max;
    }
}
