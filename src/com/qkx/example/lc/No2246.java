package com.qkx.example.lc;

//You are given a tree (i.e. a connected, undirected graph that has no cycles)
//rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is
//represented by a 0-indexed array parent of size n, where parent[i] is the parent of
//node i. Since node 0 is the root, parent[0] == -1.
//
// You are also given a string s of length n, where s[i] is the character
//assigned to node i.
//
// Return the length of the longest path in the tree such that no pair of
//adjacent nodes on the path have the same character assigned to them.
//
//
// Example 1:
//
//
//Input: parent = [-1,0,0,1,1,2], s = "abacbe"
//Output: 3
//Explanation: The longest path where each two adjacent nodes have different
//characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3
// is returned.
//It can be proven that there is no longer path that satisfies the conditions.
//
//
// Example 2:
//
//
//Input: parent = [-1,0,0,0], s = "aabc"
//Output: 3
//Explanation: The longest path where each two adjacent nodes have different
//characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is
//returned.
//
//
//
// Constraints:
//
//
// n == parent.length == s.length
// 1 <= n <= 10⁵
// 0 <= parent[i] <= n - 1 for all i >= 1
// parent[0] == -1
// parent represents a valid tree.
// s consists of only lowercase English letters.
//
//
// Related Topics Array String Tree Depth-First Search Graph Topological Sort 👍
// 2162 👎 58


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 解答成功:
 * 	执行耗时:96 ms,击败了94.57% 的Java用户
 * 	内存消耗:92.3 MB,击败了86.96% 的Java用
 */
class No2246 {

    private int maxLength = 0;

    public int longestPath(int[] parent, String s) {
        int n = s.length();
        // 子节点
        ArrayList<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }
        dfs(0, g, 'X', s);
        return maxLength;
    }

    private int dfs(int i, ArrayList<Integer>[] g, char pCh, String s) {
        char ch = s.charAt(i);
        // 获取第一第二长的
        int first = 0, second = 0;
        for (int child : g[i]) {
            int cl = dfs(child, g, ch, s);
            if (cl >= first) {
                second = first;
                first = cl;
            } else if (cl >= second) {
                second = cl;
            }
        }
        maxLength = Math.max(maxLength, first + second + 1);
        return ch != pCh ? first + 1 : 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
