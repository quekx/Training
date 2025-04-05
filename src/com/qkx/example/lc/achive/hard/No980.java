package com.qkx.example.lc.achive.hard;

//You are given an m x n integer array grid where grid[i][j] could be:
//
//
// 1 representing the starting square. There is exactly one starting square.
// 2 representing the ending square. There is exactly one ending square.
// 0 representing empty squares we can walk over.
// -1 representing obstacles that we cannot walk over.
//
//
// Return the number of 4-directional walks from the starting square to the
//ending square, that walk over every non-obstacle square exactly once.
//
//
// Example 1:
//
//
//Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
//Output: 2
//Explanation: We have the following two paths:
//1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
//2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
//
//
// Example 2:
//
//
//Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
//Output: 4
//Explanation: We have the following four paths:
//1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
//2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
//3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
//4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
//
//
// Example 3:
//
//
//Input: grid = [[0,1],[2,0]]
//Output: 0
//Explanation: There is no path that walks over every empty square exactly once.
//
//Note that the starting and ending square can be anywhere in the grid.
//
//
//
// Constraints:
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 20
// 1 <= m * n <= 20
// -1 <= grid[i][j] <= 2
// There is exactly one starting cell and one ending cell.
//
//
// Related Topics Array Backtracking Bit Manipulation Matrix 👍 3939 👎 156


//leetcode submit region begin(Prohibit modification and deletion)
class No980 {
    int ans = 0;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.6 MB,击败了86.77% 的Java用户
     */
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length, num = m * n;
        int target = 0;
        int sx = 0, sy = 0;
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                if (grid[i][k] == -1) {
                    continue;
                }
                target |= 1 << (i * n + k);
                if (grid[i][k] == 1) {
                    sx = i;
                    sy = k;
                }
            }
        }
        boolean[][] visit = new boolean[m][n];
        visit(sx, sy, grid, visit, 0, target, m, n);
        return ans;
    }

    private void visit(int x, int y, int[][] grid, boolean[][] visit, int tag, int target, int m, int n) {
        if (x < 0 || x >= m || y < 0 || y >= n || visit[x][y]) {
            return;
        }
        if (grid[x][y] == -1) {
            return;
        }
        if (grid[x][y] == 2) {
            if ((tag | 1 << (x * n + y)) == target) {
                ans++;
            }
            return;
        }

        visit[x][y] = true;
        tag |= 1 << (x * n + y);
        visit(x + 1, y, grid, visit, tag, target, m, n);
        visit(x - 1, y, grid, visit, tag, target, m, n);
        visit(x, y + 1, grid, visit, tag, target, m, n);
        visit(x, y - 1, grid, visit, tag, target, m, n);
        visit[x][y] = false;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        System.out.println(new No980().uniquePathsIII(grid));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

