package com.qkx.example.lc.hard;

import com.qkx.example.utils.ArrayUtil;

/**
 * @author kaixin
 * @since 2020-12-03 18:24
 */
//Given a string s, partition s such that every substring of the partition is a
//palindrome.
//
// Return the minimum cuts needed for a palindrome partitioning of s.
//
//
// Example 1:
//
//
//Input: s = "aab"
//Output: 1
//Explanation: The palindrome partitioning ["aa","b"] could be produced using 1
//cut.
//
//
// Example 2:
//
//
//Input: s = "a"
//Output: 0
//
//
// Example 3:
//
//
//Input: s = "ab"
//Output: 1
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 2000
// s consists of lower-case English letters only.
//
// Related Topics Dynamic Programming
// 👍 1495 👎 43


//leetcode submit region begin(Prohibit modification and deletion)
public class No132 {
    public static void main(String[] args) {
        String s;
        s = "aab";
        System.out.println(new No132().minCut(s));
    }

    /**
     * boolean: f[i][j] 表示 s[i, j] 是否回文串
     * dp[k] 表示 s[0, k] 最小次数
     * 1. f[0][k] == true
     * dp[k] = 0
     * 2. f[0][k] == false
     * dp[k] = min(dp[x] + 1) (0 <= x <= k - 1 && f[x+1][k] = true)
     * 解答成功:
     * 执行耗时:12 ms,击败了58.16% 的Java用户
     * 内存消耗:38.6 MB,击败了83.80% 的Java用户
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int len = s.length();
        boolean[][] f = new boolean[len][len];

        for (int i = 0; i <= len - 1; i++) {
            f[i][i] = true;
        }

        for (int i = 0; i <= len - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                f[i][i + 1] = true;
            }
        }

        // delta = j - i
        for (int delta = 2; delta <= len - 1; delta++) {
            for (int i = 0; i + delta <= len - 1; i++) {
                int j = i + delta;
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1];
                }
            }
        }

        int[] dp = new int[len];
        for (int k = 1; k <= len - 1; k++) {
            if (f[0][k]) {
                dp[k] = 0;
                continue;
            }

            int min = Integer.MAX_VALUE;
            for (int x = 0; x <= k - 1; x++) {
                if (f[x + 1][k]) {
                    min = Math.min(min, dp[x] + 1);
                }
            }
            dp[k] = min;
        }
        return dp[len - 1];
    }

    /**
     * dp[i][j] 表示 s[i, j]最小切割次数
     * dp[i][i] = 0;
     * dp[i][i+1] = s[i] == s[i+1] ? 0 : 1;
     * <p>
     * 1. s[i] == s[j] && dp[i+1][j-1] == 0
     * 说明s[i, j]为回文串
     * 2. 其他情况
     * dp[i][j] = min(dp[i][k] + dp[k+1][j])    i <= k <= j - 1
     * 解答成功:
     * 执行耗时:1035 ms,击败了14.02% 的Java用户
     * 内存消耗:48.7 MB,击败了8.10% 的Java用户
     *
     * @param s
     * @return
     */
    public int minCut2(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i <= len - 2; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                dp[i][i + 1] = 1;
            }
        }

        // delta = j - i
        for (int delta = 2; delta <= len - 1; delta++) {
            for (int i = 0; i + delta <= len - 1; i++) {
                int j = i + delta;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    min = Math.min(min, dp[i][k] + dp[k + 1][j] + 1);
                }
                dp[i][j] = min;
            }
        }
        ArrayUtil.print(dp);
        return dp[0][len - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

