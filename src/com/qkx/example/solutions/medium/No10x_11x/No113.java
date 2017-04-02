package com.qkx.example.solutions.medium.No10x_11x;

import com.qkx.example.model.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/9/18.
 */
public class No113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        List<Integer> temp = new LinkedList<>();
        addNode(res, temp, root, 0, sum);
        return res;
    }

    private void addNode(List<List<Integer>> res, List<Integer> temp, TreeNode node, int preSum, int sum) {
        temp.add(node.val);
        int curSum = preSum + node.val;
        if (node.left == null && node.right == null) {
            if (curSum == sum) {
                res.add(temp);
            }
            return;
        }

        if (node.left != null && node.right != null) {
            List<Integer> tempRight = new LinkedList<>(temp);

            addNode(res, temp, node.left, curSum, sum);
            addNode(res, tempRight, node.right, curSum, sum);
            return;
        }

        if (node.left != null) {
            addNode(res, temp, node.left, curSum, sum);
        } else {
            addNode(res, temp, node.right, curSum, sum);
        }
    }
}
