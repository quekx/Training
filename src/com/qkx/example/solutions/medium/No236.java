package com.qkx.example.solutions.medium;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 17/3/20.
 */
public class No236 {
    /**
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     * According to the definition of LCA on Wikipedia:
     * “The lowest common ancestor is defined between two nodes v and w as the lowest node
     * in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p) return p;
        if (root == q) return q;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        else if (left != null) return left;
        else return right;
    }
}
