package contest.lc2024;

// 1615 ~ 1702
public class LC0514 {
    public int[] circularGameLosers(int n, int k) {
        boolean[] visit = new boolean[n];
        int count = 0;
        int p = 0, r = 1;
        while (!visit[p]) {
            visit[p] = true;
            p = (p + r * k) % n;
            r++;
            count++;
        }
        int[] ans = new int[n - count];
        p = 0;
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                ans[p++] = i + 1;
            }
        }
        return ans;
    }

    public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        int[] ori = new int[n];
        // ori[0] = 0
        for (int i = 1; i < n; i++) {
            ori[i] = derived[i - 1] ^ ori[i - 1];
        }
        if ((derived[n - 1] ^ ori[n - 1]) == 0) {
            return true;
        }
        // ori[0] = 1
        ori[0] = 1;
        for (int i = 1; i < n; i++) {
            ori[i] = derived[i - 1] ^ ori[i - 1];
        }
        if ((derived[n - 1] ^ ori[n - 1]) == 1) {
            return true;
        }
        return false;
    }

    public int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int r = 0; r < m; r++) {
            dp[r][0] = 1;
        }
        int max = 1;
        for (int c = 1; c < n; c++) {
            for (int r = 0; r < m; r++) {
                if (r - 1 >= 0 && grid[r - 1][c - 1] < grid[r][c] && dp[r - 1][c - 1] > 0) {
                    dp[r][c] = Math.max(dp[r][c], dp[r - 1][c - 1] + 1);
                }
                if (grid[r][c - 1] < grid[r][c]  && dp[r][c - 1] > 0) {
                    dp[r][c] = Math.max(dp[r][c], dp[r][c - 1] + 1);
                }
                if (r + 1 < m && grid[r + 1][c - 1] < grid[r][c]  && dp[r + 1][c - 1] > 0) {
                    dp[r][c] = Math.max(dp[r][c], dp[r + 1][c - 1] + 1);
                }
                max = Math.max(max, dp[r][c]);
            }
        }
        return max - 1;
    }

    private int find(int x, int[] p) {
        if (p[x] != x) {
            p[x] = find(p[x], p);
        }
        return p[x];
    }

    public int countCompleteComponents(int n, int[][] edges) {
        int[] p = new int[n];
        int[] counter = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            counter[i] = 1;
        }
        int[] edgeCounter = new int[n];
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            int pa = find(a, p), pb = find(b, p);
            if (pa == pb) {
                edgeCounter[pb]++;
            } else {
                p[pa] = pb;
                counter[pb] += counter[pa];
                edgeCounter[pb] += edgeCounter[pa] + 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (find(i, p) != i) {
                continue;
            }
            int v = counter[i], e = edgeCounter[i];
            if (e == (v - 1) * v / 2) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] grid = {{1000000,92910,1021,1022,1023,1024,1025,1026,1027,1028,1029,1030,1031,1032,1033,1034,1035,1036,1037,1038,1039,1040,1041,1042,1043,1044,1045,1046,1047,1048,1049,1050,1051,1052,1053,1054,1055,1056,1057,1058,1059,1060,1061,1062,1063,1064,1065,1066,1067,1068},{1069,1070,1071,1072,1073,1074,1075,1076,1077,1078,1079,1080,1081,1082,1083,1084,1085,1086,1087,1088,1089,1090,1091,1092,1093,1094,1095,1096,1097,1098,1099,1100,1101,1102,1103,1104,1105,1106,1107,1108,1109,1110,1111,1112,1113,1114,1115,1116,1117,1118}};
//        System.out.println(new LC0514().maxMoves(grid));

        int n = 6;
        int[][] e = {{0,1},{0,2},{1,2},{3,4}};
        System.out.println(new LC0514().countCompleteComponents(n, e));
    }
}
