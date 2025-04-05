package com.qkx.example.lc.achive.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kaixin
 * @since 2021-11-18 10:54
 */
//Given two strings s and p, return an array of all the start indices of p's
//anagrams in s. You may return the answer in any order.
//
// An Anagram is a word or phrase formed by rearranging the letters of a
//different word or phrase, typically using all the original letters exactly once.
//
//
// Example 1:
//
//
//Input: s = "cbaebabacd", p = "abc"
//Output: [0,6]
//Explanation:
//The substring with start index = 0 is "cba", which is an anagram of "abc".
//The substring with start index = 6 is "bac", which is an anagram of "abc".
//
//
// Example 2:
//
//
//Input: s = "abab", p = "ab"
//Output: [0,1,2]
//Explanation:
//The substring with start index = 0 is "ab", which is an anagram of "ab".
//The substring with start index = 1 is "ba", which is an anagram of "ab".
//The substring with start index = 2 is "ab", which is an anagram of "ab".
//
//
//
// Constraints:
//
//
// 1 <= s.length, p.length <= 3 * 10⁴
// s and p consist of lowercase English letters.
//
// Related Topics Hash Table String Sliding Window 👍 5317 👎 218


//leetcode submit region begin(Prohibit modification and deletion)
public class No438 {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(new No438().findAnagrams(s, p));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if (s.length() < p.length()) {
            return result;
        }

        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pArr.length; i++) {
            sCount[sArr[i] - 'a']++;
            pCount[pArr[i] - 'a']++;
        }
        if (isOk(sCount, pCount)) {
            result.add(0);
        }

        for (int i = 1; i + p.length() - 1 < s.length(); i++) {
            int end = i + p.length() - 1;
            sCount[sArr[i - 1] - 'a']--;
            sCount[sArr[end] - 'a']++;
            if (isOk(sCount, pCount)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isOk(int[] sCount, int[] pCount) {
        if (sCount.length != pCount.length) {
            return false;
        }
        for (int i = 0; i < sCount.length; i++) {
            if (sCount[i] != pCount[i]) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

