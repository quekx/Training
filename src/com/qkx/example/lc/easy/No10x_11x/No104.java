package com.qkx.example.lc.easy.No10x_11x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/17.
 */
public class No104 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
