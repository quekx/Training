package com.qkx.example.solutions.LeetCode.medium;

/**
 * @author kaixin
 * @since 2021-09-22 15:05
 */
//Given the root of a binary tree and an integer targetSum, return the number
//of paths where the sum of the values along the path equals targetSum.
//
// The path does not need to start or end at the root or a leaf, but it must go
//downwards (i.e., traveling only from parent nodes to child nodes).
//
//
// Example 1:
//
//
//Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//Output: 3
//Explanation: The paths that sum to 8 are shown.
//
//
// Example 2:
//
//
//Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//Output: 3
//
//
//
// Constraints:
//
//
// The number of nodes in the tree is in the range [0, 1000].
// -10⁹ <= Node.val <= 10⁹
// -1000 <= targetSum <= 1000
//
// Related Topics Tree Depth-First Search Binary Tree 👍 5977 👎 331


//leetcode submit region begin(Prohibit modification and deletion)


import com.qkx.example.model.TreeNode;

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
public class No437 {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        return containPathSum(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    /**
     * 包含当前节点
     * 路径和为targetSum的数目
     */
    int containPathSum(TreeNode curNode, int targetSum) {
        if (curNode == null) {
            return 0;
        }

        if (curNode.val == targetSum) {
            return 1 + containPathSum(curNode.left, 0) + containPathSum(curNode.right, 0);
        } else {
            int remain = targetSum - curNode.val;
            return containPathSum(curNode.left, remain) + containPathSum(curNode.right, remain);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

