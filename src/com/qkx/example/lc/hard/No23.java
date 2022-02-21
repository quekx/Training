package com.qkx.example.lc.hard;

/**
 * @author kaixin
 * @since 2021-01-13 17:34
 */
//You are given an array of k linked-lists lists, each linked-list is sorted in
//ascending order.
//
// Merge all the linked-lists into one sorted linked-list and return it.
//
//
// Example 1:
//
//
//Input: lists = [[1,4,5],[1,3,4],[2,6]]
//Output: [1,1,2,3,4,4,5,6]
//Explanation: The linked-lists are:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//merging them into one sorted list:
//1->1->2->3->4->4->5->6
//
//
// Example 2:
//
//
//Input: lists = []
//Output: []
//
//
// Example 3:
//
//
//Input: lists = [[]]
//Output: []
//
//
//
// Constraints:
//
//
// k == lists.length
// 0 <= k <= 10^4
// 0 <= lists[i].length <= 500
// -10^4 <= lists[i][j] <= 10^4
// lists[i] is sorted in ascending order.
// The sum of lists[i].length won't exceed 10^4.
//
// Related Topics Linked List Divide and Conquer Heap
// 👍 6128 👎 327



//leetcode submit region begin(Prohibit modification and deletion)

import com.qkx.example.model.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 *
 * 20:28	info
 * 解答成功:
 * 执行耗时:1 ms,击败了100.00% 的Java用户
 * 内存消耗:40.9 MB,击败了39.36% 的Java用户
 *
 */
public class No23 {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (left + 1 == right) {
            return mergeList(lists[left], lists[right]);
        }

        int mid = (left + right) / 2;
        ListNode leftResult = merge(lists, left, mid);
        ListNode rightResult = merge(lists, mid + 1, right);
        return mergeList(leftResult, rightResult);
    }

    private ListNode mergeList(ListNode list1, ListNode list2) {
        ListNode root = new ListNode(-1);
        ListNode tail = root;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            }
        }
        if (list1 != null) {
            tail.next = list1;
        } else if (list2 != null) {
            tail.next = list2;
        }
        return root.next;
    }

    //[
    //  1->4->5,
    //  1->3->4,
    //  2->6
    //]
    public static void main(String[] args) {
        ListNode i1 = new ListNode(1);
        ListNode i2 = new ListNode(4);
        ListNode i3 = new ListNode(5);
        i1.next = i2;
        i2.next = i3;

        ListNode i4 = new ListNode(1);
        ListNode i5 = new ListNode(3);
        ListNode i6 = new ListNode(4);
        i4.next = i5;
        i5.next = i6;

        ListNode i7 = new ListNode(2);
        ListNode i8 = new ListNode(6);
        i7.next = i8;

        ListNode[] input = (ListNode[]) Arrays.asList(i1, i4, i7).toArray();
        ListNode out = new No23().mergeKLists(input);
        while (out != null) {
            System.out.println(out.val);
            out = out.next;
        }
    }

    public ListNode mergeKLists3(ListNode[] lists) {
        ListNode root = new ListNode(-1);
        ListNode tail = root;
        ListNode min = chooseMin(lists);
        while (min != null) {
            tail.next = min;
            tail = min;
            min = chooseMin(lists);
        }

        return root.next;
    }

    private ListNode chooseMin(ListNode[] list) {
        int minIndex = -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                continue;
            }
            if (minIndex < 0) {
                minIndex = i;
            } else {
                minIndex = list[i].val < list[minIndex].val ? i : minIndex;
            }
        }
        if (minIndex < 0) {
            return null;
        } else {
            ListNode min = list[minIndex];
            list[minIndex] = min.next;
            return min;
        }
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        queue.addAll(Arrays.asList(lists));

        ListNode root = new ListNode(-1);
        ListNode tail = root;
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            tail.next = cur;
            if (cur.next != null) {
                queue.add(cur.next);
            }
            tail = cur;
        }
        return root.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

