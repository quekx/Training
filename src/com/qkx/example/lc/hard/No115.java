package com.qkx.example.lc.hard;

/**
 * @author kaixin
 * @since 2020-12-03 11:47
 */
//Given two strings s and t, return the number of distinct subsequences of s whi
//ch equals t.
//
// A string's subsequence is a new string formed from the original string by del
//eting some (can be none) of the characters without disturbing the relative posit
//ions of the remaining characters. (i.e., "ACE" is a subsequence of "ABCDE" while
// "AEC" is not).
//
// It's guaranteed the answer fits on a 32-bit signed integer.
//
//
// Example 1:
//
//
//Input: s = "rabbbit", t = "rabbit"
//Output: 3
//Explanation:
//As shown below, there are 3 ways you can generate "rabbit" from S.
//rabbbit
//rabbbit
//rabbbit
//
//
// Example 2:
//
//
//Input: s = "babgbag", t = "bag"
//Output: 5
//Explanation:
//As shown below, there are 5 ways you can generate "bag" from S.
//babgbag
//babgbag
//babgbag
//babgbag
//babgbag
//
//
// Constraints:
//
//
// 0 <= s.length, t.length <= 1000
// s and t consist of English letters.
//
// Related Topics String Dynamic Programming
// 👍 1616 👎 60


//leetcode submit region begin(Prohibit modification and deletion)
public class No115 {
    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";
        System.out.println(new No115().numDistinct(s, t));
    }

    /**
     * dp[i][j] 标识s[0,i - 1] 与 t[j - 1] 子序列匹配个数
     * 0 <= i <= length1; 0 <= j <= length2
     * 1. s[i] == t[j]
     * 1) 匹配最后一位 dp[i - 1][j - 1] 个
     * 2) 不匹配最后一位 dp[i - 1][j] 个
     *
     * 2. s[i] != t[j]
     * dp[i - 1][j]
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len1][len2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

