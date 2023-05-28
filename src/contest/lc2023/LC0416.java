package contest.lc2023;

import java.util.ArrayList;

public class LC0416 {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int max = 0;
        int row = 0;
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int num : mat[i]) {
                if (num == 1) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                row = i;
            }
        }
        return new int[]{row, max};
    }

    public int maxDivScore(int[] nums, int[] divisors) {
        int max = -1;
        int ans = 0;
        for (int divisor : divisors) {
            int count = 0;
            for (int num : nums) {
                if (num % divisor == 0) {
                    count++;
                }
            }
            if (count > max || (count == max && ans > divisor)) {
                max = count;
                ans = divisor;
            }
        }
        return ans;
    }

    public int addMinimum(String word) {
        int i = 0, j = 0, ans = 0;
        while (i < word.length()) {
            if (word.charAt(i) - 'a' == j) {
                i++;
            } else {
                ans++;
            }
            j = (j + 1) % 3;
        }
        // j ~ 2
        return ans + (3 - j) % 3;
    }

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        ArrayList<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        int[] counter = new int[n];
        for (int[] trip : trips) {
            int start = trip[0], end = trip[1];
            boolean[] visit = new boolean[n];
            dfs(g, counter, start, end, visit);
        }
        for (int i = 0; i < n; i++) {
            price[i] *= counter[i];
        }
        boolean[] visit = new boolean[n];
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        return cal(g, price, 0, visit, 1, dp);
    }

    private int cal(ArrayList<Integer>[] g, int[] prices, int i, boolean[] visit, int reduceTag, int[][] dp) {
        if (visit[i]) {
            return 0;
        }
        if (dp[i][reduceTag] != -1) {
            return dp[i][reduceTag];
        }
        visit[i] = true;
        int price = prices[i];
        for (int next : g[i]) {
            price += cal(g, prices, next, visit, 1, dp);
        }

        if (reduceTag == 1) {
            int price2 = prices[i] / 2;
            for (int next : g[i]) {
                price2 += cal(g, prices, next, visit, 0, dp);
            }
            price = Math.min(price, price2);
        }
        visit[i] = false;
        dp[i][reduceTag] = price;
        return price;
    }

    private boolean dfs(ArrayList<Integer>[] g, int[] counter, int i, int end, boolean[] visit) {
        if (visit[i]) {
            return false;
        }
        visit[i] = true;
        counter[i]++;
        if (i == end) {
            return true;
        }
        for (int next : g[i]) {
            if (dfs(g, counter, next, end, visit)) {
                return true;
            }
        }
        counter[i]--;
        return false;
    }

    public static void main(String[] args) {
        int n = 2;
        int[][] edges = {{0, 1}};
        int[] price = {2, 2};
        int[][] trips = {{0, 0}};
        System.out.println(new LC0416().minimumTotalPrice(n, edges, price, trips));
    }
}
