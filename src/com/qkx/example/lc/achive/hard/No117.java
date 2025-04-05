package com.qkx.example.lc.achive.hard;


import com.qkx.example.model.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by qkx on 16/9/18.
 */
public class No117 {
    public static void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeLinkNode> queue = new LinkedList<>();
        Queue<TreeLinkNode> tempQueue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            connectNode(queue.peek(), null);

            while (!queue.isEmpty()) {
                TreeLinkNode h = queue.remove();
                if (h.left != null) {
                    tempQueue.add(h.left);
                }
                if (h.right != null) {
                    tempQueue.add(h.right);
                }
            }
            Queue<TreeLinkNode> t = queue;
            queue = tempQueue;
            tempQueue = t;
        }

    }

    private static void connectNode(TreeLinkNode cur, TreeLinkNode lastChild) {
        if (cur == null) {
            return;
        }

        if (cur.left == null && cur.right == null) {
            connectNode(cur.next, lastChild);
            return;
        }

        TreeLinkNode front;
        TreeLinkNode rear;
        if (cur.left != null && cur.right != null) {
            cur.left.next = cur.right;
            front = cur.left;
            rear = cur.right;
        } else if (cur.left != null) {
            front = cur.left;
            rear = cur.left;
        } else {
            front = cur.right;
            rear = cur.right;
        }

        if (lastChild != null) {
            lastChild.next = front;
        }

        connectNode(cur.next, rear);

    }
}
