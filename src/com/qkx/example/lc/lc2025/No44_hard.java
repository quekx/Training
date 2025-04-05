package com.qkx.example.lc.lc2025;

import com.qkx.example.utils.ArrayUtil;

/**
 * 给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符序列（包括空字符序列）。
 * 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
 */
public class No44_hard {
    /**
     * 通过
     * 1811 / 1811 个通过的测试用例
     * QueKaix
     * QueKaix
     * 提交于 2025.04.05 20:59
     *
     * 官方题解
     *
     * 写题解
     * 1024
     * 小红书特训计划
     * 掌握小红书面试高频考点
     * 执行用时分布
     * 18
     * ms
     * 击败
     * 71.28%
     * 复杂度分析
     * 消耗内存分布
     * 43.69
     * MB
     * 击败
     * 90.99%
     *
     * dp[i][j] 表示 s[0,i] 与 p[0, j] 是否匹配
     * dp[i][j] = p[j] == '?' ? dp[i - 1][j - 1] : false
     * dp[i][j] = p[j] == '*' ? dp[i - 1][j] || dp[i - 1][j - 1] : false
     * dp[i][j] = s[i] == p[j] ? dp[i - 1][j - 1] : false
     *
     * 空字符场景, 使用dp[i + 1][j + 1]
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*') {
                dp[0][j + 1] = dp[0][j];
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char sch = s.charAt(i);
            for (int j = 0; j < p.length(); j++) {
                char pch = p.charAt(j);
                switch (pch) {
                    case '?':
                        dp[i + 1][j + 1] = dp[i][j];
                        break;
                    case '*':
                        dp[i + 1][j + 1] = dp[i][j + 1] || dp[i][j] || dp[i + 1][j];
                        break;
                    default:
                        dp[i + 1][j + 1] = sch == pch && dp[i][j];
                }
            }
        }
        ArrayUtil.print(dp);
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        String s = "abcabczzzde";
        String p = "*abc???de*";
        System.out.println(new No44_hard().isMatch(s, p));
    }
}
