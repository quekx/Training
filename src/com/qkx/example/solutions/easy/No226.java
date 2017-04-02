package com.qkx.example.solutions.easy;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 17/3/15.
 */
public class No226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode node = new TreeNode(root.val);
        node.left = invertTree(root.right);
        node.right = invertTree(root.left);

        return node;
    }
}
