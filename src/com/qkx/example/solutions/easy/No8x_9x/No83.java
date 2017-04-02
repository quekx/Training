package com.qkx.example.solutions.easy.No8x_9x;

import com.qkx.example.model.ListNode;

/**
 * Created by qkx on 16/5/27.
 */
public class No83 {
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode node = new ListNode(Integer.MAX_VALUE);
        node.next = head;

        ListNode pre = node;
        ListNode last = node;
        ListNode i = head;
        while (i != null) {
            if (last.val != i.val) {
                pre.next = i;
                pre = i;
            }

            last = last.next;
            i = i.next;
        }
        if (pre.val == last.val) {
            pre.next = null;
        }
        return node.next;
    }
}

