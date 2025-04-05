package com.qkx.example.lc.achive.easy.No10x_11x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/18.
 */
public class No112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return checkSum(root, 0, sum);
    }

    private boolean checkSum(TreeNode node, int preSum, int sum) {
        int curSum = preSum + node.val;
        if (node.left == null && node.right == null) {
            return curSum == sum;
        }

        if (node.left != null && node.right != null) {
            return checkSum(node.left, curSum, sum) || checkSum(node.right, curSum, sum);
        }

        if (node.left != null) {
            return checkSum(node.left, curSum, sum);
        } else {
            return checkSum(node.right, curSum, sum);
        }
    }
}
