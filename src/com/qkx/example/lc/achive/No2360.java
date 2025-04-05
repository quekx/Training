package com.qkx.example.lc.achive;

//You are given a directed graph of n nodes numbered from 0 to n - 1, where
//each node has at most one outgoing edge.
//
// The graph is represented with a given 0-indexed array edges of size n,
//indicating that there is a directed edge from node i to node edges[i]. If there is no
//outgoing edge from node i, then edges[i] == -1.
//
// Return the length of the longest cycle in the graph. If no cycle exists,
//return -1.
//
// A cycle is a path that starts and ends at the same node.
//
//
// Example 1:
//
//
//Input: edges = [3,3,4,2,3]
//Output: 3
//Explanation: The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
//The length of this cycle is 3, so 3 is returned.
//
//
// Example 2:
//
//
//Input: edges = [2,-1,3,1]
//Output: -1
//Explanation: There are no cycles in this graph.
//
//
//
// Constraints:
//
//
// n == edges.length
// 2 <= n <= 10⁵
// -1 <= edges[i] < n
// edges[i] != i
//
//
// Related Topics Depth-First Search Graph Topological Sort 👍 1336 👎 26


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class No2360 {
    /**
     * 拓扑排序解法
     * 解答成功:
     * 执行耗时:27 ms,击败了84.94% 的Java用户
     * 内存消耗:55.1 MB,击败了92.66% 的Java用户
     */
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            if (edges[i] != -1) {
                degree[edges[i]]++;
            }
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int x = queue.poll(), y = edges[x];
            if (y != -1) {
                degree[y]--;
                if (degree[y] == 0) {
                    queue.add(y);
                }
            }
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                continue;
            }
            int step = 0;
            int x = i;
            while (degree[x] != 0) {
                degree[x]--;
                x = edges[x];
                step++;
            }
            ans = Math.max(ans, step);
        }
        return ans;
    }

    /**
     * 并查集解法
     * 解答成功:
     * 执行耗时:23 ms,击败了91.89% 的Java用户
     * 内存消耗:54 MB,击败了94.98% 的Java用户
     */
    public int longestCycle2(int[] edges) {
        int n = edges.length;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        for (int a = 0; a < n; a++) {
            if (edges[a] == -1) {
                continue;
            }
            // a -> b
            int b = edges[a];
            int pa = find(a, p);
            int pb = find(b, p);
            p[pa] = pb;
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (edges[i] == -1) {
                continue;
            }
            if (find(i, p) != i) {
                continue;
            }
            int step = 0;
            int x = i;
            while (edges[x] != i) {
                x = edges[x];
                step++;
            }
            ans = Math.max(ans, step + 1);
        }
        return ans;
    }

    private int find(int x, int[] p) {
        if (p[x] != x) {
            p[x] = find(p[x], p);
        }
        return p[x];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

