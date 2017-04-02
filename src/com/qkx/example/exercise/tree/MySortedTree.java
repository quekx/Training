package com.qkx.example.exercise.tree;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/6/12.
 */
public class MySortedTree {
    public static void insertNode(TreeNode root, int val) {
        TreeNode p = root;
        while (p != null) {
            if (p.val > val) {
                if (p.left == null) {
                    p.left = new TreeNode(val);
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new TreeNode(val);
                    return;
                }
                p = p.right;
            }
        }
    }
}
