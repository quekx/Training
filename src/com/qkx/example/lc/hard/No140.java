package com.qkx.example.lc.hard;

import java.util.*;

/**
 * @author kaixin
 * @since 2020-12-04 10:21
 */
//Given a non-empty string s and a dictionary wordDict containing a list of non-
//empty words, add spaces in s to construct a sentence where each word is a valid
//dictionary word. Return all such possible sentences.
//
// Note:
//
//
// The same word in the dictionary may be reused multiple times in the segmentat
//ion.
// You may assume the dictionary does not contain duplicate words.
//
//
// Example 1:
//
//
//Input:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//Output:
//[
//  "cats and dog",
//  "cat sand dog"
//]
//
//
// Example 2:
//
//
//Input:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//Output:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//Explanation: Note that you are allowed to reuse a dictionary word.
//
//
// Example 3:
//
//
//Input:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//Output:
//[]
// Related Topics Dynamic Programming Backtracking
// 👍 2668 👎 426

/**
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * Related Topics
 * Hash Table
 * String
 * Dynamic Programming
 * Backtracking
 * Trie
 * Memoization
 */

//leetcode submit region begin(Prohibit modification and deletion)
public class No140 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.26% 的Java用户
     * 	内存消耗:42.2 MB,击败了62.46% 的Java用户
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new LinkedList<>();
        StringBuilder tmp = new StringBuilder();
        dfs(s, 0, wordDict, tmp, ans);
        return ans;
    }

    private void dfs(String s, int i, List<String> wordDict, StringBuilder tmp, List<String> ans) {
        if (s.length() == i) {
            ans.add(tmp.substring(0, tmp.length() - 1));
        }

        for (String word : wordDict) {
            if (match(s, i, word)) {
                tmp.append(word).append(' ');
                dfs(s, i + word.length(), wordDict, tmp, ans);;
                tmp.delete(tmp.length() - word.length() - 1, tmp.length());
            }
        }
    }

    private boolean match(String s, int i, String word) {
        for (int k = 0; k < word.length(); k++) {
            if (i + k >= s.length() || s.charAt(i + k) != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

