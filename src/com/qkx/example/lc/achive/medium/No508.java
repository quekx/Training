package com.qkx.example.lc.achive.medium;

/**
 * @author kaixin
 * @since 2022-02-22 20:35
 */
//Given the root of a binary tree, return the most frequent subtree sum. If
//there is a tie, return all the values with the highest frequency in any order.
//
// The subtree sum of a node is defined as the sum of all the node values
//formed by the subtree rooted at that node (including the node itself).
//
//
// Example 1:
//
//
//Input: root = [5,2,-3]
//Output: [2,-3,4]
//
//
// Example 2:
//
//
//Input: root = [5,2,-5]
//Output: [2]
//
//
//
// Constraints:
//
//
// The number of nodes in the tree is in the range [1, 10⁴].
// -10⁵ <= Node.val <= 10⁵
//
// Related Topics Hash Table Tree Depth-First Search Binary Tree 👍 1377 👎 210


//leetcode submit region begin(Prohibit modification and deletion)

import com.qkx.example.model.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class No508 {

    /**
     * 20:57	info
     * 解答成功:
     * 执行耗时:10 ms,击败了35.36% 的Java用户
     * 内存消耗:46.1 MB,击败了19.08% 的Java用户
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> sumCountMap = new HashMap<>();
        dfs(root, sumCountMap);

        int maxCount = 0;
        for (Integer count : sumCountMap.values()) {
            maxCount = Math.max(maxCount, count);
        }

        List<Integer> list = new LinkedList<>();
        for (Map.Entry<Integer, Integer> kv : sumCountMap.entrySet()) {
            if (kv.getValue() == maxCount) {
                list.add(kv.getKey());
            }
        }
        return list.stream().mapToInt(value -> value).toArray();
    }

    private int dfs(TreeNode node, Map<Integer, Integer> sumCountMap) {
        if (node == null) {
            return 0;
        }
        int leftSum = dfs(node.left, sumCountMap);
        int rightSum = dfs(node.right, sumCountMap);
        int curSum = leftSum + rightSum + node.val;
        sumCountMap.put(curSum, sumCountMap.getOrDefault(curSum, 0) + 1);
        return curSum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

