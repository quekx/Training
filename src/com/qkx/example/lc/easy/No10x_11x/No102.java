package com.qkx.example.lc.easy.No10x_11x;

import com.qkx.example.model.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by qkx on 16/9/17.
 */
public class No102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> tempQueue = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode n = queue.remove();
                temp.add(n.val);
                if (n.left != null) {
                    tempQueue.add(n.left);
                }
                if (n.right != null) {
                    tempQueue.add(n.right);
                }
            }
            res.add(temp);
            Queue<TreeNode> t = tempQueue;
            tempQueue = queue;
            queue = t;
        }

        return res;
    }
}
