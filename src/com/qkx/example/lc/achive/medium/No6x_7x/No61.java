package com.qkx.example.lc.achive.medium.No6x_7x;

/**
 * Created by qkx on 16/5/24.
 */
public class No61 {

    public static ListNode rotateRight(ListNode head, int k) {

        if (head == null) {
            return null;
        }

        int n = 0;
        ListNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }

        k = k % n;

        if (k == 0) {
            return head;
        }

        ListNode left = head;
        ListNode right = head;

        for (int i = 1; i <= k; i++) {
            right = right.next;
        }


        while (right.next != null) {
            right = right.next;
            left = left.next;
        }

        ListNode res = left.next;

        left.next = null;
        right.next = head;

        return res;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
