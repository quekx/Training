package contest.lc2024;

import java.util.Arrays;

public class LC1221 {
    public int countPathsWithXorValue(int[][] grid, int k) {
        int mod = 1000_000_000 + 7;
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m][n][16];
        dp[0][0][grid[0][0]] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // [i, j]
                int cur = grid[i][j];
                if (i - 1 >= 0) {
                    for (int x = 0; x < 16; x++) {
                        int res = x ^ cur;
                        dp[i][j][res] += dp[i - 1][j][x];
                        dp[i][j][res] %= mod;
                    }
                }
                if (j - 1 >= 0) {
                    for (int x = 0; x < 16; x++) {
                        int res = x ^ cur;
                        dp[i][j][res] += dp[i][j - 1][x];
                        dp[i][j][res] %= mod;
                    }
                }
            }
        }
        return dp[m - 1][n - 1][k] % mod;
    }

    public boolean checkValidCuts(int n, int[][] rectangles) {
        int len = rectangles.length;
        int[][] intervalH = new int[len][];
        int[][] intervalV = new int[len][];
        for (int i = 0; i < len; i++) {
            int lx = rectangles[i][0];
            int ly = rectangles[i][1];
            int rx = rectangles[i][2];
            int ry = rectangles[i][3];
            intervalH[i][0] = lx;
            intervalH[i][1] = rx;
            intervalV[i][0] = ly;
            intervalV[i][1] = ry;
        }

        Arrays.sort(intervalH, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        Arrays.sort(intervalV, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        return false;
    }

    public static void main(String[] args) {
        int[][] g = {{2, 1, 5}, {7, 10, 0}, {12, 6, 4}};
        int k = 11;
        new LC1221().countPathsWithXorValue(g, k);
    }
}
