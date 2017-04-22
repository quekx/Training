package com.qkx.example.solutions.LeetCode.medium.No10x_11x;

import com.qkx.example.model.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by qkx on 16/9/17.
 */
public class No103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();

        if (root == null) {
            return res;
        }

        Stack<TreeNode> tempStack = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        boolean isLeft = true;

        while (!stack.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            while (!stack.isEmpty()) {
                TreeNode n = stack.pop();
                temp.add(n.val);
                if (isLeft) {
                    if (n.left != null) {
                        tempStack.push(n.left);
                    }
                    if (n.right != null) {
                        tempStack.push(n.right);
                    }
                } else {
                    if (n.right != null) {
                        tempStack.push(n.right);
                    }
                    if (n.left != null) {
                        tempStack.push(n.left);
                    }
                }
            }
            res.add(temp);
            Stack<TreeNode> t = tempStack;
            tempStack = stack;
            stack = t;
            isLeft = !isLeft;
        }

        return res;
    }
}
