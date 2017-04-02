package com.qkx.example.solutions.medium.No8x_9x;

import com.qkx.example.model.ListNode;

import java.util.Stack;

/**
 * Created by qkx on 16/5/29.
 */
public class No92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {

        if (head == null) {
            return null;
        }
        if (m == n) {
            return head;
        }

        ListNode mHead = new ListNode(0);
        mHead.next = head;

        ListNode current = mHead;

        ListNode front = null;
        ListNode rear = null;

        Stack<ListNode> stack = new Stack<>();
        int i = 0;
        while (i < n + 1) {
            if (i == m - 1) {
                front = current;
            }
            if (i >= m && i <= n) {
                stack.push(current);
            }
            current = current.next;
            i++;
        }
        rear = current;

        ListNode last;
        while (!stack.isEmpty()) {
            last = stack.pop();
            front.next = last;
            front = front.next;
        }
        front.next = rear;

        return mHead.next;
    }
}
