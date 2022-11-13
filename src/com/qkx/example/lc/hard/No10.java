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
     * 动态规划
     * dp[i][j] 表示 s[0][i - 1] 和 p[0][j - 1] 是否匹配 1 <= i <= len(s), 1 <= j <= len(p)
     * i = 0 和 j = 0 代表空串
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.2 MB
     * , 在所有 Java 提交中击败了
     * 42.69%
     * 的用户
     * 通过测试用例：
     * 353 / 353
     */
    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;
        for (int j = 1; j <= plen; j++) {
            // dp[0][j]
            char pk = p.charAt(j - 1);
            if (pk == '*') {
                dp[0][j] = dp[0][j - 2];
            }
            // 其余均无法匹配
        }

        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= plen; j++) {
                // dp[i][j]
                char pk = p.charAt(j - 1);
                if (pk == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pk == '*') {
                    // 不匹配
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                        continue;
                    }
                    // 匹配
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 3) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - 2];
                    }
                } else if (pk == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[slen][plen];
    }

    /**
     * 递归实现
     */
    public boolean isMatch2(String s, String p) {
        return subMatch(s, s.length() - 1, p, p.length() - 1);
    }

    public boolean subMatch(String s, int j, String p, int k) {
        if (j == -1 && k == -1) {
            return true;
        }
        if (k == -1) {
            return false;
        }

        char pk = p.charAt(k);
        if (pk == '.') {
            return j >= 0 && subMatch(s, j - 1, p, k - 1);
        } else if (pk == '*') {
            // 不匹配
            boolean ans = subMatch(s, j, p, k - 2);
            if (ans) {
                return true;
            }
            // 匹配
            if ((j >= 0) && (p.charAt(k - 1) == '.' || p.charAt(k - 1) == s.charAt(j))) {
                return subMatch(s, j - 1, p, k) || subMatch(s, j - 1, p, k - 2);
            } else {
                return false;
            }
        } else {
            if (j >= 0 && p.charAt(k) == s.charAt(j)) {
                return subMatch(s, j - 1, p, k - 1);
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        String s = "aaa";
        String p = "aaaa";
        System.out.println(new No10().isMatch(s, p));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
