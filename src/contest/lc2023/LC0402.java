package contest.lc2023;

import java.util.*;

public class LC0402 {
    public int findTheLongestBalancedSubstring(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        int ans = 0;
        for (int i = 0; i <= n - 2; i++) {
            if (s.charAt(i) == '0' && s.charAt(i + 1) == '1') {
                f[i][i + 1] = true;
                ans = 2;
            }
        }

        for (int d = 3; d < n; d += 2) {
            for (int i = 0; i + d < n; i++) {
                if (s.charAt(i) == '0' && s.charAt(i + d) == '1') {
                    f[i][i + d] = f[i + 1][i + d - 1];
                    if (f[i][i + d]) {
                        ans = Math.max(ans, d + 1);
                    }
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> findMatrix(int[] nums) {
        int n = nums.length;
        int[] counter = new int[n + 1];
        int max = 0;
        for (int num : nums) {
            counter[num]++;
            max = Math.max(max, counter[num]);
        }
        List<List<Integer>> ans = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            ans.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            int p = 0;
            while (counter[i] > 0) {
                ans.get(p).add(i);
                p++;
                counter[i]--;
            }
        }
        return ans;
    }

    /**
     * dp[i][j] 表示 [0, i - 1] 第一只老鼠吃 j 块的最大值 j <= i
     * dp[i][j] = max{dp[i - 1][j] + r2[i - 1], dp[i - 1][j - 1] + r1[i - 1]}
     */
    public int miceAndCheese3(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int[][] dp = new int[n + 1][k + 1];
        // dp[i][0]
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + reward2[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i && j <= k; j++) {
                dp[i][j] = dp[i - 1][j - 1] + reward1[i - 1];
                // [0, i - 1] 吃 j 块 i >= j
                if (i > j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + reward2[i - 1]);
                }
            }
        }
        return dp[n][k];
    }

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = reward1[i] - reward2[i];
        }
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        Arrays.sort(p, (a, b) -> diff[a] - diff[b]);
        int ans = 0;
        for (int i = 0; i < k; i++) {
            ans += reward1[p[n - i - 1]];
        }
        for (int i = 0; i < n - k; i++) {
            ans += reward2[p[i]];
        }
        return ans;
    }

    /**
     * 状态：超出时间限制
     */
    public int miceAndCheese2(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int[] f = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(k, i); j >= 1; j--) {
                if (i > j) {
                    f[j] = Math.max(f[j] + reward2[i - 1], f[j - 1] + reward1[i - 1]);
                } else {
                    f[j] = f[j - 1] + reward1[i - 1];
                }
            }
            f[0] += reward2[i - 1];
        }
        return f[k];
    }

    // p ->
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        boolean[] visit = new boolean[n];
        for (int ban : banned) {
            visit[ban] = true;
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(p);
        ans[p] = 0;
        visit[p] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int i = Math.max(cur - k + 1, 0);
//            while ()
            for (int l = Math.max(cur - k + 1, 0); l <= cur && l + k - 1 < n; l++) {
                // [l, l + k - 1] ==>> p -> l + k - 1 - (p - l)
                int target = l + k - 1 - (cur - l);
                if (!visit[target]) {
                    visit[target] = true;
                    queue.add(target);
                    ans[target] = ans[cur] + 1;
                }
            }
        }
        return ans;
    }

    public int[] minReverseOperations2(int n, int p, int[] banned, int k) {
        boolean[] visit = new boolean[n];
        for (int ban : banned) {
            visit[ban] = true;
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(p);
        ans[p] = 0;
        visit[p] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int l = Math.max(cur - k + 1, 0); l <= cur && l + k - 1 < n; l++) {
                // [l, l + k - 1] ==>> p -> l + k - 1 - (p - l)
                int target = l + k - 1 - (cur - l);
                if (!visit[target]) {
                    visit[target] = true;
                    queue.add(target);
                    ans[target] = ans[cur] + 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] r1 = {1, 1, 3, 4};
        int[] r2 = {4, 4, 1, 1};
        int k = 2;
        System.out.println(new LC0402().miceAndCheese(r1, r2, k));


    }
}
