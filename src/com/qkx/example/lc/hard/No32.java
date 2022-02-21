package com.qkx.example.lc.hard;

/**
 * Created by qkx on 16/11/24.
 */
public class No32 {
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;

        int res = 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 当前为'('
                dp[i] = 0;
            } else {
                // 当前为')'
                // pre为与当前位置对应的位置坐标
                int pre = i - 1 - dp[i - 1];
                if (pre < 0) {
                    dp[i] = 0;
                } else if (s.charAt(pre) == ')') {
                    dp[i] = 0;
                } else {
                    dp[i] = pre > 0 ? dp[i - 1] + 2 + dp[pre - 1] : dp[i - 1] + 2;
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
