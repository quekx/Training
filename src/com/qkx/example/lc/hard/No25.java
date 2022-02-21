package com.qkx.example.lc.hard;

/**
 * @author kaixin
 * @since 2020-11-27 14:35
 */
//Given a linked list, reverse the nodes of a linked list k at a time and return
// its modified list.
//
// k is a positive integer and is less than or equal to the length of the linked
// list. If the number of nodes is not a multiple of k then left-out nodes, in the
// end, should remain as it is.
//
// Follow up:
//
//
// Could you solve the problem in O(1) extra memory space?
// You may not alter the values in the list's nodes, only nodes itself may be ch
//anged.
//
//
//
// Example 1:
//
//
//Input: head = [1,2,3,4,5], k = 2
//Output: [2,1,4,3,5]
//
//
// Example 2:
//
//
//Input: head = [1,2,3,4,5], k = 3
//Output: [3,2,1,4,5]
//
//
// Example 3:
//
//
//Input: head = [1,2,3,4,5], k = 1
//Output: [1,2,3,4,5]
//
//
// Example 4:
//
//
//Input: head = [1], k = 1
//Output: [1]
//
//
//
// Constraints:
//
//
// The number of nodes in the list is in the range sz.
// 1 <= sz <= 5000
// 0 <= Node.val <= 1000
// 1 <= k <= sz
//
// Related Topics Linked List
// 👍 2943 👎 379


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
public class No25 {
    /**
     * 从根节点开始，循环找到需要翻转的一段链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode root = new ListNode(-1);
        // 前一段翻转完成后的尾节点
        ListNode preTail = root;
        ListNode curHead = head;
        while (curHead != null) {
            ListNode curTail = curHead;
            for (int j = 1; j <= k - 1; j++) {
                curTail = curTail.next;
                if (curTail == null) {
                    break;
                }
            }

            if (curTail == null) {
                break;
            }
            System.out.println(curHead.val + " >>> " + curTail.val);
            ListNode nextHead = curTail.next;
            // 翻转从curHead到curTail这一段链表
            ListNode pre = null;
            ListNode m = curHead;
            while (pre != curTail) {
                ListNode next = m.next;
                m.next = pre;
                pre = m;
                m = next;
            }
            // 翻转完成
            // 头为curTail
            // 尾为curHead
            preTail.next = curTail;
            curHead.next = nextHead;
            preTail = curHead;
            curHead = nextHead;
        }

        return root.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
