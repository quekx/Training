package com.qkx.example;

//Given the root of a binary tree, return an array of the largest value in each
//row of the tree (0-indexed).
//
//
// Example 1:
//
//
//Input: root = [1,3,2,5,3,null,9]
//Output: [1,3,9]
//
//
// Example 2:
//
//
//Input: root = [1,2,3]
//Output: [1,3]
//
//
//
// Constraints:
//
//
// The number of nodes in the tree will be in the range [0, 10⁴].
// -2³¹ <= Node.val <= 2³¹ - 1
//
//
// Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 25
//93 👎 93


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
class No515 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了71.70% 的Java用户
     * 	内存消耗:43 MB,击败了25.69% 的Java用户
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int max = deque.peek().val;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                max = Math.max(max, cur.val);
                if (cur.left != null) {
                    deque.add(cur.left);
                }
                if (cur.right != null) {
                    deque.add(cur.right);
                }
            }
            ans.add(max);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

