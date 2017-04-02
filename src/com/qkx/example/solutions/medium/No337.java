package com.qkx.example.solutions.medium;

import com.qkx.example.model.TreeNode;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

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
    public int rob(TreeNode root) {
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

        HashMap hashMap = new HashMap();
        ConcurrentHashMap concurrentHashMap;

        return Math.max(cur, sub);
    }
}
