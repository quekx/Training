package com.qkx.example.solutions.LeetCode.medium.No8x_9x;

import com.qkx.example.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by qkx on 16/6/12.
 */
public class No94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode top = stack.pop();
                // 输出
//                System.out.println(top.val);
                // System.out.print((char) top.val + " ");
                res.add(top.val);
                p = top.right;
            }
        }
        return res;
    }
}
