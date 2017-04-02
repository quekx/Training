package com.qkx.example.solutions.medium.No10x_11x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/18.
 */
public class No114 {
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        changeNode(root);
    }

    private static ChangeResult changeNode(TreeNode node) {
        if (node.left == null && node.right == null) {
            return new ChangeResult(node, node);
        }

        if (node.left != null && node.right != null) {
            ChangeResult leftResult = changeNode(node.left);
            ChangeResult rightResult = changeNode(node.right);

            node.right = leftResult.start;
            node.left = null;

            leftResult.end.right = rightResult.start;
            return new ChangeResult(node, rightResult.end);
        }

        ChangeResult result;
        if (node.left != null) {
            result = changeNode(node.left);
            node.left = null;
        } else {
            result = changeNode(node.right);
        }
        node.right = result.start;
        return new ChangeResult(node, result.end);
    }

    private static class ChangeResult {
        TreeNode start;
        TreeNode end;

        public ChangeResult(TreeNode start, TreeNode end) {
            this.start = start;
            this.end = end;
        }
    }
}
