package com.qkx.example.lc.achive.medium;

/**
 * @author kaixin
 * @since 2021-10-28 15:21
 */
//Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of
//s1 and s2.
//
// An interleaving of two strings s and t is a configuration where they are
//divided into non-empty substrings such that:
//
//
// s = s1 + s2 + ... + sn
// t = t1 + t2 + ... + tm
// |n - m| <= 1
// The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 +
//t3 + s3 + ...
//
//
// Note: a + b is the concatenation of strings a and b.
//
//
// Example 1:
//
//
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//Output: true
//
//
// Example 2:
//
//
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//Output: false
//
//
// Example 3:
//
//
//Input: s1 = "", s2 = "", s3 = ""
//Output: true
//
//
//
// Constraints:
//
//
// 0 <= s1.length, s2.length <= 100
// 0 <= s3.length <= 200
// s1, s2, and s3 consist of lowercase English letters.
//
//
//
// Follow up: Could you solve it using only O(s2.length) additional memory
//space?
// Related Topics String Dynamic Programming 👍 3214 👎 171


//leetcode submit region begin(Prohibit modification and deletion)
public class No97 {

    public static void main(String[] args) {
        //Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
        //Output: true
        //Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
        //Output: false
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(new No97().isInterleave(s1, s2, s3));
    }

    /**
     *
     * dp[i][j] (1<=i<=length1,1<=j<=length2) 表示 s1[0,i-1] s2[0,j-1] 是否满足 s3[0,i+j-1]
     * 1. dp[i][j] = dp[i][j-1] && s2(j-1)==s3[i+j-1]
     * 2. dp[i][j] = dp[i-1][j] && s1(i-1)==s3[i+j-1]
     *
     * dp[i][j] = (dp[i][j-1] && s2(j-1)==s3[i+j-1]) || (dp[i-1][j] && s1(i-1)==s3[i+j-1])
     *
     * 解答成功: 执行耗时:3 ms,击败了75.06% 的Java用户 内存消耗:37.6 MB,击败了67.24% 的Java用户
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        if (s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2.length() == 0) {
            return s1.equals(s3);
        }

        int length1 = s1.length();
        int length2 = s2.length();
        boolean[][] dp = new boolean[length1 + 1][length2 + 1];
        dp[1][0] = s1.charAt(0) == s3.charAt(0);
        dp[0][1] = s2.charAt(0) == s3.charAt(0);
        for (int i = 2; i <= length1; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 2; j <= length2; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                        || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[length1][length2];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return isOk(s1, 0, s2, 0, s3, 0);
    }

    private boolean isOk(String s1, int i, String s2, int j, String s3, int k) {
        if (s1.length() == i && s2.length() == j) {
            return true;
        }
        if (s1.length() == i) {
            if (s2.charAt(j) == s3.charAt(k)) {
                return isOk(s1, i, s2, j + 1, s3, k + 1);
            } else {
                return false;
            }
        } else if (s2.length() == j) {
            if (s1.charAt(i) == s3.charAt(k)) {
                return isOk(s1, i + 1, s2, j, s3, k + 1);
            } else {
                return false;
            }
        } else {
            if (s1.charAt(i) == s3.charAt(k) && isOk(s1, i + 1, s2, j, s3, k + 1)) {
                return true;
            } else if (s2.charAt(j) == s3.charAt(k) && isOk(s1, i, s2, j + 1, s3, k + 1)) {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

