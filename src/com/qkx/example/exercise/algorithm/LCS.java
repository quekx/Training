package com.qkx.example.exercise.algorithm;

/**
 * Created by qkx on 17/5/7.
 */
public class LCS {
    private static int longestCommonString(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) return 0;

        int max = 0;
        int[][] dp = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i > 0 && j > 0) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    private static int longestCommonSequence(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) return 0;

        int[][] dp = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i > 0 && j > 0) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    if (i > 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                    }
                    if (j > 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                    }
                }
            }
        }
        return dp[s1.length() - 1][s2.length() - 1];
    }
}
