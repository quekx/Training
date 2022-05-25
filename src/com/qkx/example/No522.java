package com.qkx.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return 0;
            }
            return o1.length() < o2.length() ? 1 : -1;
        });

        Set<String> set = new HashSet<>();
        set.add(strs[0]);
        for (int i = 1; i < strs.length; i++) {
//            if ()
        }
        return 0;
    }

    private boolean existParent(Set<String> set, String str) {
        for (String base : set) {
            if (isCommonSubSequence(base, str)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCommonSubSequence(String base, String str) {
        if (base.length() < str.length()) {
            return false;
        }

        int i = 0;
        int k = 0;
        while (k < str.length()) {
            while (i < base.length() && base.charAt(i) != str.charAt(k)) {
                i++;
            }
            if (i >= base.length()) {
                return false;
            }
            k++;
            i++;
        }
        return true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

