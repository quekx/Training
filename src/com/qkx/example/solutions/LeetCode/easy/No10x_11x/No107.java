package com.qkx.example.solutions.LeetCode.easy.No10x_11x;

import com.qkx.example.model.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by qkx on 16/9/18.
 */
public class No107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Stack<List<Integer>> stack = new Stack<>();
        Queue<TreeNode> tempQueue = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode h = queue.remove();
                temp.add(h.val);
                if (h.left != null) {
                    tempQueue.add(h.left);
                }
                if (h.right != null) {
                    tempQueue.add(h.right);
                }
            }

            Queue<TreeNode> t = tempQueue;
            tempQueue = queue;
            queue = t;

            stack.push(temp);
        }

        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }
}
