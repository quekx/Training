package com.qkx.example.solutions.easy;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 17/3/20.
 */
public class No235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (p.val > q.val) {
            TreeNode t = p;
            p = q;
            q = t;
        }

        if (root.val >= p.val && root.val <= q.val) return root;
        else if (root.val < p.val) return lowestCommonAncestor(root.right, p, q);
        else return lowestCommonAncestor(root.left, p, q);
    }
}
