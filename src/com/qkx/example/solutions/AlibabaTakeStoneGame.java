package com.qkx.example.solutions;

/**
 * Created by qkx on 17/4/22.
 *
 * 4.21阿里测试编程题.
 * 问题：有n个石子,两人轮流拿,规则如下:
 * 第一个人最多拿n - 1个, 接下来的人拿的个数不能超过前一次他人拿的个数
 * 拿到最后一个石子的人获胜
 * 求获胜条件下第一个人第一次拿的最大数目
 */
public class AlibabaTakeStoneGame {
    /**
     * dp[i][j] 表示剩余i个石子时拿j个能否获胜
     * dp[i][j] = !dp[i - j][x] && dp[i][j] (x = 1 ~ min(i - j, j))
     */
    public static int solve(int n) {
        if (n <= 2) return 0;

        boolean[][] dp = new boolean[n + 1][n + 1];
        for (int j = 1; j <= n; j++) {
            dp[1][j] = true;
            dp[2][j] = true;
        }
        dp[2][1] = false;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= i) {
                    dp[i][j] = true;
                } else if (j * 2 >= i) {
                    dp[i][j] = false;
                } else {
                    boolean result = true;
                    // min(i - j, j) = j
                    for (int x = 1; x <= j; x++) {
                        result = result && !dp[i - j][x];
                    }
                    dp[i][j] = result;
                }
            }
        }

        // print result
//        for (int i = 1; i <= n; i++) {
//            System.out.print(i + ": ");
//            for (int j = 1; j <= n && i >= j; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int x = n - 1; x >= 1; x--) {
            if (dp[n][x]) return x;
        }

        return 0;
    }
}
