package com.qkx.example.solutions.medium.x;

import com.qkx.example.model.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by qkx on 17/3/15.
 */
public class No199 {
    /**
     * Given a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
     */
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new LinkedList<>();
        if (root == null) return res;

        Queue<TreeNode> curList = new LinkedList<>();
        Queue<TreeNode> tempList = new LinkedList<>();

        curList.add(root);
        while (!curList.isEmpty()) {
            TreeNode right = curList.peek();
            res.add(right.val);

            while (!curList.isEmpty()) {
                TreeNode node = curList.remove();
                if (node.right != null) {
                    tempList.add(node.right);
                }
                if (node.left != null) {
                    tempList.add(node.left);
                }
            }
            Queue<TreeNode> t = curList;
            curList = tempList;
            tempList = t;
        }

        return res;
    }

}
