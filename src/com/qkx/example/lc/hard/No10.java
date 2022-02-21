package com.qkx.example.lc.hard;

/**
 * @author kaixin
 * @since 2020-11-27 10:28
 */
//Given an input string (s) and a pattern (p), implement regular expression matc
//hing with support for '.' and '*' where:
//
//
// '.' Matches any single character.
// '*' Matches zero or more of the preceding element.
//
//
// The matching should cover the entire input string (not partial).
//
//
// Example 1:
//
//
//Input: s = "aa", p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".
//
//
// Example 2:
//
//
//Input: s = "aa", p = "a*"
//Output: true
//Explanation: '*' means zero or more of the preceding element, 'a'. Therefore,
//by repeating 'a' once, it becomes "aa".
//
//
// Example 3:
//
//
//Input: s = "ab", p = ".*"
//Output: true
//Explanation: ".*" means "zero or more (*) of any character (.)".
//
//
// Example 4:
//
//
//Input: s = "aab", p = "c*a*b"
//Output: true
//Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, i
//t matches "aab".
//
//
// Example 5:
//
//
//Input: s = "mississippi", p = "mis*is*p*."
//Output: false
//
//
//
// Constraints:
//
//
// 0 <= s.length <= 20
// 0 <= p.length <= 30
// s contains only lowercase English letters.
// p contains only lowercase English letters, '.', and '*'.
// It is guaranteed for each appearance of the character '*', there will be a pr
//evious valid character to match.
//
// Related Topics String Dynamic Programming Backtracking
// 👍 4995 👎 791


//leetcode submit region begin(Prohibit modification and deletion)
public class No10 {
    /**
     * dp[i][j] i -> [1, sLen]; j -> [1, pLen]
     * 表示 s[0]~s[i-1]和p[0]~p[j-1]是否匹配
     * 情况
     * 1. p[j-1] == '.', dp[i][j] = dp[i-1][j-1]
     *
     * 2. p[j-1] == '*', dp[i][j] = dp[i-1][j-1] + dp[i][j-1] + dp[i-1][j]
     * p[j-2]和s[i-1]不匹配，只有一种情况：'x*'代表0个字符 ==> dp[i][j-2]
     * p[j-2]和s[i-1]匹配(字符相等或者是'.')，有三种情况
     * 1）'x*'匹配0个字符 ==> dp[i][j-2]
     * 2）'x*'匹配一个字符 ==> dp[i][j-1]
     * 3）'x*'匹配多个字符，即p[j]不只影响s[i]，也影响s[i-1] ==> dp[i-1][j]
     *
     * 3. p[j-1] 为字符，dp[i][j] = s[i-1] == p[j-1] ? dp[i - 1][j - 1] : false;
     *
     * @param s 匹配字符
     * @param p 正则表达式
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();

        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int j = 1; j <= pLen; j++) {
            char pj = p.charAt(j - 1);
            if (pj == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                char pj = p.charAt(j - 1);
                if (pj == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pj == '*') {
                    dp[i][j] = s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.'
                            ? dp[i][j - 2]
                            : dp[i][j - 1] || dp[i - 1][j] || dp[i][j - 2];
                } else {
                    dp[i][j] = s.charAt(i - 1) == pj && dp[i - 1][j - 1];
                }
            }
        }
        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                System.out.println(s.substring(0, i) + " >>> " + p.substring(0, j) + " <<< " + dp[i][j]);
            }
        }
        return dp[sLen][pLen];
    }

    /**
     * 题意搞错！！！ * 代表前一个字符重复任意次，而不是匹配任意多个字符
     * <p>
     * dp[i][j] i -> [1, sLen]; j -> [1, pLen]
     * 表示 s[0]~s[i-1]和p[0]~p[j-1]是否匹配
     * 情况
     * 1. p[j-1] == '.', dp[i][j] = dp[i-1][j-1]
     * 2. p[j-1] == '*', dp[i][j] = dp[i-1][j-1] + dp[i][j-1] + dp[i-1][j]
     * '*'不匹配字符 ==> dp[i][j-1]
     * '*'匹配一个字符 ==> dp[i-1][j-1]
     * '*'匹配多个字符，即p[j]不只影响s[i]，也影响s[i-1] ==> dp[i-1][j]
     * 3. p[j-1] 为字符，dp[i][j] = s[i-1] == p[j-1] ? dp[i - 1][j - 1] : false;
     *
     * @param s 匹配字符
     * @param p 正则表达式
     * @return
     */
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();
        if (sLen == 0 && pLen == 0) {
            return true;
        } else if (sLen == 0) {
            return false;
        } else if (pLen == 0) {
            return false;
        }

        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                char pj = p.charAt(j - 1);
                if (pj == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pj == '*') {
                    dp[i][j] = dp[i][j] || dp[i - 1][j] || dp[i - 1][j - 1];
                } else {
                    dp[i][j] = s.charAt(i - 1) == pj && dp[i - 1][j - 1];
                }
            }
        }
        return dp[sLen][pLen];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
