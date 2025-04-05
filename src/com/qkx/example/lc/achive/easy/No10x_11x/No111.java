package com.qkx.example.lc.achive.easy.No10x_11x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/18.
 */
public class No111 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        if (root.left != null && root.right != null) {
            int leftMinDepth = minDepth(root.left);
            int rightMinDepth = minDepth(root.right);

            return Math.min(leftMinDepth, rightMinDepth) + 1;
        }

        if (root.left != null) {
            return minDepth(root.left) + 1;
        } else {
            return minDepth(root.right) + 1;
        }
    }
}
