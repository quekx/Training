package com.qkx.example.lc.achive.medium;

//We want to split a group of n people (labeled from 1 to n) into two groups of
//any size. Each person may dislike some other people, and they should not go
//into the same group.
//
// Given the integer n and the array dislikes where dislikes[i] = [ai, bi]
//indicates that the person labeled ai does not like the person labeled bi, return
//true if it is possible to split everyone into two groups in this way.
//
//
// Example 1:
//
//
//Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
//Output: true
//Explanation: group1 [1,4] and group2 [2,3].
//
//
// Example 2:
//
//
//Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
//Output: false
//
//
// Example 3:
//
//
//Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
//Output: false
//
//
//
// Constraints:
//
//
// 1 <= n <= 2000
// 0 <= dislikes.length <= 10⁴
// dislikes[i].length == 2
// 1 <= dislikes[i][j] <= n
// ai < bi
// All the pairs of dislikes are unique.
//
//
// Related Topics Depth-First Search Breadth-First Search Union Find Graph 👍 33
//72 👎 76


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class No886 {

    /**
     * 解答成功:
     * 	执行耗时:35 ms,击败了68.68% 的Java用户
     * 	内存消耗:73.3 MB,击败了45.18% 的Java用户
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] d : dislikes) {
            int a = d[0], b = d[1];
            g[a].add(b);
            g[b].add(a);
        }
        int[] color = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0 && !dfs(i, color, 1, g)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int i, int[] color, int curColor, ArrayList<Integer>[] g) {
        if (color[i] != 0) {
            return color[i] == curColor;
        }
        color[i] = curColor;
        for (int next : g[i]) {
            if (!dfs(next, color, -curColor, g)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] dis = {{1, 2}, {2, 3}, {1, 3}};
        System.out.println(new No886().possibleBipartition(n, dis));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
