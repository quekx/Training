package contest.lc2023;

import com.qkx.example.model.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC0415 {
    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < m; j++) {
                max = Math.max(max, getLen(grid[j][i]));
            }
            ans[i] = max;
        }
        return ans;
    }

    private int getLen(int x) {
        if (x == 0) {
            return 1;
        }
        int len = x < 0 ? 1 : 0;
        while (x != 0) {
            len++;
            x /= 10;
        }
        return len;
    }

    public long[] findPrefixScore(int[] nums) {
        long[] ans = new long[nums.length];
        int max = Integer.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i] + max;
            ans[i] = sum;
        }
        return ans;
    }

    public TreeNode replaceValueInTree(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        Deque<TreeNode> next = new ArrayDeque<>();
        deque.add(root);
        root.val = 0;
        while (!deque.isEmpty()) {
            int sum = 0;
            for (TreeNode node : deque) {
                if (node.left != null) {
                    sum += node.left.val;
                }
                if (node.right != null) {
                    sum += node.right.val;
                }
            }
            while (!deque.isEmpty()) {
                TreeNode cur = deque.poll();
                if (cur.left != null && cur.right != null) {
                    int curSum = cur.left.val + cur.right.val;
                    cur.left.val = sum - curSum;
                    cur.right.val = sum - curSum;
                    next.add(cur.left);
                    next.add(cur.right);
                } else if (cur.left != null) {
                    cur.left.val = sum - cur.left.val;
                    next.add(cur.left);
                } else if (cur.right != null) {
                    cur.right.val = sum - cur.right.val;
                    next.add(cur.right);
                }
            }
            Deque<TreeNode> tmp = deque;
            deque = next;
            next = tmp;
        }
        return root;
    }

    public static void main(String[] args) {
        int[][] e = {{0,2,5},{0,1,2},{1,2,1},{3,0,3}};
        Graph graph = new Graph(4, e);
    }
}

/**
 * 执行结果：
 * 通过
 * 显示详情
 * 添加备注
 *
 * 执行用时：
 * 1456 ms
 * , 在所有 Java 提交中击败了
 * 100.00%
 * 的用户
 * 内存消耗：
 * 49.9 MB
 * , 在所有 Java 提交中击败了
 * 100.00%
 * 的用户
 * 通过测试用例：
 * 36 / 36
 */
class Graph {

    private long[][] path;
    private int n;

    public Graph(int n, int[][] edges) {
        this.n = n;
        path = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                path[i][j] = Integer.MAX_VALUE;
            }
            path[i][i] = 0;
        }
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], cost = edge[2];
            path[from][to] = Math.min(path[from][to], cost);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }
                    // i -> j
                    // i -> f -> t -> j
                    // add 12  01 -> 01 + 12 + 21
                    if (path[i][from] == Integer.MAX_VALUE || path[to][j] == Integer.MIN_VALUE) {
                        continue;
                    }
                    path[i][j] = Math.min(path[i][j], path[i][from] + path[from][to] + path[to][j]);
                }
            }
        }
    }

    public void addEdge(int[] edge) {
        int from = edge[0], to = edge[1], cost = edge[2];
        path[from][to] = Math.min(path[from][to], cost);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }// i -> j
                // i -> f -> t -> j
                if (path[i][from] == Integer.MAX_VALUE || path[to][j] == Integer.MIN_VALUE) {
                    continue;
                }
                path[i][j] = Math.min(path[i][j], path[i][from] + path[from][to] + path[to][j]);
            }
        }
    }

    public int shortestPath(int node1, int node2) {
        return path[node1][node2] != Integer.MAX_VALUE ? (int) path[node1][node2] : -1;
    }
}