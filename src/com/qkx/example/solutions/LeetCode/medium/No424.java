package com.qkx.example.solutions.LeetCode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaixin
 * @since 2021-09-16 20:35
 */
//You are given a string s and an integer k. You can choose any character of
//the string and change it to any other uppercase English character. You can perform
//this operation at most k times.
//
// Return the length of the longest substring containing the same letter you
//can get after performing the above operations.
//
//
// Example 1:
//
//
//Input: s = "ABAB", k = 2
//Output: 4
//Explanation: Replace the two 'A's with two 'B's or vice versa.
//
//
// Example 2:
//
//
//Input: s = "AABABBA", k = 1
//Output: 4
//Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
//The substring "BBBB" has the longest repeating letters, which is 4.
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 10⁵
// s consists of only uppercase English letters.
// 0 <= k <= s.length
//
// Related Topics Hash Table String Sliding Window 👍 3032 👎 130


//leetcode submit region begin(Prohibit modification and deletion)
public class No424 {
    public static void main(String[] args) {
        String s = "AAAA";
        int k = 2;
        int res = new No424().characterReplacement(s, k);
        System.out.println(res);
    }
    /**
     * start 为左指针，初始为 0
     * end 为右指针，从头开始遍历
     * s[start, end]
     * 用 hash 表统计区间内的字符数
     * 出现次数最多的字符为 X，剩余的字符数 Y = end - start + 1 - X
     * 如果 Y < K，说明剩余字符都可以替换为 X，end 右移
     * 如果 Y == K，说明剩余 K 个字符都替换为 X，记录结果，同时end 右移
     * 如果 Y > K，说明剩余字符不能全部替换为 X，start 右移
     *
     */
    public int characterReplacement(String s, int k) {
        int result = 0;
        Map<Character, Integer> map = new HashMap<>(26);
        char c0 = s.charAt(0);
        map.put(c0, 1);
        int start = 0;
        int end = 0;
        while (start <= end) {
            int max = getMax(map);
            int len = end - start + 1;
            int remain = len - max;
            if (remain <= k) {
                result = Math.max(result, len);
                end++;
                if (end >= s.length()) {
                    break;
                } else {
                    char tail = s.charAt(end);
                    map.put(tail , map.getOrDefault(tail, 0) + 1);
                }
            } else {
                char head = s.charAt(start);
                map.put(head , map.getOrDefault(head, 0) - 1);
                start++;
            }
        }
        return result;
    }

    private int getMax(Map<Character, Integer> map) {
        int max = Integer.MIN_VALUE;
        for (Integer num : map.values()) {
            max = Math.max(max, num);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

