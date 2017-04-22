package com.qkx.example.solutions.LeetCode.hard;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/20.
 */
public class No124 {
    private int result = Integer.MIN_VALUE;

    public int maxPathSumBetter(TreeNode root) {
        if (root == null) {
            return 0;
        }

        measureBetter(root);
        return result;
    }

    private int measureBetter(TreeNode h) {
        if (h == null) {
            return 0;
        }

        int leftSum = measureBetter(h.left);
        int rightSum = measureBetter(h.right);

        int maxPathSum = Math.max(leftSum, rightSum) + h.val;
        maxPathSum = h.val > maxPathSum ? h.val:maxPathSum;
        int max = Math.max(maxPathSum, leftSum + rightSum + h.val);
        result = max > result ? max : result;

        return maxPathSum;
    }

    // worse
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        SumResult sumResult = measure(root);

        return Math.max(sumResult.maxSumThroughRoot, sumResult.maxSumNotThroughRoot);
    }

    private SumResult measure(TreeNode h) {
        if (h == null) {
            return new SumResult(0, 0);
        }

        if (h.left == null && h.right == null) {
            return new SumResult(h.val, h.val);
        } else if (h.left != null && h.right != null) {
            SumResult left = measure(h.left);
            SumResult right = measure(h.right);
            int maxSumThroughRoot = Math.max(left.maxSumThroughRoot, right.maxSumThroughRoot) + h.val;
            maxSumThroughRoot = h.val > maxSumThroughRoot ? h.val : maxSumThroughRoot;

            int maxSumNotThroughRoot = Integer.MIN_VALUE;
            maxSumNotThroughRoot = left.maxSumNotThroughRoot > maxSumNotThroughRoot ?
                    left.maxSumNotThroughRoot : maxSumNotThroughRoot;
            maxSumNotThroughRoot = left.maxSumThroughRoot > maxSumNotThroughRoot ?
                    left.maxSumThroughRoot : maxSumNotThroughRoot;
            maxSumNotThroughRoot = right.maxSumNotThroughRoot > maxSumNotThroughRoot ?
                    right.maxSumNotThroughRoot : maxSumNotThroughRoot;
            maxSumNotThroughRoot = right.maxSumThroughRoot > maxSumNotThroughRoot ?
                    right.maxSumThroughRoot : maxSumNotThroughRoot;
            int sum = left.maxSumThroughRoot + right.maxSumThroughRoot + h.val;
            maxSumNotThroughRoot = sum > maxSumNotThroughRoot ? sum : maxSumNotThroughRoot;

            return new SumResult(maxSumThroughRoot, maxSumNotThroughRoot);
        }

        TreeNode child;
        if (h.left != null) {
            child = h.left;
        } else {
            child = h.right;
        }
        SumResult result = measure(child);
        int maxSumThroughRoot = result.maxSumThroughRoot > 0 ? result.maxSumThroughRoot + h.val : h.val;
        int maxSumNotThroughRoot = Math.max(result.maxSumThroughRoot, result.maxSumNotThroughRoot);
        return new SumResult(maxSumThroughRoot, maxSumNotThroughRoot);
    }

    private class SumResult {
        private int maxSumThroughRoot;
        private int maxSumNotThroughRoot;

        public SumResult(int maxSumThroughRoot, int maxSumNotThroughRoot) {
            this.maxSumThroughRoot = maxSumThroughRoot;
            this.maxSumNotThroughRoot = maxSumNotThroughRoot;
        }
    }
}
