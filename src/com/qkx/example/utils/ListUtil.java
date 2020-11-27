package com.qkx.example.utils;

import com.qkx.example.model.ListNode;

/**
 * Created by qkx on 17/6/17.
 */
public class ListUtil {
    public static void print(ListNode head) {
        if (head == null) return;

        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode genList(int... nums) {
        ListNode root = new ListNode(-1);
        ListNode pre = root;
        for (int num : nums) {
            pre.next = new ListNode(num);
            pre = pre.next;
        }
        return root.next;
    }
}
