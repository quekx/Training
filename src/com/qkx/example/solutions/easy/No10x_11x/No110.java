package com.qkx.example.solutions.easy.No10x_11x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/18.
 */
public class No110 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return checkNode(root).isBalance;
    }

    private Result checkNode(TreeNode h) {
        if (h == null) {
            return new Result(true, 0);
        }

        Result leftResult = checkNode(h.left);
        if (!leftResult.isBalance) {
            return new Result(false, 0);
        }

        Result rightResult = checkNode(h.right);
        if (!rightResult.isBalance) {
            return new Result(false, 0);
        }

        boolean isBalance = Math.abs(leftResult.depth - rightResult.depth) <= 1;
        int depth = Math.max(leftResult.depth, rightResult.depth) + 1;

        return new Result(isBalance, depth);
    }

    private class Result {
        boolean isBalance;
        int depth;

        Result(boolean isBalance, int depth) {
            this.isBalance = isBalance;
            this.depth = depth;
        }
    }
}
