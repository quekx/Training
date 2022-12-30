package com.qkx.example.lc;

//Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find
//all possible paths from node 0 to node n - 1 and return them in any order.
//
// The graph is given as follows: graph[i] is a list of all nodes you can visit
//from node i (i.e., there is a directed edge from node i to node graph[i][j]).
//
//
// Example 1:
//
//
//Input: graph = [[1,2],[3],[3],[]]
//Output: [[0,1,3],[0,2,3]]
//Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
//
//
// Example 2:
//
//
//Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
//Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
//
//
//
// Constraints:
//
//
// n == graph.length
// 2 <= n <= 15
// 0 <= graph[i][j] < n
// graph[i][j] != i (i.e., there will be no self-loops).
// All the elements of graph[i] are unique.
// The input graph is guaranteed to be a DAG.
//
//
// Related Topics Backtracking Depth-First Search Breadth-First Search Graph 👍
//5465 👎 127


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class No797 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了87.35% 的Java用户
     * 	内存消耗:44.2 MB,击败了84.21% 的Java用户
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> tmp = new ArrayList<>();
        List<List<Integer>> ans = new LinkedList<>();
        visit(graph, 0, graph.length - 1, tmp, ans);
        return ans;
    }

    private void visit(int[][] graph, int cur, int target, List<Integer> tmp, List<List<Integer>> ans) {
        if (cur == target) {
            tmp.add(cur);
            ans.add(tmp);
            return;
        }

        tmp.add(cur);
        for (int next : graph[cur]) {
            visit(graph, next, target, new ArrayList<>(tmp), ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
