package com.qkx.example.lc.achive.medium;

import com.qkx.example.model.ListNode;

/**
 * Created by qkx on 17/6/17.
 */
public class No328 {
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;

        ListNode oddHead = new ListNode(0);
        ListNode odd = oddHead;
        ListNode evenHead = new ListNode(0);
        ListNode even = evenHead;

        ListNode p = head;
        boolean isOdd = true;
        while (p != null) {
            if (isOdd) {
                odd.next = p;
                odd = p;
            } else {
                even.next = p;
                even = p;
            }
            isOdd = !isOdd;
            p = p.next;
        }

        even.next = null;
        odd.next = evenHead.next;
        return oddHead.next;
    }
}
