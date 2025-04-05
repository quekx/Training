package com.qkx.example.lc.achive.medium;

/**
 * @author kaixin
 * @since 2021-09-22 16:25
 */
//You are given two non-empty linked lists representing two non-negative
//integers. The most significant digit comes first and each of their nodes contains a
//single digit. Add the two numbers and return the sum as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the
//number 0 itself.
//
//
// Example 1:
//
//
//Input: l1 = [7,2,4,3], l2 = [5,6,4]
//Output: [7,8,0,7]
//
//
// Example 2:
//
//
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [8,0,7]
//
//
// Example 3:
//
//
//Input: l1 = [0], l2 = [0]
//Output: [0]
//
//
//
// Constraints:
//
//
// The number of nodes in each linked list is in the range [1, 100].
// 0 <= Node.val <= 9
// It is guaranteed that the list represents a number that does not have
//leading zeros.
//
//
//
// Follow up: Could you solve it without reversing the input lists?
// Related Topics Linked List Math Stack 👍 2788 👎 214


//leetcode submit region begin(Prohibit modification and deletion)

import com.qkx.example.model.ListNode;
import com.qkx.example.utils.ListUtil;

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
public class No445 {

    /**
     * 16:43	info
     * 			运行失败:
     * 			Memory Limit Exceeded
     * 			测试用例:[7,2,4,3]
     * 			[5,6,4]
     * 			stdout:
     * @param args
     */
    public static void main(String[] args) {
        ListNode l1 = ListUtil.genList(7,2,4,3);
        ListNode l2 = ListUtil.genList(5,6,4);
        ListNode res = new No445().addTwoNumbers(l1, l2);
        ListUtil.print(res);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        int more = 0;
        ListNode front = new ListNode(-1);
        ListNode i = front;
        while (l1 != null || l2 != null || more != 0) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + more;
            i.next = new ListNode(sum % 10);
            more = sum / 10;
            i = i.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        ListNode res = front.next;
        front.next = null;
        return reverseList(res);
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

