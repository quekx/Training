package com.qkx.example.solutions.LeetCode.medium.x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 17/3/15.
 */
public class No230 {
    private int seq = 0;

    public int kthSmallest(TreeNode root, int k) {

        return findKthNode(root, k).val;
    }

    private TreeNode findKthNode(TreeNode node, int k) {
        if (node == null) return null;

        TreeNode left = findKthNode(node.left, k);
        if (left != null) return left;

        if (seq == k - 1) return node;

        seq++;
        return findKthNode(node.right, k);
    }
}
