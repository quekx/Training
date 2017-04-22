package com.qkx.example.solutions.LeetCode.easy.No10x_11x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/17.
 */
public class No100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        return checkNode(p, q);
    }

    private boolean checkNode(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p != null && q != null) {
            if (p.val !=  q.val) {
                return false;
            }

            boolean checkLeft = checkNode(p.left, q.left);
            boolean checkRight = checkNode(p.right, q.right);
            return checkLeft && checkRight;
        }
        return false;
    }
}
