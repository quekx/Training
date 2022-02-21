package com.qkx.example.lc.easy.No10x_11x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/17.
 */
public class No101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return checkNode(root.left, root.right);
    }

    private boolean checkNode(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p != null && q != null) {
            if (p.val !=  q.val) {
                return false;
            }

            boolean checkLeft = checkNode(p.left, q.right);
            boolean checkRight = checkNode(p.right, q.left);
            return checkLeft && checkRight;
        }
        return false;
    }
}
