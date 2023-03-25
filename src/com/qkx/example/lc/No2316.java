package com.qkx.example.lc;

//You are given an integer n. There is an undirected graph with n nodes,
//numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai,
//bi] denotes that there exists an undirected edge connecting nodes ai and bi.
//
// Return the number of pairs of different nodes that are unreachable from each
//other.
//
//
// Example 1:
//
//
//Input: n = 3, edges = [[0,1],[0,2],[1,2]]
//Output: 0
//Explanation: There are no pairs of nodes that are unreachable from each other.
// Therefore, we return 0.
//
//
// Example 2:
//
//
//Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
//Output: 14
//Explanation: There are 14 pairs of nodes that are unreachable from each other:
//
//[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6]
//,[5,6]].
//Therefore, we return 14.
//
//
//
// Constraints:
//
//
// 1 <= n <= 10⁵
// 0 <= edges.length <= 2 * 10⁵
// edges[i].length == 2
// 0 <= ai, bi < n
// ai != bi
// There are no repeated edges.
//
//
// Related Topics Depth-First Search Breadth-First Search Union Find Graph 👍 67
//6 👎 18


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class No2316 {
    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了80.46% 的Java用户
     * 	内存消耗:99.8 MB,击败了86.97% 的Java用户
     */
    public long countPairs(int n, int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            map.put(i, 1);
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            int pa = find(a, p);
            int pb = find(b, p);
            p[pb] = pa;
            if (pa == pb) {
                continue;
            }
            map.put(pa, map.get(pa) + map.get(pb));
        }
        long preNum = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (find(i, p) != i) {
                continue;
            }
            int curNum = map.get(i);
            ans += preNum * curNum;
            preNum += curNum;
        }
        return ans;
    }

    private int find(int x, int[] p) {
        if (p[x] != x) {
            p[x] = find(p[x], p);
        }
        return p[x];
    }

    public static void main(String[] args) {
        int[][] e = {{2,6},{11,3},{5,4},{9,6}};
        System.out.println(new No2316().countPairs(12, e));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

