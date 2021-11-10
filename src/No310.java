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
        int n = 6;
//        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        n = 1;
        int[][] edges = {};
        System.out.println(new No310().findMinHeightTrees2(n, edges));
    }

    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        if (edges == null || (n - 1) != edges.length) {
            return null;
        }
        if (edges.length == 0) {
            List<Integer> result = new LinkedList<>();
            result.add(0);
            return result;
        }

        Set<Integer> leafNode = new HashSet<>();
        Set<Integer> traveledNode = new HashSet<>();
        int[][] distance = new int[n][n];
        boolean[] isTravel = new boolean[edges.length];

        int n1 = edges[0][0];
        int n2 = edges[0][1];
        leafNode.add(n1);
        leafNode.add(n2);
        traveledNode.add(n1);
        traveledNode.add(n2);
        distance[n1][n2] = 1;
        distance[n2][n1] = 1;
        isTravel[0] = true;

        travel(distance, leafNode, traveledNode, edges, isTravel);

        int minHeight = Integer.MAX_VALUE;
        Map<Integer, List<Integer>> heightMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int height = Integer.MIN_VALUE;
            for (Integer leaf : leafNode) {
                height = Math.max(height, distance[i][leaf]);
            }
            List<Integer> list = heightMap.computeIfAbsent(height, k -> new LinkedList<>());
            list.add(i);
            minHeight = Math.min(minHeight, height);
        }
        return heightMap.get(minHeight);
    }

    private void travel(int[][] distance, Set<Integer> leafNode, Set<Integer> traveledNode, int[][] edges, boolean[] isTravel) {
        for (int i = 1; i < edges.length; i++) {
            if (isTravel[i]) {
                continue;
            }
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            if (traveledNode.contains(n1)) {
                isTravel[i] = true;
                distance[n1][n2] = 1;
                distance[n2][n1] = 1;
                leafNode.remove(n1);
                leafNode.add(n2);
                traveledNode.add(n2);
                for (int k = 0; k < distance.length; k++) {
                    if (k != n1 && distance[k][n1] != 0) {
                        distance[k][n2] = distance[k][n1] + 1;
                        distance[n2][k] = distance[k][n1] + 1;
                    }
                }
                travel(distance, leafNode, traveledNode, edges, isTravel);
            } else if (traveledNode.contains(n2)) {
                isTravel[i] = true;
                distance[n1][n2] = 1;
                distance[n2][n1] = 1;
                leafNode.remove(n2);
                leafNode.add(n1);
                traveledNode.add(n1);
                for (int k = 0; k < distance.length; k++) {
                    if (k != n2 && distance[k][n2] != 0) {
                        distance[k][n1] = distance[k][n2] + 1;
                        distance[n1][k] = distance[k][n2] + 1;
                    }
                }
                travel(distance, leafNode, traveledNode, edges, isTravel);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

