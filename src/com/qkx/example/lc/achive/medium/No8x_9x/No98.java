package com.qkx.example.lc.achive.medium.No8x_9x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/7/2.
 */
public class No98 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return judgeCurNode(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean judgeCurNode (TreeNode curNode, long min, long max) {
        if (curNode.left == null && curNode.right == null) {
            return true;
        } else if (curNode.left == null) {
            if (curNode.val >= curNode.right.val || curNode.right.val >= max) {
                return false;
            } else {
                return judgeCurNode(curNode.right, curNode.val, max);
            }
        } else if (curNode.right == null) {
            if (curNode.val <= curNode.left.val || curNode.left.val <= min) {
                return false;
            } else {
                return judgeCurNode(curNode.left, min, curNode.val);
            }
        } else {
            if (curNode.val <= curNode.left.val || curNode.val >= curNode.right.val) {
                return false;
            } else if (curNode.left.val <= min || curNode.right.val >= max) {
                return false;
            } else {
                return judgeCurNode(curNode.left, min, curNode.val) && judgeCurNode(curNode.right, curNode.val, max);
            }
        }
    }
}
