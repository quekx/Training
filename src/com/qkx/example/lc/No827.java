package com.qkx.example.lc;

//You are given an n x n binary matrix grid. You are allowed to change at most
//one 0 to be 1.
//
// Return the size of the largest island in grid after applying this operation.
//
//
// An island is a 4-directionally connected group of 1s.
//
//
// Example 1:
//
//
//Input: grid = [[1,0],[0,1]]
//Output: 3
//Explanation: Change one 0 to 1 and connect two 1s, then we get an island with
//area = 3.
//
//
// Example 2:
//
//
//Input: grid = [[1,1],[1,0]]
//Output: 4
//Explanation: Change the 0 to 1 and make the island bigger, only one island
//with area = 4.
//
// Example 3:
//
//
//Input: grid = [[1,1],[1,1]]
//Output: 4
//Explanation: Can't change any 0 to 1, only one island with area = 4.
//
//
//
// Constraints:
//
//
// n == grid.length
// n == grid[i].length
// 1 <= n <= 500
// grid[i][j] is either 0 or 1.
//
//
// Related Topics Array Depth-First Search Breadth-First Search Union Find
//Matrix 👍 2873 👎 58


import com.qkx.example.utils.ArrayUtil;

import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class No827 {

    int[][] ori;
    int[][] size;

    /**
     * DFS
     * 解答成功:
     * 执行耗时:148 ms,击败了80.02% 的Java用户
     * 内存消耗:70.3 MB,击败了99.17% 的Java用户
     */
    public int largestIsland2(int[][] grid) {
        int n = grid.length;
        ori = new int[n][n];
        size = new int[n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (ori[i][j] == 0) {
                    int size = dfs(i, j, i * n + j + 1, grid, n);
                    this.size[i][j] = size;
                    ans = Math.max(ans, size);
                } else {
                    int originX = (ori[i][j] - 1) / n, originY = (ori[i][j] - 1) % n;
                    size[i][j] = size[originX][originY];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }
                Set<Integer> block = new HashSet<>();
                int size = 1;
                for (int[] d : dxy) {
                    int x = i + d[0], y = j + d[1];
                    if (x >= 0 && x < n && y >= 0 && y < n && !block.contains(ori[x][y])) {
                        block.add(ori[x][y]);
                        size += this.size[x][y];
                    }
                }
                ans = Math.max(ans, size);
            }
        }
        return ans;
    }

    private int dfs(int curX, int curY, int origin, int[][] grid, int n) {
        if (ori[curX][curY] != 0) {
            return 0;
        }

        if (grid[curX][curY] == 0) {
            return 0;
        }

        ori[curX][curY] = origin;
        int size = 1;
        for (int[] d : dxy) {
            int nextX = curX + d[0], nextY = curY + d[1];
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                size += dfs(nextX, nextY, origin, grid, n);
            }
        }
        return size;
    }

    int[][] dxy = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int[] p;

    int[] s;

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    int union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return s[pa];
        }
        p[pb] = pa;
        s[pa] += s[pb];
        return s[pa];
    }

    /**
     * 并查集
     * 解答成功:
     * 	执行耗时:238 ms,击败了50.54% 的Java用户
     * 	内存消耗:81.2 MB,击败了81.93% 的Java用户
     */
    public int largestIsland(int[][] grid) {
        int n = grid.length, len = n * n;
        p = new int[len];
        s = new int[len];
        for (int i = 0; i < len; i++) {
            p[i] = i;
            s[i] = grid[i / n][i % n] == 1 ? 1 : 0;
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int x = i / n, y = i % n;
            if (grid[x][y] == 0) {
                continue;
            }
            ans = Math.max(ans, s[i]);
            for (int[] d : dxy) {
                int nextX = x + d[0], nextY = y + d[1];
                int nextIndex = nextX * n + nextY;
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && grid[nextX][nextY] == 1) {
                    ans = Math.max(ans, union(i, nextIndex));
                }
            }
        }
        for (int i = 0; i < len; i++) {
            int x = i / n, y = i % n;
            if (grid[x][y] == 1) {
                continue;
            }
            Set<Integer> block = new HashSet<>();
            int size = 1;
            for (int[] d : dxy) {
                int nextX = x + d[0], nextY = y + d[1];
                int nextIndex = nextX * n + nextY;
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !block.contains(find(nextIndex))) {
                    size += s[find(nextIndex)];
                    block.add(find(nextIndex));
                }
            }
            ans = Math.max(ans, size);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,1,0,1},{0,1,1,0,1},{1,1,1,0,0},{1,0,1,1,1},{0,0,1,1,0}};
        System.out.println(new No827().largestIsland(grid));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
