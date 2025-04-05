package com.qkx.example.lc.achive.hard;

//There is an undirected connected tree with n nodes labeled from 0 to n - 1
//and n - 1 edges.
//
// You are given the integer n and the array edges where edges[i] = [ai, bi]
//indicates that there is an edge between nodes ai and bi in the tree.
//
// Return an array answer of length n where answer[i] is the sum of the
//distances between the iᵗʰ node in the tree and all other nodes.
//
//
// Example 1:
//
//
//Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
//Output: [8,12,6,10,10,10]
//Explanation: The tree is shown above.
//We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
//equals 1 + 1 + 2 + 2 + 2 = 8.
//Hence, answer[0] = 8, and so on.
//
//
// Example 2:
//
//
//Input: n = 1, edges = []
//Output: [0]
//
//
// Example 3:
//
//
//Input: n = 2, edges = [[1,0]]
//Output: [1,1]
//
//
//
// Constraints:
//
//
// 1 <= n <= 3 * 10⁴
// edges.length == n - 1
// edges[i].length == 2
// 0 <= ai, bi < n
// ai != bi
// The given input represents a valid tree.
//
//
// Related Topics Dynamic Programming Tree Depth-First Search Graph 👍 3883 👎 8
//7


import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class No834 {

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
        System.out.println(Arrays.toString(new No834().sumOfDistancesInTree(n, edges)));
    }

    /**
     * 时间复杂度 o(n)
     *
     * 解答成功:
     * 	执行耗时:44 ms,击败了94.26% 的Java用户
     * 	内存消耗:64.7 MB,击败了85.17% 的Java用户
     */
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        ArrayList<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int[] visit = new int[n];
        int[][] tmp = new int[n][2];
        dfs(0, g, visit, 1, tmp);

        int[] ans = new int[n];
        ans[0] = tmp[0][1];
        visit[0] = 2;
        for (int child : g[0]) {
            cal(child, g, visit, 2, tmp[0][1], tmp, ans, tmp[0][0]);
        }
        return ans;
    }

    /**
     * 需要的值
     * 当父节点向子节点遍历时，相当于把子节点旋转到父节点，原有父节点变为子节点
     * 此时要计算节点变为子节点时的两个参数
     * 1. 当前树节点数 = 子树结点数 + 1
     * 2. 每个子树到当前树总节点的距离 = 每个子树到当前子树根节点的距离和 + 每个子树总结点数
     *
     * 当前节点由父节点变为子节点时，看成将当前子节点旋转成根节点
     *
     * 将分为两部分，当前子节点子树 d1, 父节点和兄弟节点组成的子树 d2
     * d = d1 + c1 + d2
     *
     * 旋转后子节点变为父节点
     * d' = d1 + d2 + c2
     *    = d - c1 + c2
     *    = d - total - 2*c1
     *
     */
    /**
     * 每次往下递归，看成将子节点旋转成根节点
     * 将父节点的状态带下去，作为子节点计算
     */
    private void cal(int i, ArrayList<Integer>[] g, int[] visit, int tag, int pDist, int[][] tmp, int[] ans, int totalCount) {
        if (visit[i] == tag) {
            return;
        }
        visit[i] = tag;
        int curCount = tmp[i][0];
        int newDist = pDist + totalCount - curCount * 2;
        ans[i] = newDist;
        for (int child : g[i]) {
            cal(child, g, visit, tag, newDist, tmp, ans, totalCount);
        }
    }

    /**
     * 计算两个值
     * 1. 当前节点下子树总节点数
     * 2. 当前节点到每个子节点的总距离
     */
    private void dfs(int i, ArrayList<Integer>[] g, int[] visit, int tag, int[][] tmp) {
        if (visit[i] == tag) {
            return;
        }
        visit[i] = tag;
        int count = 1;
        int dist = 0;
        for (int child : g[i]) {
            dfs(child, g, visit, tag, tmp);
            int subCount = tmp[child][0], subDist = tmp[child][1];
            count += subCount;
            dist += subCount + subDist;
        }
        tmp[i][0] = count;
        tmp[i][1] = dist;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
