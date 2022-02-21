package com.qkx.example.lc.medium.x;

import com.qkx.example.model.TreeNode;

import java.util.Stack;

/**
 * Created by qkx on 17/3/4.
 */
public class No173 {

    /**
     Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

     Calling next() will return the next smallest number in the BST.

     Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
     */

    public static class BSTIterator {

        Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            add(root);
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode cur = stack.pop();
            if (cur.right != null) {
                add(cur.right);
            }
            return cur.val;
        }

        private void add(TreeNode node) {
            if (node == null) return;

            TreeNode p = node;
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }
    }
}
