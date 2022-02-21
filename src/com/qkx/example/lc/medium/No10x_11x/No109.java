package com.qkx.example.lc.medium.No10x_11x;

import com.qkx.example.model.ListNode;
import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/18.
 */
public class No109 {
    ListNode node = null;

    public TreeNode sortedListToBST(ListNode head) {
        node = head;
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }

        return createNodeInInorder(0, len - 1);
    }

    private TreeNode createNodeInInorder(int start, int end) {
        if (start > end) {
            return null;
        }

        int cur = start + ((end - start) >> 1);
        TreeNode left = createNodeInInorder(start, cur - 1);

        TreeNode h = new TreeNode(node.val);
        node = node.next;

        TreeNode right = createNodeInInorder(cur + 1, end);

        h.left = left;
        h.right = right;
        return h;
    }

}
