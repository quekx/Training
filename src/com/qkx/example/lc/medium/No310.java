package com.qkx.example.lc.medium;

import java.util.*;

/**
 * @author kaixin
 * @since 2021-11-05 16:40
 */
//A tree is an undirected graph in which any two vertices are connected by
//exactly one path. In other words, any connected graph without simple cycles is a
//tree.
//
// Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1
//edges where edges[i] = [ai, bi] indicates that there is an undirected edge between
//the two nodes ai and bi in the tree, you can choose any node of the tree as the
//root. When you select a node x as the root, the result tree has height h. Among
//all possible rooted trees, those with minimum height (i.e. min(h)) are called
//minimum height trees (MHTs).
//
// Return a list of all MHTs' root labels. You can return the answer in any
//order.
//
// The height of a rooted tree is the number of edges on the longest downward
//path between the root and a leaf.
//
//
// Example 1:
//
//
//Input: n = 4, edges = [[1,0],[1,2],[1,3]]
//Output: [1]
//Explanation: As shown, the height of the tree is 1 when the root is the node
//with label 1 which is the only MHT.
//
//
// Example 2:
//
//
//Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
//Output: [3,4]
//
//
// Example 3:
//
//
//Input: n = 1, edges = []
//Output: [0]
//
//
// Example 4:
//
//
//Input: n = 2, edges = [[0,1]]
//Output: [0,1]
//
//
//
// Constraints:
//
//
// 1 <= n <= 2 * 10⁴
// edges.length == n - 1
// 0 <= ai, bi < n
// ai != bi
// All the pairs (ai, bi) are distinct.
// The given input is guaranteed to be a tree and there will be no repeated
//edges.
//
// Related Topics Depth-First Search Breadth-First Search Graph Topological
//Sort 👍 3905 👎 157


//leetcode submit region begin(Prohibit modification and deletion)
public class No310 {

    public static void main(String[] args) {
//        int n = 4;
//        int[][] edges = {{1,0},{1,2},{1,3}};
        int n = 3;
        int[][] edges = {{1, 0}, {0, 2}};
        System.out.println(new No310().findMinHeightTrees(n, edges));
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (edges == null || (n - 1) != edges.length) {
            return null;
        }
        if (edges.length == 0) {
            List<Integer> result = new LinkedList<>();
            result.add(0);
            return result;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Set<Integer>> vertex : graph.entrySet()) {
            if (vertex.getValue().size() == 1) {
                queue.offer(vertex.getKey());
            }
        }
        while (graph.size() > 2 && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer leaf = queue.poll();
                Integer parent = graph.get(leaf).iterator().next();
                graph.get(parent).remove(leaf);
                graph.remove(leaf);

                if (graph.get(parent).size() == 1) {
                    queue.offer(parent);
                }
            }
        }
        return new LinkedList<>(graph.keySet());
    }
}
//leetcode submit region end(Prohibit modification and deletion)

