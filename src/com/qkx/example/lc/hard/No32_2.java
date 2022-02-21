package com.qkx.example.lc.hard;

/**
 * @author kaixin
 * @since 2021-01-13 20:35
 */
//Given a string containing just the characters '(' and ')', find the length of
//the longest valid (well-formed) parentheses substring.
//
//
// Example 1:
//
//
//Input: s = "(()"
//Output: 2
//Explanation: The longest valid parentheses substring is "()".
//
//
// Example 2:
//
//
//Input: s = ")()())"
//Output: 4
//Explanation: The longest valid parentheses substring is "()()".
//
//
// Example 3:
//
//
//Input: s = ""
//Output: 0
//
//
//
// Constraints:
//
//
// 0 <= s.length <= 3 * 104
// s[i] is '(', or ')'.
//
// Related Topics String Dynamic Programming
// 👍 4350 👎 163


//leetcode submit region begin(Prohibit modification and deletion)


public class No32_2 {

    public static void main(String[] args) {
        String s = ")()())";
        s = null;
        System.out.println(new No32_2().longestValidParentheses2(s));
    }

    /**
     * dp[i] 代表以 s[i] 为结尾，最长字符的长度
     * 21:15	info
     * 解答成功:
     * 执行耗时:1 ms,击败了100.00% 的Java用户
     * 内存消耗:38.6 MB,击败了95.95% 的Java用户
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                // (.....)
                // s[i - 1 - dp[i - 1]]
                int k = i - 1 - dp[i - 1];
                if (s.charAt(k) == '(') {
                    dp[i] = k >= 1 ? dp[k - 1] + dp[i - 1] + 2 : dp[i - 1] + 2;
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }

    /**
     * dp[i][j] 代表 s[i, j] 是否满足
     * dp[i][j] = (dp[i][x] && dp[x + 1][j]) || dp[i + 1][j - 1] && s[i] == '(' && s[j] == ')'
     */
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int max = 0;
        boolean dp[][] = new boolean[s.length()][s.length()];
        for (int i = 0; i <= s.length() - 2; i++) {
            if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
                dp[i][i + 1] = true;
                max = 2;
            }
        }

        for (int len = 4; len <= s.length(); len += 2) {
            // dp[i][i + delta - 1]
            for (int i = 0; i + len <= s.length(); i++) {
                int j = i + len - 1;
                if (s.charAt(i) == '(' && s.charAt(j) == ')' && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    max = len;
                    continue;
                }

                for (int x = i + 1; x + 1 <= j - 1; x += 2) {
                    if (dp[i][x] && dp[x + 1][j]) {
                        dp[i][j] = true;
                        max = len;
                        break;
                    }
                }
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

