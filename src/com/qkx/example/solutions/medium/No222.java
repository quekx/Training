package com.qkx.example.solutions.medium;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 17/3/15.
 */
public class No222 {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = countLeftHeight(root.left);
        int rightHeight = countLeftHeight(root.right);
        if (leftHeight > rightHeight) {
            return countNodes(root.left) + (1 << rightHeight);
        } else {
            return countNodes(root.right) + (1 << leftHeight);
        }

    }

    private int countLeftHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

}
