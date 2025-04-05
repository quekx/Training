package com.qkx.example.lc.achive.medium.x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 17/3/20.
 */
public class No337 {
    /**
     * The thief has found himself a new place for his thievery again. There is only one entrance to this area,
     * called the "root." Besides the root, each house has one and only one parent house. After a tour,
     * the smart thief realized that "all houses in this place forms a binary tree".
     * It will automatically contact the police if two directly-linked houses were broken into on the same night.
     * Determine the maximum amount of money the thief can rob tonight without alerting the police.
     */
    public int rob2(TreeNode root) {
//        Scanner
        if (root == null) return 0;

        int curLeft = 0;
        int curRight = 0;
        if (root.left != null) {
            curLeft = rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            curRight = rob(root.right.left) + rob(root.right.right);
        }
        int cur = curLeft + curRight + root.val;

        int left = rob(root.left);
        int right = rob(root.right);
        int sub = left + right;
        return Math.max(cur, sub);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了78.00% 的Java用户
     * 	内存消耗:44.4 MB,击败了48.14% 的Java用户
     */
    public int rob(TreeNode root) {
        int[] r = robInner(root);
        return Math.max(r[0], r[1]);
    }

    private int[] robInner(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int robCurrent = node.val;
        int[] left = robInner(node.left);
        int[] right = robInner(node.right);
        return new int[]{robCurrent + left[1] + right[1], Math.max(left[0], left[1]) + Math.max(right[0], right[1])};
    }
}
