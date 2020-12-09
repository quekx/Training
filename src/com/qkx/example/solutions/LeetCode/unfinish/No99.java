package com.qkx.example.solutions.LeetCode.unfinish;

/**
 * @author kaixin
 * @since 2020-12-02 16:35
 */
//You are given the root of a binary search tree (BST), where exactly two nodes
//of the tree were swapped by mistake. Recover the tree without changing its struc
//ture.
//
// Follow up: A solution using O(n) space is pretty straight forward. Could you
//devise a constant space solution?
//
//
// Example 1:
//
//
//Input: root = [1,3,null,null,2]
//        1
//    3      N
//  N   2
//Output: [3,1,null,null,2]
//Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 mak
//es the BST valid.
//
//
// Example 2:
//
//
//Input: root = [3,1,4,null,null,2]
//       3
//    1     4
//  N  N   2
//Output: [2,1,4,null,null,3]
//Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 a
//nd 3 makes the BST valid.
//
//
//
// Constraints:
//
//
// The number of nodes in the tree is in the range [2, 1000].
// -231 <= Node.val <= 231 - 1
//
// Related Topics Tree Depth-first Search
// 👍 2086 👎 80


//leetcode submit region begin(Prohibit modification and deletion)

import com.qkx.example.model.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class No99 {
    public void recoverTree(TreeNode root) {

    }

    private TraverseResult traverse(TreeNode cur) {
        if (cur.left == null && cur.right == null) {
            return new TraverseResult(cur, cur);
        }

        if (cur.left == null) {
            TraverseResult rightRes = traverse(cur.right);
            TreeNode rightMax = rightRes.maxNode;
            TreeNode rightMin = rightRes.minNode;
            TraverseResult curRes = new TraverseResult(chooseMax(rightMax, cur),
                    chooseMin(rightMin, cur));
            // 和右子树最小值比较
            if (cur.val > rightMin.val) {
                // 有问题
                curRes.errorNode = cur;
            } else {
                // 没问题，向上传递
                curRes.errorNode = rightRes.errorNode;
            }
            return curRes;
        }

        if (cur.right == null) {
            TraverseResult leftRes = traverse(cur.left);
            TreeNode leftMax = leftRes.maxNode;
            TreeNode leftMin = leftRes.minNode;
            TraverseResult curRes = new TraverseResult(chooseMax(leftMax, cur),
                    chooseMin(leftMin, cur));
            if (cur.val < leftMin.val) {
                curRes.errorNode = cur;
            } else {
                curRes.errorNode = leftRes.errorNode;
            }
            return curRes;
        }

        TraverseResult leftRes = traverse(cur.left);
        TraverseResult rightRes = traverse(cur.right);

        TreeNode leftMax = leftRes.maxNode;
        TreeNode leftMin = leftRes.minNode;
        TreeNode rightMax = rightRes.maxNode;
        TreeNode rightMin = rightRes.minNode;
        TraverseResult curRes = new TraverseResult(chooseMax(leftMax, cur),
                chooseMin(leftMin, cur));
        if (cur.val >= leftMax.val && cur.val <= rightMin.val) {
            // 正常

        }

        return new TraverseResult(cur, cur);
    }

    private TreeNode chooseMax(TreeNode... nodes) {
        TreeNode max = nodes[0];
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].val > max.val) {
                max = nodes[i];
            }
        }
        return max;
    }

    private TreeNode chooseMin(TreeNode... nodes) {
        TreeNode min = nodes[0];
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].val < min.val) {
                min = nodes[i];
            }
        }
        return min;
    }

    class TraverseResult {
        TreeNode maxNode;
        TreeNode minNode;
        TreeNode errorNode;

        public TraverseResult(TreeNode maxNode, TreeNode minNode) {
            this.maxNode = maxNode;
            this.minNode = minNode;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
