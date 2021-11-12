package com.qkx.example.solutions.LeetCode.medium;

/**
 * @author kaixin
 * @since 2021-01-27 16:22
 */
//Given a linked list, swap every two adjacent nodes and return its head.
//
//
// Example 1:
//
//
//Input: head = [1,2,3,4]
//Output: [2,1,4,3]
//
//
// Example 2:
//
//
//Input: head = []
//Output: []
//
//
// Example 3:
//
//
//Input: head = [1]
//Output: [1]
//
//
//
// Constraints:
//
//
// The number of nodes in the list is in the range [0, 100].
// 0 <= Node.val <= 100
//
//
//
//Follow up: Can you solve the problem without modifying the values in the list'
//s nodes? (i.e., Only nodes themselves may be changed.) Related Topics Linked Lis
//t Recursion
// 👍 3223 👎 199


//leetcode submit region begin(Prohibit modification and deletion)

import com.qkx.example.model.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class No24 {
    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:36.6 MB,击败了63.34% 的Java用户
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode root = new ListNode(-1);
        root.next = head;

        ListNode pre = root;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            ListNode post = next.next;
            next.next = cur;
            pre.next = next;
            cur.next = post;
            pre = cur;
            cur = post;
        }
        return root.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
