package com.qkx.example.lc.achive.hard;

//An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [
//ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note
//that there may be multiple edges between two nodes.
//
// Given an array queries, where queries[j] = [pj, qj, limitj], your task is to
//determine for each queries[j] whether there is a path between pj and qj such
//that each edge on the path has a distance strictly less than limitj .
//
// Return a boolean array answer, where answer.length == queries.length and the
//jᵗʰ value of answer is true if there is a path for queries[j] is true, and
//false otherwise.
//
//
// Example 1:
//
//
//Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2
//],[0,2,5]]
//Output: [false,true]
//Explanation: The above figure shows the given graph. Note that there are two
//overlapping edges between 0 and 1 with distances 2 and 16.
//For the first query, between 0 and 1 there is no path where each distance is
//less than 2, thus we return false for this query.
//For the second query, there is a path (0 -> 1 -> 2) of two edges with
//distances less than 5, thus we return true for this query.
//
//
// Example 2:
//
//
//Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,
//14],[1,4,13]]
//Output: [true,false]
//Exaplanation: The above figure shows the given graph.
//
//
//
// Constraints:
//
//
// 2 <= n <= 10⁵
// 1 <= edgeList.length, queries.length <= 10⁵
// edgeList[i].length == 3
// queries[j].length == 3
// 0 <= ui, vi, pj, qj <= n - 1
// ui != vi
// pj != qj
// 1 <= disi, limitj <= 10⁹
// There may be multiple edges between two nodes.
//
//
// Related Topics Array Union Find Graph Sorting 👍 644 👎 13


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class No1697 {
    /**
     * 对象封装
     * 解答成功:
     * 	执行耗时:87 ms,击败了98.95% 的Java用户
     * 	内存消耗:69.3 MB,击败了92.11% 的Java用户
     *
     * 	使用位置数组
     * 解答成功:
     * 执行耗时:200 ms,击败了42.63% 的Java用户
     * 内存消耗:136.3 MB,击败了37.37% 的Java用户
     */
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        int m = queries.length;
        MyQuery[] queryList = new MyQuery[m];
        for (int i = 0; i < m; i++) {
            queryList[i] = new MyQuery(queries[i][0], queries[i][1], queries[i][2], i);
        }
        Arrays.sort(queryList, (a, b) -> a.limit - b.limit);

        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        boolean[] ans = new boolean[m];
        int k = 0;
        for (int i = 0; i < m; i++) {
            MyQuery query = queryList[i];
            while (k < edgeList.length && edgeList[k][2] < query.limit) {
                int a = edgeList[k][0], b = edgeList[k][1];
                int pa = find(a, p), pb = find(b, p);
                p[pb] = pa;
                k++;
            }
            int x = find(query.a, p), y = find(query.b, p);
            ans[query.idx] = x == y;
        }
        return ans;
    }

    private int find(int x, int[] p) {
        if (p[x] != x) {
            p[x] = find(p[x], p);
        }
        return p[x];
    }

    private class MyQuery {
        int a;
        int b;
        int limit;
        int idx;
        public MyQuery(int a, int b, int limit, int idx) {
            this.a = a;
            this.b = b;
            this.limit = limit;
            this.idx = idx;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
