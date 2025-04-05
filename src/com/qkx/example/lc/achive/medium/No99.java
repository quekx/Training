package com.qkx.example.lc.achive.medium;

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
//       2
//    1     4
//  N  N   3  5

//       2
//    1     4
//  N  N   5  3

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

    private TreeNode preNode;

    private TreeNode firstNode;
    private TreeNode secondNode;

    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root);
        swap(firstNode, secondNode);
    }

    private void swap(TreeNode i, TreeNode k) {
        int vi = i.val;
        i.val = k.val;
        k.val = vi;
    }

    private void inOrder(TreeNode cur) {
        if (cur == null) {
            return;
        }
        inOrder(cur.left);

        if (preNode != null) {
            if (preNode.val > cur.val) {
                if (firstNode == null) {
                    firstNode = preNode;
                    secondNode = cur;
                } else {
                    secondNode = cur;
                }
            }
        }
        preNode = cur;

        inOrder(cur.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
