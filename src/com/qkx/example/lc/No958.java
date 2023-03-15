package com.qkx.example.lc;

//Given the root of a binary tree, determine if it is a complete binary tree.
//
// In a complete binary tree, every level, except possibly the last, is
//completely filled, and all nodes in the last level are as far left as possible. It can
//have between 1 and 2ʰ nodes inclusive at the last level h.
//
//
// Example 1:
//
//
//Input: root = [1,2,3,4,5,6]
//Output: true
//Explanation: Every level before the last is full (ie. levels with node-values
//{1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as
//possible.
//
//
// Example 2:
//
//
//Input: root = [1,2,3,4,5,null,7]
//Output: false
//Explanation: The node with value 7 isn't as far left as possible.
//
//
//
// Constraints:
//
//
// The number of nodes in the tree is in the range [1, 100].
// 1 <= Node.val <= 1000
//
//
// Related Topics Tree Breadth-First Search Binary Tree 👍 3412 👎 43


//leetcode submit region begin(Prohibit modification and deletion)

import com.qkx.example.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

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
class No958 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了83.31% 的Java用户
     * 	内存消耗:41.8 MB,击败了67.59% 的Java用户
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> tmp = new LinkedList<>();
        root.val = 1;
        queue.add(root);
        int num = 0;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                num++;
                TreeNode cur = queue.poll();
                if (cur.val != num) {
                    return false;
                }
                if (cur.left != null) {
                    cur.left.val = cur.val * 2;
                    tmp.add(cur.left);
                }
                if (cur.right != null) {
                    cur.right.val = cur.val * 2 + 1;
                    tmp.add(cur.right);
                }
            }
            Queue<TreeNode> t = queue;
            queue = tmp;
            tmp = t;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

