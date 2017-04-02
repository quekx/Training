package com.qkx.example.solutions.unfinish;

import com.qkx.example.model.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/6/13.
 */
public class No95 {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new LinkedList<>();
        if (n == 1) {
            res.add(new TreeNode(1));
            return res;
        }

        return null;
    }
    public void addNode(boolean added, TreeNode root, TreeNode current, TreeNode newCurrent, List<TreeNode> res, int n) {
        if (current == null) {
            return;
        }
        if (added) {
            if (current.left != null) {
                newCurrent.left = new TreeNode(current.left.val);
                addNode(added, root, current.left, newCurrent.left, res, n);
            }
            if (current.right != null) {
                newCurrent.right = new TreeNode(current.right.val);
                addNode(added, root, current.right, newCurrent.right, res, n);
            }
        } else {
            TreeNode next = current.right;
            if (next == null) {

            }
        }

    }
}
