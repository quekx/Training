package com.qkx.example.lc.easy;

import com.qkx.example.model.ListNode;

/**
 * Created by qkx on 17/4/9.
 */
public class No203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode result = head;
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == val) {
                remove(pre, cur);
            } else {
                pre = cur;
            }
            cur = next;

            if (pre == null) {
                result = next;
            }
        }
        return result;
    }

    private void remove(ListNode pre, ListNode x) {
        ListNode xn = x.next;
        if (pre != null) {
            pre.next = xn;
        }
    }
}
