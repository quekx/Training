package exercise;

import java.util.HashMap;
import java.util.Map;

public class Exercise1202 {
    /**
     * https://www.bilibili.com/video/BV1j14y1n7eT/?spm_id_from=333.999.0.0&vd_source=377067b9ddfef24cbeb6f8862e6c1288
     */
    /**
     * dp[i][j] 代表 第 i 轮，最后位置在 j 的方案中收益最大的值
     * dp[i][j]
     * 1. if ci != j, 不完成任务: dp[i - 1][j]
     * 2. if ci == j
     *  2.1 不完成任务: dp[i - 1][j] 可以排除，做任务会更多收益
     *  2.2 完成任务 max(dp[i - 1][k] + ai (if k == ci), dp[i - 1][k] + bi (if k != ci))
     */
    public int getMaxProfit(int n, int k, int[] c, int[] a, int[] b) {
        int ans = 0, m = c.length;
        int[][] dp = new int[n][m];
        dp[0][k] = k == c[0] ? a[0] : b[0];
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (c[i] == j) {
                    for (int x = 0; x < n; x++) {
                        dp[i][j] = Math.max(dp[i][j], x == c[i] ? dp[i - 1][x] + a[i] : dp[i - 1][x] + b[i]);
                    }
                }
            }
        }
        return ans;
    }

    public int getMaxProfit2(int n, int k, int[] c, int[] a, int[] b) {
        return zuo(n, k, 0, c, a, b);
    }

    private int zuo(int n, int curCity, int i, int[] c, int[] a, int[] b) {
        if (i == c.length) {
            return 0;
        }
        // 不做任务+做任务
        int p1 = zuo(n, curCity, i + 1, c, a, b);
        int p2 = curCity == c[i] ? a[i] : b[i];
        p2 += zuo(n, c[i], i + 1, c, a, b);
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
    }

}
