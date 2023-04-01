package contest;

import java.util.*;

/**
 * https://leetcode.cn/contest/biweekly-contest-101/ranking/
 */
public class LC0401 {
    public int minNumber(int[] nums1, int[] nums2) {
        int ans = Integer.MAX_VALUE;
        for (int a : nums1) {
            int min = Integer.MAX_VALUE;
            for (int b : nums2) {
                if (a == b) {
                    min = a;
                } else {
                    min = Math.min(min, Math.min(10 * a + b, 10 * b + a));
                }
            }
            ans = Math.min(ans, min);
        }
        return ans;
    }

    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] valMap = new int[26];
        for (int i = 0; i < 26; i++) {
            valMap[i] = i + 1;
        }
        for (int i = 0; i < chars.length(); i++) {
            valMap[chars.charAt(i) - 'a'] = vals[i];
        }
        int ans = 0;
        int preMax = 0;
        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            preMax = Math.max(preMax + valMap[idx], valMap[idx]);
            ans = Math.max(ans, preMax);
        }
        return ans;
    }

    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        boolean[] visit = new boolean[n];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                continue;
            }
            ArrayList<Integer> tmp = new ArrayList<>();
            int p = i;
            while (!visit[p]) {
                tmp.add(arr[p]);
                visit[p] = true;
                p = (p + k) % n;
            }
            tmp.sort((a, b) -> a - b);
            int x = 0, y = tmp.size() - 1;
            while (x < y) {
                ans += (tmp.get(y) - tmp.get(x));
                x++;
                y--;
            }
        }
        return ans;
    }

    public int findShortestCycle(int n, int[][] edges) {
        ArrayList<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
            degree[a]++;
            degree[b]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int leaf = queue.poll();
            degree[leaf]--;
            for (int p : g[leaf]) {
                degree[p]--;
                if (degree[p] == 1) {
                    queue.add(p);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                continue;
            }
            // BFS
            ans = Math.min(ans, getLevel(i, g, n));
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int getLevel(int i, ArrayList<Integer>[] g, int n) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] level = new int[n];
        queue.add(i);
        level[i] = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int cur = queue.poll();
                for (int next : g[cur]) {
                    if (level[next] == 0) {
                        queue.add(next);
                        level[next] = level[cur] + 1;
                    } else if (level[next] == level[cur]) {
                        return level[cur] * 2 - 1;
                    } else if (level[next] == level[cur] + 1) {
                        return level[cur] * 2;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

}
