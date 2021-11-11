package com.qkx.example.solutions.LeetCode.medium;

import java.util.*;

/**
 * @author kaixin
 * @since 2021-11-10 14:23
 */
//You are given an array of variable pairs equations and an array of real
//numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai
/// Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
//
// You are also given some queries, where queries[j] = [Cj, Dj] represents the
//jᵗʰ query where you must find the answer for Cj / Dj = ?.
//
// Return the answers to all queries. If a single answer cannot be determined,
//return -1.0.
//
// Note: The input is always valid. You may assume that evaluating the queries
//will not result in division by zero and that there is no contradiction.
//
//
// Example 1:
//
//
//Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a",
//"c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
//Explanation:
//Given: a / b = 2.0, b / c = 3.0
//queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
//
//
// Example 2:
//
//
//Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
//queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//Output: [3.75000,0.40000,5.00000,0.20000]
//
//
// Example 3:
//
//
//Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"]
//,["a","c"],["x","y"]]
//Output: [0.50000,2.00000,-1.00000,-1.00000]
//
//
//
// Constraints:
//
//
// 1 <= equations.length <= 20
// equations[i].length == 2
// 1 <= Ai.length, Bi.length <= 5
// values.length == equations.length
// 0.0 < values[i] <= 20.0
// 1 <= queries.length <= 20
// queries[i].length == 2
// 1 <= Cj.length, Dj.length <= 5
// Ai, Bi, Cj, Dj consist of lower case English letters and digits.
//
// Related Topics Array Depth-First Search Breadth-First Search Union Find
//Graph Shortest Path 👍 4233 👎 348


//leetcode submit region begin(Prohibit modification and deletion)
public class No399 {

    /**
     * 解答成功:
     * 执行耗时:2 ms,击败了28.62% 的Java用户
     * 内存消耗:38.3 MB,击败了39.17% 的Java用户
     * @param args
     */
    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("x1","x2"),
                Arrays.asList("x2","x3"),
                Arrays.asList("x1","x4"),
                Arrays.asList("x2","x5")
        );
        double[] values = {3.0,0.5,3.4,5.6};
        //queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"),
                Arrays.asList("cd", "bc")
        );

        double[] res = new No399().calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(res));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Set<String> nodes = new HashSet<>();
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> edge = equations.get(i);
            String v1 = edge.get(0);
            String v2 = edge.get(1);
            Map<String, Double> route1 = graph.computeIfAbsent(v1, s -> new HashMap<>());
            route1.put(v2, values[i]);

            Map<String, Double> route2 = graph.computeIfAbsent(v2, s -> new HashMap<>());
            route2.put(v1, 1 / values[i]);

            nodes.add(v1);
            nodes.add(v2);
        }

        Set<String> traveled = new HashSet<>();
        for (String node : nodes) {
            dfs(null, node, new HashSet<>(), traveled, graph);
        }
        System.out.println(graph);

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> edge = queries.get(i);
            String v1 = edge.get(0);
            String v2 = edge.get(1);
            if (!graph.containsKey(v1)) {
                result[i] = -1D;
            } else {
                if (v1.equals(v2)) {
                    result[i] = 1D;
                } else {
                    result[i] = graph.get(v1).getOrDefault(v2, -1D);
                }
            }
        }
        return result;
    }

    /**
     * 遍历当前顶点 j
     * 把当前顶点加到当前集合内
     * 此时要算当前顶点和集合内其他顶点之间的有向边的值
     * edge[a,i] = a / i = (a / pre) / (i / pre) = edge[a,pre] / edge[i,pre]
     */
    private void dfs(String preVertex, String curVertex, Set<String> curSet, Set<String> traveled, Map<String, Map<String, Double>> graph) {
        if (traveled.contains(curVertex)) {
            return;
        }
        traveled.add(curVertex);

        if (preVertex != null) {
            // 计算新加入的当前顶点与集合内顶点之间有向边的值
            for (String otherVertex : curSet) {
                if (preVertex.equals(otherVertex)) {
                    continue;
                }
                double edgeValue = graph.get(otherVertex).get(preVertex) / graph.get(curVertex).get(preVertex);
                graph.computeIfAbsent(otherVertex, s -> new HashMap<>()).put(curVertex, edgeValue);
                graph.computeIfAbsent(curVertex, s -> new HashMap<>()).put(otherVertex, 1 / edgeValue);
            }
        }
        curSet.add(curVertex);
        HashSet<String> nextNode = new HashSet<>(graph.get(curVertex).keySet());
        for (String next : nextNode) {
            dfs(curVertex, next, curSet, traveled, graph);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

