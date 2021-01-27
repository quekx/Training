package com.qkx.example.solutions.LeetCode.medium;

/**
 * @author kaixin
 * @since 2021-01-27 16:02
 */
//Given the head of a linked list, remove the nth node from the end of the list
//and return its head.
//
// Follow up: Could you do this in one pass?
//
//
// Example 1:
//
//
//Input: head = [1,2,3,4,5], n = 2
//Output: [1,2,3,5]
//
//
// Example 2:
//
//
//Input: head = [1], n = 1
//Output: []
//
//
// Example 3:
//
//
//Input: head = [1,2], n = 1
//Output: [1]
//
//
//
// Constraints:
//
//
// The number of nodes in the list is sz.
// 1 <= sz <= 30
// 0 <= Node.val <= 100
// 1 <= n <= sz
//
// Related Topics Linked List Two Pointers
// 👍 4599 👎 281


//leetcode submit region begin(Prohibit modification and deletion)

import com.qkx.example.model.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class No19 {
    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:37.2 MB,击败了35.29% 的Java用户
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode k = head;
        for (int i = 1; i <= n; i++) {
            k = k.next;
        }

        if (k == null) {
            return head.next;
        }

        ListNode j = head;
        while (k.next != null) {
            k = k.next;
            j = j.next;
        }

        ListNode delete = j.next;
        j.next = delete.next;
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

