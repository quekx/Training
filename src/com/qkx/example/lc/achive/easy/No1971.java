package com.qkx.example.lc.achive.easy;

//There is a bi-directional graph with n vertices, where each vertex is labeled
//from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D
//integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge
//between vertex ui and vertex vi. Every vertex pair is connected by at most one
//edge, and no vertex has an edge to itself.
//
// You want to determine if there is a valid path that exists from vertex
//source to vertex destination.
//
// Given edges and the integers n, source, and destination, return true if
//there is a valid path from source to destination, or false otherwise.
//
//
// Example 1:
//
//
//Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
//Output: true
//Explanation: There are two paths from vertex 0 to vertex 2:
//- 0 → 1 → 2
//- 0 → 2
//
//
// Example 2:
//
//
//Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0,
//destination = 5
//Output: false
//Explanation: There is no path from vertex 0 to vertex 5.
//
//
//
// Constraints:
//
//
// 1 <= n <= 2 * 10⁵
// 0 <= edges.length <= 2 * 10⁵
// edges[i].length == 2
// 0 <= ui, vi <= n - 1
// ui != vi
// 0 <= source, destination <= n - 1
// There are no duplicate edges.
// There are no self edges.
//
//
// Related Topics Depth-First Search Breadth-First Search Union Find Graph 👍 22
//14 👎 112


//leetcode submit region begin(Prohibit modification and deletion)
class No1971 {
    /**
     * 并查集
     */
    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了98.46% 的Java用户
     * 	内存消耗:138.3 MB,击败了81.37% 的Java用户
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        for (int[] e : edges) {
            int a = find(e[0], p), b = find(e[1], p);
            p[b] = a;
        }
        return find(source, p) == find(destination, p);
    }

    private int find(int x, int[] p) {
        if (x == p[x]) {
            return x;
        }
        int t = find(p[x], p);
        p[x] = t;
        return t;
    }

    public static void main(String[] args) {
        int n = 10;
        int[][] edges = {{4,3},{1,4},{4,8},{1,7},{6,4},{4,2},{7,4},{4,0},{0,9},{5,4}};
        int source = 5, dest = 9;
        System.out.println(new No1971().validPath(n, edges, source, dest));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

