package com.qkx.example.exercise.algorithm.list;

import com.qkx.example.model.ListNode;

/**
 * Created by qkx on 17/3/21.
 */
public class ListMethod {
    public static ListNode invert(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode p = head;
        ListNode q = head.next;
        head.next = null;

        while (q != null) {
            ListNode t = q.next;
            q.next = p;
            p = q;
            q = t;
        }

        return p;
    }

    public static ListNode getList(int n) {
        if (n == 0) return null;

        ListNode head = new ListNode(-1);
        ListNode p = head;
        for (int i = 1; i < n; i++) {
            p.next = new ListNode((int) (Math.random() * 100));
            p = p.next;
        }
        return head;
    }

    public static String toListString(ListNode head) {
        if (head == null) return "null";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (head.next != null) {
            sb.append(head.val);
            sb.append("->");
            head = head.next;
        }
        sb.append(head.val);
        sb.append(']');
        return sb.toString();
    }
}
