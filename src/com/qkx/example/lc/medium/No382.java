package com.qkx.example.lc.medium;

import com.qkx.example.model.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by qkx on 17/7/9.
 */
public class No382 {
    /**
     * Given a singly linked list, return a random node's value from the linked list.
     * Each node must have the same probability of being chosen.
     *
     * Follow up:
     * What if the linked list is extremely large and its length is unknown to you?
     * Could you solve this efficiently without using extra space?
     */
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        Map<Integer, Integer> map = new HashMap<>();
        Solution1 s = new Solution1(node1);
        for (int i = 0; i < 10000; i++) {
            int num = s.getRandom();
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        System.out.println(map);
    }
}
class Solution1 {

    private ListNode head;
    private Random random;

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution1(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int k = random.nextInt();
        k = k & 0x7FFFFFFF;
        int pos = k;
        int len = 1;
        ListNode p = head;
        while (pos > 0) {
            if (p.next != null) {
                p = p.next;
                len++;
                pos--;
            } else {
                p = head;
                pos = k % len;
            }
        }
        return p.val;
    }
}
