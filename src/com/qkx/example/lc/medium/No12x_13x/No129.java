package com.qkx.example.lc.medium.No12x_13x;

import com.qkx.example.model.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/9/22.
 */
public class No129 {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;

        calculateNode(root, 0);
        return sum;
    }

    private void calculateNode(TreeNode h, int pre) {
        int cur = pre * 10 + h.val;
        if (h.left == null && h.right == null) {
            sum += cur;
            return;
        }

        if (h.left != null) {
            calculateNode(h.left, cur);
        }

        if (h.right != null) {
            calculateNode(h.right, cur);
        }

    }

    public int sumNumbersWorse(TreeNode root) {
        if (root == null) return 0;

        List<Integer> res = new LinkedList<>();
        calculateNodeWorse(root, 0, res);

        int sum = 0;
        for (int num : res) {
            sum += num;
        }
        return sum;
    }

    private void calculateNodeWorse(TreeNode h, int pre, List<Integer> res) {
        int cur = pre * 10 + h.val;
        if (h.left == null && h.right == null) {
            res.add(cur);
            return;
        }

        if (h.left != null) {
            calculateNodeWorse(h.left, cur, res);
        }

        if (h.right != null) {
            calculateNodeWorse(h.right, cur, res);
        }

    }
}
