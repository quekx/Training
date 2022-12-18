package com.qkx.example.lc.medium;

import java.util.*;

/**
 * @author kaixin
 * @since 2022-04-18 15:21
 */
//Given an array of strings strs, return the length of the longest uncommon
//subsequence between them. If the longest uncommon subsequence does not exist,
//return -1.
//
// An uncommon subsequence between an array of strings is a string that is a
//subsequence of one string but not the others.
//
// A subsequence of a string s is a string that can be obtained after deleting
//any number of characters from s.
//
//
// For example, "abc" is a subsequence of "aebdc" because you can delete the
//underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc"
//include "aebdc", "aeb", and "" (empty string).
//
//
//
// Example 1:
// Input: strs = ["aba","cdc","eae"]
//Output: 3
// Example 2:
// Input: strs = ["aaa","aaa","aa"]
//Output: -1
//
//
// Constraints:
//
//
// 2 <= strs.length <= 50
// 1 <= strs[i].length <= 10
// strs[i] consists of lowercase English letters.
//
// Related Topics Array Hash Table Two Pointers String Sorting 👍 366 👎 1063


//leetcode submit region begin(Prohibit modification and deletion)
class No522 {

    /**
     *  解答成功:
     * 	执行耗时:3 ms,击败了63.54% 的Java用户
     * 	内存消耗:41.6 MB,击败了51.93% 的Java用户
     */
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (a, b) -> b.length() - a.length());
        for (int i = 0; i < strs.length; i++) {
            if (!isSunSeqOfPre(i, strs)) {
                return strs[i].length();
            }
        }
        return -1;
    }

    private boolean isSunSeqOfPre(int i, String[] str) {
        for (int j = 0; j < str.length; j++) {
            if (i == j) {
                continue;
            }
            if (isSubSeq(str[i], str[j])) {
                return true;
            }
        }
        return false;
    }

    private boolean isSubSeq(String word, String parent) {
        if (word.length() > parent.length()) {
            return false;
        }
        int i = 0, j = 0;
        while (i < word.length() && j < parent.length()) {
            if (word.charAt(i) == parent.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == word.length();
    }

    /**
     * 解答成功:
     * 执行耗时:7 ms,击败了13.81% 的Java用户
     * 内存消耗:42.6 MB,击败了9.94% 的Java用户
     */
    public int findLUSlength2(String[] strs) {
        Map<String, Integer> counter = new HashMap<>();
        Map<Integer, Set<String>> groups = new HashMap<>();
        int maxLen = 0;
        for (String str : strs) {
            counter.put(str, counter.getOrDefault(str, 0) + 1);
            groups.computeIfAbsent(str.length(), k -> new HashSet<>()).add(str);
            maxLen = Math.max(maxLen, str.length());
        }
        // 1. max length level group
        Set<String> maxGroup = groups.get(maxLen);
        for (String word : maxGroup) {
            if (counter.get(word) == 1) {
                return maxLen;
            }
        }

        // 2. next level group
        PriorityQueue<Integer> lengthLevels = new PriorityQueue<>((a, b) -> b - a);
        lengthLevels.addAll(groups.keySet());
        lengthLevels.poll();
        while (!lengthLevels.isEmpty()) {
            int len = lengthLevels.poll();
            Set<String> group = groups.get(len);
            for (String word : group) {
                if (!isSubSeqOfMaxGroup(word, maxGroup) && counter.get(word) == 1) {
                    return len;
                }
            }
        }
        return -1;
    }

    private boolean isSubSeqOfMaxGroup(String word, Set<String> maxGroup) {
        for (String parent : maxGroup) {
            if (isSubSeq(word, parent)) {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

