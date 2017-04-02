package com.qkx.example.solutions.medium.No8x_9x;

import com.qkx.example.model.ListNode;

/**
 * Created by qkx on 16/5/27.
 */
public class No82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode node = new ListNode(Integer.MAX_VALUE);
        node.next = head;

        ListNode pre = node;
        ListNode last = node;
        ListNode i = head;
        boolean hasDuplicates = false;
        while (i != null) {
            if (last.val == i.val) {
                hasDuplicates = true;
            } else {
                if (hasDuplicates) {
                    pre.next = i;
                    hasDuplicates = false;
                } else {
                    pre = last;
                }
            }
            last = last.next;
            i = i.next;
        }

        if (hasDuplicates) {
            pre.next = null;
        }

        return node.next;
    }

}
